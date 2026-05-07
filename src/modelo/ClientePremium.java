package modelo;
import java.sql.Date;

public class ClientePremium extends Cliente {
	private Date fechaCaducidad;

	public ClientePremium(String nombre, String apellido, String Idioma, 
			String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro, Date fechaCaducidad) {
		
		super(nombre, apellido, Idioma, usuario, contraseña, fechaNacimiento, fechaRegistro);
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
}