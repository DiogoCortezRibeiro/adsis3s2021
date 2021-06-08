package aepSegundoBimestre;

public class Matricula {
	private String matricula;
	
	public Matricula(String matricula)
	{
		this.setMatricula(matricula);
	}
	
	public String getMatricula()
	{
		return this.matricula;
	}
	
	private void setMatricula(String matricula) {
		if (matricula == null || matricula.trim().length() == 0 || matricula.length() < 5) {
			throw new RuntimeException("Matricula inválida, por favor informe novamente");
		}		
		this.matricula = matricula;
	}
}
