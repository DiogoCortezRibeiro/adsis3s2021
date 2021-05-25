package aula20212405;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepositoryPersistent implements PessoaRepository {
	private Connection conexão;
	
	public PessoaRepositoryPersistent() {
		abrirConexão();
	}
	
	private void abrirConexão() {
		try {
			try {
				conexão = DriverManager.getConnection("jdbc:h2:~/adsis3s2021","sa","");
				conexão.close();
			} catch (Exception e) {
				System.out.println("Opa, acho que o banco já estava criado...");
			}
			conexão = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/adsis3s2021","sa","");			
			//conexão = DriverManager.getConnection("jdbc:postgresql://localhost:5432/adsis3s2021","sa","");
			//conexão = DriverManager.getConnection("jdbc:oracle://localhost:1521/adsis3s2021","sa","");
			PreparedStatement psCreateTable = conexão.prepareStatement("create table if not exists Pessoa ("
					+ "id number AUTO_INCREMENT, "
					+ "nome varchar(255) not null, "
					+ "idade number(02) not null, "
					+ "primary key(id)"
					+ ")");
			psCreateTable.execute();
			psCreateTable.close();
			System.out.println("Foi.");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void atualizar(Pessoa pessoa, Integer id) {
		try {
			PreparedStatement psUpdate = conexão.prepareStatement("UPDATE PESSOA SET nome = ? idade = ? WHERE id = ?");
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
