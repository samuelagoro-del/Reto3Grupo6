package modelo;
import java.time.Year;
public class Album  {
	
	private String IdAlbum;
	private String Titulo;
	private  Year Año;
  private String Genero;
  private String Imagen;
  private String idMusico;
  
 
  public Album(String idAlbum, String titulo, Year año, String genero, String imagen, String idMusico) {
	IdAlbum = idAlbum;
	Titulo = titulo;
	Año = año;
	Genero = genero;
	Imagen = imagen;
	this.idMusico = idMusico;
	
	
  }
  public String getIdAlbum() {
	return IdAlbum;
  }
  public void setIdAlbum(String idAlbum) {
	IdAlbum = idAlbum;
  }
  public String getTitulo() {
	return Titulo;
  }
  public void setTitulo(String titulo) {
	Titulo = titulo;
  }
  public Year getAño() {
	return Año;
  }
  public void setAño(Year año) {
	Año = año;
  }
  public String getGenero() {
	return Genero;
  }
  public void setGenero(String genero) {
	Genero = genero;
  }
  public String getImagen() {
	return Imagen;
  }
  public void setImagen(String imagen) {
	Imagen = imagen;
  }
  public String getIdMusico() {
	return idMusico;
  }
  public void setIdMusico(String idMusico) {
	this.idMusico = idMusico;
  }
	
}