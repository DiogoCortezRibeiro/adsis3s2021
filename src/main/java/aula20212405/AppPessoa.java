package aula20212405;

import java.util.Scanner;

public class AppPessoa {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PessoaRepository repo = new PessoaRepositoryPersistent();
		
		System.out.print("Informe um id: ");
		int id = sc.nextInt();
		
		Pessoa p1 = new Pessoa("Thalita Martins", 22);
		
		repo.atualizar(p1, id);
		
		System.out.println("Passou");
		
	}
}
