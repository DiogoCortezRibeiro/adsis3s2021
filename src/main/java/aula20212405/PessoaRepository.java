package aula20212405;

import java.util.List;

public interface PessoaRepository {
	void salvar(Pessoa pessoa);
	
	void excluir(Integer id);
	
	void atualizar(Pessoa pessoa, Integer id);
	
	List<Pessoa> getAll();
}
