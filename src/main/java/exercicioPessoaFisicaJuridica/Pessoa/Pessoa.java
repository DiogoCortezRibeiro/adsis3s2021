package exercicioPessoaFisicaJuridica.Pessoa;

public abstract class Pessoa {
	private Long id_pessoa;
	private String nome;
	
	public Pessoa(Long id_pessoa, String nome)
	{
		this.id_pessoa = id_pessoa;
		this.nome      = nome;
	}
	
	public Long getId_pessoa() {
		return id_pessoa;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
