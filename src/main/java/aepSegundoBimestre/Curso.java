package aepSegundoBimestre;

public class Curso {
	private Nome nome;
	private Integer totalAula;
	private Double cargaHoraria;
	private String cordenador;
	private String nomeBanco;
	private String cordenadorBanco;
	
	public Curso() {}
	
	public Curso(Nome nome, Integer totalAula, Double cargaHoraria, String cordenador) {
		this.nome         = nome;
		this.totalAula    = totalAula;
		this.cargaHoraria = cargaHoraria;
		this.cordenador   = cordenador;
	}

	public Curso(String nomeCurso, Integer totalAula, String cordenador) {
		this.nomeBanco       = nomeCurso;
		this.cordenadorBanco = cordenador;
		this.totalAula       = totalAula;
	}

	public String getNome() {
		return nome.getValor();
	}

	public Integer getTotalAula() {
		return totalAula;
	}

	public Double getCargaHoraria() {
		return cargaHoraria;
	}
	
	public String getCordenador() {
		return cordenador;
	}
	
	public String getNomeBanco() {
		return nomeBanco;
	}
	
	public String getCordenadorBanco() {
		return cordenadorBanco;
	}
}
