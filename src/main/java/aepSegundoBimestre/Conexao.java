package aepSegundoBimestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conexao {
	private Connection conn;
	
	public Connection abrirConexao() {
		try {
			try {
				conn = DriverManager.getConnection("jdbc:h2:~/testeBanco10","sa","");
				conn.close();
			} catch (Exception e) {
				System.out.println("Opa, acho que o banco já estava criado...");
			}
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/testeBanco10","sa","");		
			PreparedStatement psCreateTableProfessor = conn.prepareStatement("create table if not exists professor ("
					+ "id_professor char(65) primary key,"
					+ "nome varchar(55) not null, "
					+ "email varchar(75) not null, "
					+ "matricula varchar(25) unique"
					+ ")");
			psCreateTableProfessor.execute();
			psCreateTableProfessor.close();
			
			PreparedStatement psCreateTableEspecialidadesProfessor = conn.prepareStatement("create table if not exists especialidades_professor ("
					+ "id_especialidade int primary key AUTO_INCREMENT,"
					+ "id_professor char(65) references professor(id_professor),"
					+ "especialidade varchar(100) not null"
					+ ")");
			psCreateTableEspecialidadesProfessor.execute();
			psCreateTableEspecialidadesProfessor.close();
			
			PreparedStatement psCreateTableConteudo = conn.prepareStatement("create table if not exists conteudo ("
					+ "id_conteudo char(65) primary key,"
					+ "descricao varchar(65) not null,"
					+ "cargaHoraria numeric(8,2) not null"
					+ ")");
			psCreateTableConteudo.execute();
			psCreateTableConteudo.close();
			
			PreparedStatement psCreateTableCurso = conn.prepareStatement("create table if not exists curso ("
					+ "id_curso char(65) primary key,"
					+ "nome varchar(65) not null,"
					+ "totalAula int not null,"
					+ "cargaHoraria numeric(8,2) not null,"
					+ "id_conteudo char(65) references conteudo(id_conteudo),"
					+ "cordenador varchar(65) not null"
					+ ")");
			psCreateTableCurso.execute();
			psCreateTableCurso.close();
			
			PreparedStatement psCreateTableCronograma = conn.prepareStatement("create table if not exists cronograma ("
					+ "id_cronograma char(65) primary key,"
					+ "dataInicio varchar(65) not null,"
					+ "dataFim varchar(65) not null,"
					+ "id_turma char(65)"
					+ ")");
			psCreateTableCronograma.execute();
			psCreateTableCronograma.close();
			
			PreparedStatement psCreateTableTurma = conn.prepareStatement("create table if not exists turma ("
					+ "id_turma char(65) primary key,"
					+ "nome varchar(65) not null,"
					+ "periodo varchar(65) not null,"
					+ "id_curso char(65) references curso(id_curso),"
					+ "id_cronograma char(65) references cronograma(id_cronograma)"
					+ ")");
			psCreateTableTurma.execute();
			psCreateTableTurma.close();
			
			PreparedStatement psCreateTableTurmaCronograma = conn.prepareStatement("create table if not exists turma_cronograma( "
					+ "id_cronograma_turma char(65) primary key,"
					+ "id_turma char(65) references turma(id_turma),"
					+ "id_cronograma char(65) references cronograma(id_cronograma)"
					+ ")");
			psCreateTableTurmaCronograma.execute();
			psCreateTableTurmaCronograma.close();
			
			PreparedStatement psCreateTableAluno = conn.prepareStatement("create table if not exists aluno ("
					+ "id_aluno char(65) primary key,"
					+ "nome varchar(65) not null,"
					+ "notaBimestreUm numeric(4, 2) not null,"
					+ "notaBimestreDois numeric(4, 2) not null,"
					+ "matricula varchar(65) not null,"
					+ "email varchar(65) not null,"
					+ "id_turma char(65) references turma(id_turma),"
					+ "quantidadeFalta int,"
					+ "anotacao varchar(65)"
					+ ")");
			psCreateTableAluno.execute();
			psCreateTableAluno.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
