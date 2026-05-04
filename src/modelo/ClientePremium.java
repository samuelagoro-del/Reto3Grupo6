package modelo;
import java.sql.Date;

/**
 * Clase que representa a un cliente con suscripción Premium.
 * Hereda de la clase {@link Cliente} y añade información específica 
 * sobre la validez de su cuenta de pago (fecha de caducidad).
 */
public class ClientePremium extends Cliente {
	
	/** * Fecha en la que caduca la suscripción premium del cliente. 
	 */
	private Date fechaCaducidad;

	/**
	 * Constructor de la clase ClientePremium.
	 * Recibe los datos básicos del cliente para inicializar la clase padre y 
	 * establece la fecha de caducidad específica de la suscripción premium.
	 * * @param nombre El nombre real del cliente.
	 * @param apellido El apellido del cliente.
	 * @param Idioma El identificador o nombre del idioma de preferencia.
	 * @param usuario El nombre de usuario (nickname) para el inicio de sesión.
	 * @param contraseña La contraseña de la cuenta del cliente.
	 * @param fechaNacimiento La fecha de nacimiento del cliente.
	 * @param fechaRegistro La fecha en la que se realiza el registro en el sistema.
	 * @param fechaCaducidad La fecha en la que expira la suscripción premium.
	 */
	public ClientePremium(String nombre, String apellido, String Idioma, 
			String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro, Date fechaCaducidad) {
		
		super(nombre, apellido, Idioma, usuario, contraseña, fechaNacimiento, fechaRegistro);
		this.fechaCaducidad = fechaCaducidad;
	}

	/**
	 * Obtiene la fecha de caducidad de la suscripción premium.
	 * * @return La fecha en la que finaliza la suscripción del cliente.
	 */
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
}