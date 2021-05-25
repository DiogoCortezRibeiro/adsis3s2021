package aula20212405;

public interface PessoaRepository {
	void salvar(Pessoa pessoa);
	
	void excluir(Pessoa pessoa);
	
	void atualizar(Pessoa pessoa);
	
	void getAll(Pessoa pessoa);
	
}
