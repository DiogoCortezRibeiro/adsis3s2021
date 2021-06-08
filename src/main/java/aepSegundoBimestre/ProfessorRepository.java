package aepSegundoBimestre;

import java.util.List;

public interface ProfessorRepository {
	void salvar(Professor novoProfessor);
	
	List<Professor> getAll();

}
