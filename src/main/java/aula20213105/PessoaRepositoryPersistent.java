package aula20213105;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aepSegundoBimestre.Conexao;

public class PessoaRepositoryPersistent extends Conexao implements PessoaRepository {
	private Connection conexão;
	
	public PessoaRepositoryPersistent() {
		conexão = abrirConexao();
	}

	@Override
	public void salvar(Pessoa pessoa) {
		try {
			PreparedStatement psSalvar = conexão.prepareStatement("INSERT INTO PESSOA(nome, idade)VALUES(?, ?)");
			psSalvar.setString(1, pessoa.getNome());
			psSalvar.setInt(2, pessoa.getIdade());
			psSalvar.execute();
			psSalvar.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer id) {
		try {
			PreparedStatement psDelete = conexão.prepareStatement("DELETE FROM PESSOA WHERE id = ?");
			psDelete.setInt(1, id);
			psDelete.execute();
			psDelete.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Pessoa pessoa, int id) {
		try {
			PreparedStatement psUpdate = conexão.prepareStatement("UPDATE PESSOA SET NOME = ? , IDADE = ? WHERE id = ?");
			psUpdate.setString(1, pessoa.getNome());
			psUpdate.setInt(2, pessoa.getIdade());
			psUpdate.setInt(3, id);
			psUpdate.execute();
			psUpdate.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Pessoa> getAll() {
		List<Pessoa> pessoas = new ArrayList<>();
		try {
			PreparedStatement psGetAll = conexão.prepareStatement("SELECT nome, idade FROM PESSOA");
			ResultSet rsTodas = psGetAll.executeQuery();
			
			while(rsTodas.next()) {
				Pessoa recuperada = new Pessoa(rsTodas.getString("nome"),rsTodas.getInt("idade"));
				pessoas.add(recuperada);
			}
			psGetAll.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
}
