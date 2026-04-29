package modelo;

import java.sql.*;

public class Modelo {
    
    public Cliente loginCliente(String usuario, String pass) {
        // Consulta que une Cliente y Premium para saber si tiene fecha de caducidad
        String sql = "SELECT C.*, P.FechaCaducidad FROM Cliente C LEFT JOIN Premium P ON C.IdCliente = P.IdCliente WHERE C.Usuario = ? AND C.Contraseña = ?";

        try {
            Connection con = BDConexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Miramos el ENUM 'Tipo' de tu base de datos
                String tipo = rs.getString("Tipo");

                if (tipo.equals("Premium")) {
                    return new ClientePremium(
                        rs.getString("IdCliente"), rs.getString("Nombre"), rs.getString("Apellido"),
                        rs.getString("IdIdioma"), rs.getString("Usuario"), rs.getString("Contraseña"),
                        rs.getDate("FechaNacimiento"), rs.getDate("FechaRegistro"), rs.getDate("FechaCaducidad")
                    );
                } else {
                    return new ClienteFree(
                        rs.getString("IdCliente"), rs.getString("Nombre"), rs.getString("Apellido"),
                        rs.getString("IdIdioma"), rs.getString("Usuario"), rs.getString("Contraseña"),
                        rs.getDate("FechaNacimiento"), rs.getDate("FechaRegistro")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no hay usuario, devuelve nada
    }
}