package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos.
 */
public class BDConexion {
    
    /**
     * URL de conexión a la base de datos MySQL.
     */
    private static final String URL = "jdbc:mysql://localhost:33060/grupo6_reto3_dam?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    
    /**
     * Usuario de la base de datos.
     */
    private static final String USER = "root";
    
    /**
     * Contraseña de la base de datos.
     */
    private static final String PASS = "elorrieta";

    /**
     * Establece y devuelve una conexión con la base de datos.
     * 
     * @return objeto Connection con la conexión activa
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
