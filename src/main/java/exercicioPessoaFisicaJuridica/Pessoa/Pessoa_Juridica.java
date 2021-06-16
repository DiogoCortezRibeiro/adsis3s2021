package exercicioPessoaFisicaJuridica.Pessoa;

import exercicioPessoaFisicaJuridica.ValueObject.CNPJ;

public class Pessoa_Juridica extends Pessoa {
	private CNPJ cnpj;
	
	public Pessoa_Juridica(Long id_pessoa, String nome, CNPJ cnpj) {
		super(id_pessoa, nome);
		this.cnpj = cnpj;
	}
	
	public CNPJ getCnpj() {
		return cnpj;
	}
}
