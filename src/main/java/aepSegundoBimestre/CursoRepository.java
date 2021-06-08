package aepSegundoBimestre;

public interface CursoRepository {
	void salvar(Curso novoCurso, String id_cruso, String id_curso);
	Curso getCursoById(String id_curso);
}
