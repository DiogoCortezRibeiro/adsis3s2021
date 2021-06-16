package exercicioPessoaFisicaJuridica;

import java.sql.SQLException;

import exercicioPessoaFisicaJuridica.Pessoa.Pessoa;
import exercicioPessoaFisicaJuridica.Pessoa.PessoaRepository;
import exercicioPessoaFisicaJuridica.Pessoa.Pessoa_Fisica;
import exercicioPessoaFisicaJuridica.Pessoa.Pessoa_Juridica;
import exercicioPessoaFisicaJuridica.ValueObject.CNPJ;
import exercicioPessoaFisicaJuridica.ValueObject.CPF;

public class AppTestePessoa {
	public static void main(String[] args) throws Exception {
		Pessoa_Fisica p1 = new Pessoa_Fisica((long) 10, "DIOGO TESTE", new CPF("10215189"));
		Pessoa_Fisica p2 = new Pessoa_Fisica((long) 14, "THALITA TESTE", new CPF("10215189"));
		Pessoa_Juridica p3 = new Pessoa_Juridica((long) 12, "THAIS TESTE", new CNPJ("10215189"));
		
		PessoaRepository repo = new PessoaRepository();
		//repo.incluir(p1);
		
		//repo.incluir(p2);
		
		/*repo.incluir(p2);*/
		
		/*repo.excluir(p1, (long) 10);*/
		
		/*p2.setNome("Testando Update");
		p2.setCpf(new CPF("10010011141"));
		
		p3.setNome("Testando nome juridica");
		
		repo.update(p2, (long) 14);
		//repo.update(p3, (long) 12);*/
		
		//repo.excluirPeloId((long)11);
		
		Pessoa recuperada = repo.recuperarPeloId((long) 14);
		
		System.out.println(recuperada.getNome() + " - " + recuperada.getClass().getSimpleName());
		
	}
}
