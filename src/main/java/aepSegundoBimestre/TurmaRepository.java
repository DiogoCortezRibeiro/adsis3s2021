package aepSegundoBimestre;

public interface TurmaRepository {
	void salvar(Turma novaTurma,String id_turma, String id_curso, String id_cronograma);
	public Turma getTurmaById(String id_turma);
}
