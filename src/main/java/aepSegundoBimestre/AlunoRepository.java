package aepSegundoBimestre;

import java.util.List;

public interface AlunoRepository {
	void salvar(Aluno novoAluno, String id_turma);
	
	List<Aluno> getAlunosByTurma(String id_turma);
}
