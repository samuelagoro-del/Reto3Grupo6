package modelo;

public class Musico {
	
	private String IdMusico;
	private String Caracteristica; // tiene que ser uno de estos --> 'solista','grupo'
	
	
	public Musico(String idMusico, String caracteristica) {
		IdMusico = idMusico;
		Caracteristica = caracteristica;
	}


	


	




	public String getIdMusico() {
		return IdMusico;
	}


	public void setIdMusico(String idMusico) {
		IdMusico = idMusico;
	}


	public String getCaracteristica() {
		return Caracteristica;
	}


	public void setCaracteristica(String caracteristica) {
		Caracteristica = caracteristica;
	}
	
	

}
