package modelo;

import java.util.Date;

public class PlayList {
	
	private  int IdList;
	private String Titulo;
	private Date FechaCreacion;
	private String IdCliente;
	
	
	public PlayList(int idList, String titulo, Date fechaCreacion, String idCliente) {
		IdList = idList;
		Titulo = titulo;
		FechaCreacion = fechaCreacion;
		IdCliente = idCliente;
	}


	public int getIdList() {
		return IdList;
	}


	public void setIdList(int idList) {
		IdList = idList;
	}


	public String getTitulo() {
		return Titulo;
	}


	public void setTitulo(String titulo) {
		Titulo = titulo;
	}


	public Date getFechaCreacion() {
		return FechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}


	public String getIdCliente() {
		return IdCliente;
	}


	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}
	
	
	
	
	

}
