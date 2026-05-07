package modelo;

import java.time.Year;

public class Album  {

	
	private String IdAlbum;
	private String Titulo;
	private  int Año;
   private String Genero;
   private String Imagen;
   private int idMusico;
  
   
   
   public Album(String idAlbum, String titulo, int  año, String genero, String imagen, int idMusico) {
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



   public int getAño() {
	return Año;
   }



   public void setAño(	int año) {
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



   public int getIdMusico() {
	return idMusico;
   }



   public void setIdMusico(int idMusico) {
	this.idMusico = idMusico;
   }



   @Override
   public String toString() {
	return Titulo + "-" + Año + "-" + idMusico ;
   }
   
   
   
   
   
   
   
   
   
   
   
   
	

}
