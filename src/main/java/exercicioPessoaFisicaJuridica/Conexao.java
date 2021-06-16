package exercicioPessoaFisicaJuridica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conexao {
	private Connection conexao;
	
	public Connection abrirConexao() {
		try {
			try {
				conexao = DriverManager.getConnection("jdbc:h2:~/juridicaFisica","sa","");
				conexao.close();
			} catch (Exception e) {
				System.out.println("Opa, acho que o banco já estava criado...");
			}
			conexao = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/juridicaFisica","sa","");	
			
			PreparedStatement psCreateTablePessoa = conexao.prepareStatement("create table if not exists pessoa ("
					+ "id numeric(18) not null primary key, "
					+ "nome varchar(255) not null, "
					+ "tipo_pessoa varchar(50) not null"
					+ ")");
			psCreateTablePessoa.execute();
			psCreateTablePessoa.close();
			
			PreparedStatement psCreateTablePessoaFisica = conexao.prepareStatement("create table if not exists pessoa_fisica ("
					+ "id numeric(18) not null primary key references pessoa(id), "
					+ "cpf varchar(50) not null"
					+ ")");
			psCreateTablePessoaFisica.execute();
			psCreateTablePessoaFisica.close();
			
			PreparedStatement psCreateTablePessoaJuridica = conexao.prepareStatement("create table if not exists pessoa_juridica ("
					+ "id numeric(18) not null primary key references pessoa(id), "
					+ "cnpj varchar(50) not null "
					+ ")");
			psCreateTablePessoaJuridica.execute();
			psCreateTablePessoaJuridica.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
