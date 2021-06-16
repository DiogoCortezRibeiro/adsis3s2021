package associacaoAutorLivro;

import java.util.List;

public interface PessoaRepository {
	void salvar(Pessoa pessoa);
	
	void excluir(Integer id);
	
	void atualizar(Pessoa pessoa, int id);
	
	List<Pessoa> getAll();
}
