package aepSegundoBimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CronogramaPersistent extends Conexao implements CronogramaRepository {
	private Connection conexao;

	public CronogramaPersistent()
	{
		this.conexao = this.abrirConexao();
	}

	@Override
	public void salvar(Cronograma novoCronograma, String id_turma, String id_cronograma) {
		try {
			PreparedStatement psSalvarAluno = conexao.prepareStatement(
					"INSERT INTO cronograma(id_cronograma, dataInicio, dataFim, id_turma)VALUES(?, ?, ?, ?)");
			psSalvarAluno.setString(1, id_cronograma);
			psSalvarAluno.setString(2, novoCronograma.getDataInicio());
			psSalvarAluno.setString(3, novoCronograma.getDataFim());
			psSalvarAluno.setString(4, id_turma);
			psSalvarAluno.execute();
			psSalvarAluno.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
