package aula20212405;

public class AppPessoa {
	public static void main(String[] args) {
		Pessoa p1 = new Pessoa("Thalita", 25);
		
		PessoaRepository repo = new PessoaRepositoryPersistent();
		
		p1.setIdade(30);
		
		repo.atualizar(p1, 3);
	}
}
