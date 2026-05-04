package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL.
 * Proporciona un método estático para obtener la conexión a la base de datos 
 * configurada para el proyecto.
 */
public class BDConexion {
	
    /**
     * URL de conexión a la base de datos MySQL.
     * Incluye el puerto (33060), el nombre de la base de datos (grupo6_reto3_dam) 
     * y los parámetros de configuración de zona horaria y seguridad.
     */
    private static final String URL = "jdbc:mysql://localhost:33060/grupo6_reto3_dam?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    
    /**
     * Nombre de usuario para acceder a la base de datos.
     */
    private static final String USER = "root";
    
    /**
     * Contraseña correspondiente al usuario de la base de datos.
     */
    private static final String PASS = "elorrieta";

    /**
     * Establece y devuelve una conexión activa con la base de datos.
     * * @return Un objeto de tipo {@link java.sql.Connection} que representa la conexión a la base de datos.
     * @throws SQLException Si ocurre algún error de acceso a la base de datos o si 
     * los parámetros de conexión son incorrectos.
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}