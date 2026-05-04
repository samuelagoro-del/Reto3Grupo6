package modelo;
public class Audio {
	
	private String IdAudio;
	private String Nombre;
	private int Duracion;
	private int NReproducciones;
	private String Archivo;
	private String Tipo ; // debe ser uno de estos 'podcast','cancion'
	
	
	public Audio(String idAudio, String nombre, int duracion, int nReproducciones, String archivo, String tipo) {
		IdAudio = idAudio;
		Nombre = nombre;
		Duracion = duracion;
		NReproducciones = nReproducciones;
		Archivo = archivo;
		Tipo = tipo;
	}
	public String getIdAudio() {
		return IdAudio;
	}
	public void setIdAudio(String idAudio) {
		IdAudio = idAudio;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getDuracion() {
		return Duracion;
	}
	public void setDuracion(int duracion) {
		Duracion = duracion;
	}
	public int getNReproducciones() {
		return NReproducciones;
	}
	public void setNReproducciones(int nReproducciones) {
		NReproducciones = nReproducciones;
	}
	public String getArchivo() {
		return Archivo;
	}
	public void setArchivo(String archivo) {
		Archivo = archivo;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	
	
}

