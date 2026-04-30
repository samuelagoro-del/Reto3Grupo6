package modelo;

import java.sql.Date;

/**
 * Clase que representa un cliente de tipo Free.
 * Hereda de la clase Cliente sin añadir funcionalidades adicionales.
 */
public class ClienteFree extends Cliente {

    /**
     * Constructor de la clase ClienteFree.
     *
     * @param idCliente identificador del cliente
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param idIdioma idioma del cliente
     * @param usuario nombre de usuario
     * @param contraseña contraseña del cliente
     * @param fechaNacimiento fecha de nacimiento del cliente
     * @param fechaRegistro fecha de registro del cliente
     */
    public ClienteFree(String idCliente, String nombre, String apellido, String idIdioma, 
                       String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro) {
        super(idCliente, nombre, apellido, idIdioma, usuario, contraseña, fechaNacimiento, fechaRegistro);
        
    }
}
