package aepSegundoBimestre;

public class Email {
	private String email;
	
	public Email(String email)
	{
		boolean emailValido = this.isValidEmailAddress(email);
		if(emailValido)
		{
			this.setEmail(email);
		}else
		{
			throw new RuntimeException("E-mail inválido, por favor informe novamente");
		}
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	private void setEmail(String email)
	{
		this.email = email;
	}
	
	public boolean isValidEmailAddress(String email) {
	     String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	     java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	     java.util.regex.Matcher m = p.matcher(email);
	     return m.matches();
	}
}
