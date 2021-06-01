package aula20213105;

import java.util.List;

public interface CorRepository {
	
	void salvar(Cor nova);
	
	void atualizar(Cor nova);

	void excluir(Cor cor);
	
	List<Cor> obterTodas();

}
