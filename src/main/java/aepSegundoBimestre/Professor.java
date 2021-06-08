package aepSegundoBimestre;

import java.util.ArrayList;
import java.util.List;

public class Professor {
	private Nome nome;
	private Email email;
	private Matricula matricula;
	private List<String> especializacoes = new ArrayList<>();
	
	public Professor(Nome nome, Email email, Matricula matricula)
	{
		this.nome      = nome;
		this.email     = email;
		this.matricula = matricula;
	}

	public void adicionarEspecialidade(String especialidade)
	{
		this.especializacoes.add(especialidade);
	}
	
	public String getNome()
	{
		return this.nome.getValor();
	}
	
	public String getEmail()
	{
		return this.email.getEmail();
	}
	
	public String getMatricula()
	{
		return this.matricula.getMatricula();
	}
	
	public List<String> getEspecializacoes()
	{
		return this.especializacoes;
	}

	public void apresentarProfessor() {
		System.out.println("Nome: " + this.nome.getValor());
		System.out.println("E-mail: " + this.email.getEmail());
		System.out.println("Matricula: " + this.matricula.getMatricula());
		if(this.especializacoes.size() > 0)
		{
			for(String espcializacao : this.especializacoes)
			{
				System.out.println("Especialização: " + espcializacao);
			}
		}else
		{
			System.out.println("Professor não possui especializações");
		}
	}
}
