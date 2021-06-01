package aula20213105;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conexao {
	private Connection conexão;
	
	public Connection abrirConexão() {
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
			PreparedStatement psCreateTable = conexão.prepareStatement("create table if not exists cor ("
					+ "nome varchar(255) not null, "
					+ "sigla varchar(30) not null, "
					+ "primary key(sigla)"
					+ ")");
			psCreateTable.execute();
			psCreateTable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexão;
	}
}
