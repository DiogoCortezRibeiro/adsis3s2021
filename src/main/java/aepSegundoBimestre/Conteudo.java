package aepSegundoBimestre;

public class Conteudo {
	private String descricao;
	private Double cargaHoraria;
	
	public Conteudo(String descricao, Double cargaHoraria) {
		this.descricao = descricao;
		this.cargaHoraria = cargaHoraria;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getCargaHoraria() {
		return cargaHoraria;
	}
}
