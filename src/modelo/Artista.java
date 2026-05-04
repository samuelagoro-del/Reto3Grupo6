package modelo;
public class Artista {
	private String IdArtista;
	private String NombreArtistico;
	private String Genero;
	private String Imagen;
	private String Descripcion;
	
	
	public Artista(String idArtista, String nombreArtistico, String genero, String imagen, String descripcion) {
		super();
		IdArtista = idArtista;
		NombreArtistico = nombreArtistico;
		Genero = genero;
		Imagen = imagen;
		Descripcion = descripcion;
		
	}
	public String getIdArtista() {
		return IdArtista;
	}
	public String getNombreArtistico() {
		return NombreArtistico;
	}
	public String getGenero() {
		return Genero;
	}
	public String getImagen() {
		return Imagen;
	}
	public String getDescripcion() {
		return Descripcion;
	}
		
}