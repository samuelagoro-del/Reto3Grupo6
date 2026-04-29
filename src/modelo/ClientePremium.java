package modelo;
import java.sql.Date;

public class ClientePremium extends Cliente {
	private Date fechaCaducidad;

	public ClientePremium(String idCliente, String nombre, String apellido, String idIdioma, 
			String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro, Date fechaCaducidad) {
		
		super(idCliente, nombre, apellido, idIdioma, usuario, contraseña, fechaNacimiento, fechaRegistro);
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
}