package aepSegundoBimestre;

public class Cronograma {
	private String dataInicio;
	private String dataFim;
	
	public Cronograma(String dataInicio, String dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim    = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}
}
