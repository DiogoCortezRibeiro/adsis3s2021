package exercicioPessoaFisicaJuridica.Pessoa;

import exercicioPessoaFisicaJuridica.ValueObject.CPF;

public class Pessoa_Fisica extends Pessoa {
	private CPF cpf;
	
	public Pessoa_Fisica(Long id_pessoa, String nome, CPF cpf) {
		super(id_pessoa, nome);
		this.cpf = cpf;
	}
	
	public CPF getCpf() {
		return cpf;
	}
	
	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}
}
