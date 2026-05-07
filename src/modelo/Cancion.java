package modelo;

public class Cancion {

	private String IdCancion;
	private String IdAlbum;
	private String ArtistasInvitados;

	
	public Cancion(String idCancion, String idAlbum, String artistasInvitados) {
		super();
		IdCancion = idCancion;
		IdAlbum = idAlbum;
		ArtistasInvitados = artistasInvitados;
	}


	public String getIdCancion() {
		return IdCancion;
	}


	public void setIdCancion(String idCancion) {
		IdCancion = idCancion;
	}


	public String getIdAlbum() {
		return IdAlbum;
	}


	public void setIdAlbum(String idAlbum) {
		IdAlbum = idAlbum;
	}


	public String getArtistasInvitados() {
		return ArtistasInvitados;
	}


	public void setArtistasInvitados(String artistasInvitados) {
		ArtistasInvitados = artistasInvitados;
	}
	
	








}
