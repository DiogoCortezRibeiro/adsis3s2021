package aepSegundoBimestre;

public class Turma {
	private Aluno aluno;
	private Nome nome;
	private String periodo;
	private String nomeBanco;
	
	public Turma() {}
	
	public Turma(Nome nome, String periodo) {
		this.nome       = nome;
		this.periodo    = periodo;
	}
	
	public Turma(String nome,  String periodo)
	{
		this.nomeBanco = nome;
		this.periodo   = periodo;
	}
	
	public void setAluno(Aluno aluno)
	{
		this.aluno = aluno;
	}

	public Aluno getAluno() 
	{
		return aluno;
	}

	public String getNome() 
	{
		return nome.getValor();
	}

	public String getPeriodo() 
	{
		return periodo;
	}
	
	public String getNomeBanco()
	{
		return this.nomeBanco;
	}
}
