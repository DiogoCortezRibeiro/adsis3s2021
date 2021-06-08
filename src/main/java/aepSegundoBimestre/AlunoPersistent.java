package aepSegundoBimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlunoPersistent extends Conexao implements AlunoRepository {
	private Connection conexao;
	
	public AlunoPersistent()
	{
		this.conexao = this.abrirConexao();
	}
	@Override
	public void salvar(Aluno novoAluno, String id_turma) {
		try
		{
			PreparedStatement psSalvarAluno = conexao.prepareStatement("INSERT INTO aluno(id_aluno, nome, notaBimestreUm, notaBimestreDois, matricula, email, id_turma, quantidadeFalta, anotacao)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			psSalvarAluno.setString(1, UUID.randomUUID().toString());
			psSalvarAluno.setString(2, novoAluno.getNome());
			psSalvarAluno.setDouble(3, novoAluno.getNota(0));
			psSalvarAluno.setDouble(4, novoAluno.getNota(1));
			psSalvarAluno.setString(5, novoAluno.getMatricula());
			psSalvarAluno.setString(6, novoAluno.getEmail());
			psSalvarAluno.setString(7, id_turma);
			psSalvarAluno.setInt(8, novoAluno.getTotalFalta());
			psSalvarAluno.setString(9, novoAluno.getAnotacao());
			psSalvarAluno.execute();
			psSalvarAluno.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Aluno> getAlunosByTurma(String id_turma) {
		List<Aluno> alunos = new ArrayList<>();
		try {
			PreparedStatement psSelect = this.conexao.prepareStatement(
					"select nome, notaBimestreUm, notaBimestreDois, matricula, quantidadeFalta from aluno where id_turma = ?");
			psSelect.setString(1, id_turma);
			ResultSet rsTodas = psSelect.executeQuery();
			while (rsTodas.next()) {
				Aluno alunoRecuperado = new Aluno(
						rsTodas.getString("nome"), 
						rsTodas.getDouble("notaBimestreUm"),
						rsTodas.getDouble("notaBimestreDois"),
						rsTodas.getString("matricula"),
						rsTodas.getInt("quantidadeFalta")
						);
				alunos.add(alunoRecuperado);
			}
			psSelect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alunos;
	}

}
