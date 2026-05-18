package modelo;

public class Podcast {
	
	private String IdAudio;
	private int NColaboradores;
	private String IdPodcaster;
	
	public Podcast(String idAudio, int nColaboradores, String idPodcaster) {
		super();
		IdAudio = idAudio;
		NColaboradores = nColaboradores;
		IdPodcaster = idPodcaster;
	}

	public String getIdAudio() {
		return IdAudio;
	}

	public void setIdAudio(String idAudio) {
		IdAudio = idAudio;
	}

	public int getNColaboradores() {
		return NColaboradores;
	}

	public void setNColaboradores(int nColaboradores) {
		NColaboradores = nColaboradores;
	}

	public String getIdPodcaster() {
		return IdPodcaster;
	}

	public void setIdPodcaster(String idPodcaster) {
		IdPodcaster = idPodcaster;
	}
	
	

}
