package modelo;
import java.sql.Date;

/**
 * Clase que representa a un cliente con cuenta gratuita (Free).
 * Hereda de la clase {@link Cliente} y define a un usuario que utiliza 
 * el sistema sin suscripciones o funcionalidades premium adicionales.
 */
public class ClienteFree extends Cliente {
	
    /**
     * Constructor de la clase ClienteFree.
     * Recibe los datos básicos del cliente y los pasa al constructor de la clase padre (Cliente).
     * * @param nombre El nombre real del cliente.
     * @param apellido El apellido del cliente.
     * @param Idioma El identificador o nombre del idioma de preferencia.
     * @param usuario El nombre de usuario (nickname) para el inicio de sesión.
     * @param contraseña La contraseña de la cuenta del cliente.
     * @param fechaNacimiento La fecha de nacimiento del cliente.
     * @param fechaRegistro La fecha en la que se realiza el registro en el sistema.
     */
    public ClienteFree(String nombre, String apellido, String Idioma, 
                       String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro) {
        super(nombre, apellido, Idioma, usuario, contraseña, fechaNacimiento, fechaRegistro);
        
    }
}