package aepSegundoBimestre;

public class Nome {
	private String valor;
	
	public Nome(String valor) {
		setValor(valor);
	}
	
	public String getValor() {
		return valor;
	}
	
	private void setValor(String valor) {
		if (valor == null || valor.trim().length() == 0 || valor.length() < 2) {
			throw new RuntimeException("Nome não pode ser nulo!");
		}		
		this.valor = valor;
	}
}
