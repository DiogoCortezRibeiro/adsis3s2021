package aepSegundoBimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CursoPersistent extends Conexao implements CursoRepository{
	private Connection conexao;
	
	public CursoPersistent()
	{
		this.conexao = this.abrirConexao();
	}
	
	@Override
	public void salvar(Curso novoCurso, String id_cruso, String id_conteudo) {
		try
		{
			PreparedStatement psSalvarAluno = conexao.prepareStatement("INSERT INTO curso(id_curso, nome, totalAula, cargaHoraria, id_conteudo, cordenador)VALUES(?, ?, ?, ?, ?, ?)");
			psSalvarAluno.setString(1, id_cruso);
			psSalvarAluno.setString(2, novoCurso.getNome());
			psSalvarAluno.setInt(3, novoCurso.getTotalAula());
			psSalvarAluno.setDouble(4, novoCurso.getCargaHoraria());
			psSalvarAluno.setString(5, id_conteudo);
			psSalvarAluno.setString(6, novoCurso.getCordenador());
			psSalvarAluno.execute();
			psSalvarAluno.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Curso getCursoById(String id_curso)
	{
		Curso cursoVazio = new Curso();
		try {
			PreparedStatement psSelect = this.conexao.prepareStatement(
					"select nome, totalAula, cordenador from curso where id_curso = ?");
			psSelect.setString(1, id_curso);
			ResultSet rsTodas = psSelect.executeQuery();
			while (rsTodas.next()) {
				Curso cursoPreenchido = new Curso(rsTodas.getString("nome"), rsTodas.getInt("totalAula"), rsTodas.getString("cordenador"));
				return cursoPreenchido;
			}
			psSelect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursoVazio;
	}
}
