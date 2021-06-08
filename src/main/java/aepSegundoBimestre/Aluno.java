package aepSegundoBimestre;

public class Aluno {
	private Nome nome;
	private Double[] nota = new Double[2];
	private Matricula matricula;
	private Email email;
	private Integer totalFalta;
	private String anotacao;
	private String nomeBanco;
	private String matriculaBanco;
	
	public Aluno(Nome nome, Matricula matricula, Email email, Integer totalFalta, String anotacao) 
	{
		this.nome       = nome;
		this.matricula  = matricula;
		this.email      = email;
		this.totalFalta = totalFalta;
		this.anotacao   = anotacao;
	}
	
	public Aluno(String nome, Double nota1, Double nota2, String matricula, Integer totalFalta) 
	{
		this.nomeBanco  = nome;
		this.nota[0]    = nota1;
		this.nota[1]    = nota2;
		this.totalFalta = totalFalta;
		this.matriculaBanco  = matricula;
	}

	public void adicionarNota(Double nota, int posicao)
	{
		this.nota[posicao] = nota;
	}
	
	public String getNome() 
	{
		return nome.getValor();
	}

	public Double getNota(int posicao) 
	{
		return nota[posicao];
	}

	public String getMatricula() 
	{
		return matricula.getMatricula();
	}

	public String getEmail() 
	{
		return email.getEmail();
	}

	public Integer getTotalFalta() 
	{
		return totalFalta;
	}

	public String getAnotacao() 
	{
		return anotacao;
	}
	
	public String getNomeBanco() {
		return nomeBanco;
	}
	
	public String getMatriculaBanco() {
		return matriculaBanco;
	}
}
