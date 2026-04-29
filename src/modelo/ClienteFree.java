package modelo;
import java.sql.Date;

public class ClienteFree extends Cliente {
    public ClienteFree(String idCliente, String nombre, String apellido, String idIdioma, 
                       String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro) {
        super(idCliente, nombre, apellido, idIdioma, usuario, contraseña, fechaNacimiento, fechaRegistro);
        
    }
}