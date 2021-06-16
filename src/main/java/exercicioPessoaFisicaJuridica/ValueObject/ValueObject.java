package exercicioPessoaFisicaJuridica.ValueObject;

public abstract class ValueObject {
	private String valor;
	
	public ValueObject(String valor)
	{
		if(valor.length() > 5)
		{
			this.valor = valor;
		}else
		{
			throw new RuntimeException("Quantiade invalida de caracteres");
		}
	}
	
	public String getValor() {
		return valor;
	}
}
