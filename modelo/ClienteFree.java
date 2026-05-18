package modelo;
import java.sql.Date;

public class ClienteFree extends Cliente {
    public ClienteFree(String nombre, String apellido, String Idioma, 
                       String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro) {
        super(nombre, apellido, Idioma, usuario, contraseña, fechaNacimiento, fechaRegistro);
        
    }
}