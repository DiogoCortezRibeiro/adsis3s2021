package aula20213105;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.UUID;

import javax.swing.JOptionPane;

public class AppTransacoes {
	public static void main(String[] args) {
		try 
		{
			Connection conexao = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/transacoes", "sa", "");
			conexao.setAutoCommit(false); // TRANSAÇÃO FICA MANUAL A APRTIR DAQUI
			
			PreparedStatement psCreate = conexao.prepareStatement("create table if not exists LIVRO("
					+ " ID CHAR(36) PRIMARY KEY,"
					+ " TITULO VARCHAR(200) NOT NULL,"
					+ " AINDAPUBLICADO BOOLEAN NOT NULL,"
					+ " PRECO NUMERIC(15,2) NOT NULL,"
					+ " PUBLICADO DATE NOT NULL,"
					+ " QTDPAGINAS NUMERIC(15,2)"
					+ ")");

			psCreate.execute();
			psCreate.close();
			conexao.commit();
			for(int i = 0; i < 1000; i++) {
				inserirUmLivro(conexao,
						UUID.randomUUID().toString(),
						"Java Como Programar" + System.nanoTime(),
						true,
						125.77,
						new Date(),
						120);
			}
			//JOptionPane.showMessageDialog(null, "Opa, mil inserts realizados mais ainda sem commit");
			int opcao = JOptionPane.showConfirmDialog(null, "Deseja confirmar a transação?","Confime", JOptionPane.YES_NO_OPTION);
			
			if(opcao == JOptionPane.YES_OPTION)
			{
				conexao.commit();
				System.out.println("Commit efetuado!");
			}else if(opcao == JOptionPane.NO_OPTION)
			{
				conexao.rollback();
				System.out.println("Rollback efetuado!");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void inserirUmLivro(Connection conexao, String id, String titulo, boolean publicado, double valor, Date dataPublicacao, int qtdePagina) {
		try
		{
			PreparedStatement psInsert = conexao.prepareStatement("INSERT INTO LIVRO"
					+ "(ID, TITULO, AINDAPUBLICADO, PRECO, PUBLICADO, QTDPAGINAS)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?, ?)");
			psInsert.setString(1, id);
			psInsert.setString(2, titulo);
			psInsert.setBoolean(3, publicado);
			psInsert.setDouble(4, valor);
			psInsert.setDate(5,new java.sql.Date(dataPublicacao.getTime()));
			psInsert.setInt(6, qtdePagina);
			psInsert.execute();
			psInsert.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
