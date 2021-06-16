package exercicioPessoaFisicaJuridica.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.spec.PSource;

import exercicioPessoaFisicaJuridica.Conexao;
import exercicioPessoaFisicaJuridica.ValueObject.CNPJ;
import exercicioPessoaFisicaJuridica.ValueObject.CPF;

public class PessoaRepository extends Conexao {
	private Connection conexao;
	
	public PessoaRepository()
	{
		this.conexao = this.abrirConexao();
	}
	
	public void incluir(Pessoa pessoa) throws SQLException
	{
		PreparedStatement psIncluir;
		String tipo = null;
		
		if (pessoa instanceof Pessoa_Fisica) {
			tipo = "Fisica";
		}else
		{
			tipo = "Juridica";
		}
		
		psIncluir = conexao.prepareStatement("INSERT INTO PESSOA(id, tipo_pessoa, nome)VALUES(?, ?, ?)");
		psIncluir.setLong(1, pessoa.getId_pessoa());
		psIncluir.setString(2, tipo);
		psIncluir.setString(3, pessoa.getNome());
		psIncluir.execute();
		psIncluir.close();
		
		if(tipo.equals("Fisica"))
		{
			this.inserirFisica(pessoa);
		}else
		{
			this.inserirJuridica(pessoa);
		}
	}

	private void inserirJuridica(Pessoa pessoa) throws SQLException 
	{
		Pessoa_Juridica aux = (Pessoa_Juridica) pessoa;
		PreparedStatement psIncluir = conexao.prepareStatement("INSERT INTO PESSOA_JURIDICA(id, cnpj)VALUES(?, ?)");
	    psIncluir.setLong(1, pessoa.getId_pessoa());
	    psIncluir.setString(2, aux.getCnpj().getValor());
	    psIncluir.execute();
		psIncluir.close();
	}

	private void inserirFisica(Pessoa pessoa) throws SQLException 
	{
		Pessoa_Fisica aux = (Pessoa_Fisica) pessoa;
		PreparedStatement psIncluir = conexao.prepareStatement("INSERT INTO PESSOA_FISICA(id, cpf)VALUES(?, ?)");
	    psIncluir.setLong(1, pessoa.getId_pessoa());
	    psIncluir.setString(2, aux.getCpf().getValor());
	    psIncluir.execute();
	    psIncluir.close();
	}
	
	public void excluir(Pessoa pessoa, Long id) throws SQLException
	{
		PreparedStatement psExcluir = this.conexao.prepareStatement("DELETE FROM pessoa_fisica WHERE id = ?");
		psExcluir.setLong(1, id);
		psExcluir.execute();
		psExcluir.close();
		
		psExcluir = this.conexao.prepareStatement("DELETE FROM pessoa WHERE id = ?");
		psExcluir.setLong(1, id);
		psExcluir.execute();
		psExcluir.close();
	}
	
	public void update(Pessoa pessoa, Long id) throws SQLException
	{
		PreparedStatement psUpdate = this.conexao.prepareStatement("UPDATE pessoa set nome = ? where id = ?");
		psUpdate.setString(1, pessoa.getNome());
		psUpdate.setLong(2, id);
		psUpdate.execute();
		psUpdate.close();
		String tipo = "";
		
		if (pessoa instanceof Pessoa_Fisica) {
			this.update_juridica(pessoa, id, psUpdate);
		}else
		{
			this.update_juridica(pessoa, id, psUpdate);
		}
	}

	private void update_juridica(Pessoa pessoa, Long id, PreparedStatement psUpdate) throws SQLException {
		Pessoa_Fisica aux = (Pessoa_Fisica) pessoa;
		psUpdate = this.conexao.prepareStatement("UPDATE pessoa_fisica SET NOME = ? , CPF = ? where id = ?");
		psUpdate.setString(1, aux.getNome());
		psUpdate.setString(2, aux.getCpf().getValor());
		psUpdate.setLong(3, id);
		psUpdate.execute();
		psUpdate.close();
	}

	private void update_fisica(Pessoa pessoa, Long id, PreparedStatement psUpdate) throws SQLException {
		Pessoa_Juridica aux = (Pessoa_Juridica) pessoa;
		psUpdate = this.conexao.prepareStatement("UPDATE pessoa_juridica NOME = ? , CNPJ = ? where id = ?");
		psUpdate.setString(1, aux.getNome());
		psUpdate.setString(2, aux.getCnpj().getValor());
		psUpdate.setLong(3, id);
		psUpdate.execute();
		psUpdate.close();
	}
	
	public void excluirPeloId(Long id) throws Exception
	{
		try
		{
			PreparedStatement psExcluirPessoa   = this.conexao.prepareStatement("DELETE FROM pessoa where id = ?");
			PreparedStatement psExcluirFisica   = this.conexao.prepareStatement("DELETE FROM pessoa_fisica where id = ?");
			PreparedStatement psExcluirJuridica = this.conexao.prepareStatement("DELETE FROM pessoa_juridica where id = ?");
			
			psExcluirFisica.setLong(1, id);
			psExcluirJuridica.setLong(1, id);
			psExcluirPessoa.setLong(1, id);
			
			psExcluirFisica.execute();
			psExcluirJuridica.execute();
			psExcluirPessoa.execute();
			
			psExcluirFisica.close();
			psExcluirJuridica.close();
			psExcluirPessoa.close();
			
			this.conexao.commit();
		}catch(Exception e)
		{
			this.conexao.rollback();
			e.printStackTrace();
		}
	}
	
	public Pessoa recuperarPeloId(Long id)
	{
		Pessoa recuperada = null;
		try
		{
			PreparedStatement psSelectPessoa   = this.conexao.prepareStatement("SELECT ID, NOME, TIPO_PESSOA FROM pessoa where id = ?");
			PreparedStatement psSelectFisica   = this.conexao.prepareStatement("SELECT ID, CPF FROM pessoa_fisica where id = ?");
			PreparedStatement psSelectJuridica = this.conexao.prepareStatement("SELECT ID, CNPJ FROM pessoa_juridica where id = ?");
			psSelectPessoa.setLong(1, id);
			ResultSet rsPessoa = psSelectPessoa.executeQuery();
			
			if(rsPessoa.next())
			{
				id = rsPessoa.getLong("id");
				String nome = rsPessoa.getString("NOME");
				String tipoPessoa = rsPessoa.getString("TIPO_PESSOA");
				
				if(tipoPessoa.equals("Fisica"))
				{
					psSelectFisica.setLong(1, id);
					ResultSet rsFisica = psSelectFisica.executeQuery();
					
					if(rsFisica.next())
					{
						return recuperada = new Pessoa_Fisica(id, nome, new CPF(rsFisica.getString("CPF")));
					}
				}else if(tipoPessoa.equals("Juridica"))
				{
					psSelectJuridica.setLong(1, id);
					ResultSet rsFisica = psSelectJuridica.executeQuery();
					
					if(rsFisica.next())
					{
						return recuperada = new Pessoa_Juridica(id, nome, new CNPJ(rsFisica.getString("CNPJ")));
					}
				}
			}else
			{
				throw new RuntimeException("Pessoa nao encontrada!");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return recuperada;
	}
}
