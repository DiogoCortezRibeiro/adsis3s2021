package aepSegundoBimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class TurmaPersistent extends Conexao implements TurmaRepository {
	private Connection conexao;
	
	public TurmaPersistent()
	{
		this.conexao = this.abrirConexao();
	}
	
	@Override
	public void salvar(Turma novaTurma, String id_turma, String id_curso, String id_cronograma) 
	{
		try
		{
			PreparedStatement psSalvarTurma = conexao.prepareStatement("INSERT INTO turma(id_turma, nome, periodo, id_curso, id_cronograma)VALUES(?, ?, ?, ?, ?)");
			psSalvarTurma.setString(1, id_turma);
			psSalvarTurma.setString(2, novaTurma.getNome());
			psSalvarTurma.setString(3, novaTurma.getPeriodo());
			psSalvarTurma.setString(4, id_curso);
			psSalvarTurma.setString(5, id_cronograma);
			psSalvarTurma.execute();
			psSalvarTurma.close();
			
			PreparedStatement psSalvarCronogramaTurma = conexao.prepareStatement("INSERT INTO turma_cronograma(id_cronograma_turma, id_turma, id_cronograma)VALUES(?, ?, ?)");
			psSalvarCronogramaTurma.setString(1, UUID.randomUUID().toString());
			psSalvarCronogramaTurma.setString(2, id_turma);
			psSalvarCronogramaTurma.setString(3, id_cronograma);
			psSalvarCronogramaTurma.execute();
			psSalvarCronogramaTurma.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Turma getTurmaById(String id_turma)
	{
		Turma turmaVazia = new Turma();
		try {
			PreparedStatement psSelect = this.conexao.prepareStatement(
					"select nome, periodo from turma where id_turma = ?");
			psSelect.setString(1, id_turma);
			ResultSet rsTodas = psSelect.executeQuery();
			while (rsTodas.next()) {
				Turma turmaPreenchida = new Turma(rsTodas.getString("nome"), rsTodas.getString("periodo"));
				return turmaPreenchida;
			}
			psSelect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turmaVazia;
	}

}
