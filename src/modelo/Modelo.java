package modelo;

import java.sql.*;
import java.time.LocalDate;

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
				String tipo = rs.getString("Tipo");

				// Corregido: rs.getString("IdIdioma") en lugar de "Idioma"
				if (tipo.equals("Premium")) {
					return new ClientePremium(
							rs.getString("Nombre"), rs.getString("Apellido"),
							rs.getString("IdIdioma"), rs.getString("Usuario"), rs.getString("Contraseña"),
							rs.getDate("FechaNacimiento"), rs.getDate("FechaRegistro"), rs.getDate("FechaCaducidad")
							);
				} else {
					return new ClienteFree(
							rs.getString("Nombre"), rs.getString("Apellido"),
							rs.getString("IdIdioma"), rs.getString("Usuario"), rs.getString("Contraseña"),
							rs.getDate("FechaNacimiento"), rs.getDate("FechaRegistro")
							);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	public Cliente registrarUsuarioModelo(String nombre, String apellido, String usuario, String contrasenia, LocalDate fechaNac, String fechaRegistro, String limitePremium, String idioma) {
		try {
			Connection con = BDConexion.getConexion();

			// ==========================================
			// 1. COMPROBAR SI EL USUARIO YA EXISTE
			// ==========================================
			String sql = "SELECT IdCliente FROM Cliente WHERE Usuario = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return null; // El usuario ya existe
			}

			// ==========================================
			// 2. GENERAR EL NUEVO IdCliente
			// ==========================================
			String sql2 = "SELECT MAX(IdCliente) FROM Cliente";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();

			rs2.next(); // Como sabemos que siempre hay datos(ya existen clientes), avanzamos directamente
			String ultimoId = rs2.getString(1); // Recuperamos el último, ej: "C001"
			int numero = Integer.parseInt(ultimoId.substring(1)); // Extraemos el número
			String nuevoId = String.format("C%03d", numero + 1); // Generamos el siguiente, ej: "C002"

			// ==========================================
			// 3. AMOLDAR EL IDIOMA SELECCIONADO A LA BD
			// ==========================================
			String idIdioma = "ES";
			if (idioma.equals("Euskera")) {
				idIdioma = "EU";
			}else if (idioma.equals("Inglés")) {
				idIdioma = "EN";
			}

			// ==========================================
			// 4. PREPARAR LAS FECHAS PARA SQL
			// ==========================================
			/* lo de java.sql.Date es una forma de traducirle a la base de datos la fecha, 
			esto es por tema de versiones y cosas asi, se podía haber hecho algo con 
			LocalDate pero creo que por las versiones no*/
			java.sql.Date sqlFechaNac = java.sql.Date.valueOf(fechaNac);
			java.sql.Date sqlFechaReg = java.sql.Date.valueOf(fechaRegistro);

			String tipo;
			if (limitePremium.equals("-")) {
			    tipo = "Free";
			} else {
			    tipo = "Premium";
			}

			// ==========================================
			// 5. INSERTAR EN LA TABLA CLIENTE
			// ==========================================
			String sqlInsertCliente = "INSERT INTO Cliente (IdCliente, Nombre, Apellido, IdIdioma, Usuario, Contraseña, FechaNacimiento, FechaRegistro, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement psInsertCli = con.prepareStatement(sqlInsertCliente);
			psInsertCli.setString(1, nuevoId);
			psInsertCli.setString(2, nombre);
			psInsertCli.setString(3, apellido);
			psInsertCli.setString(4, idIdioma);
			psInsertCli.setString(5, usuario);
			psInsertCli.setString(6, contrasenia);
			psInsertCli.setDate(7, sqlFechaNac);
			psInsertCli.setDate(8, sqlFechaReg);
			psInsertCli.setString(9, tipo);
			psInsertCli.executeUpdate();

			// ==========================================
			// 6. INSERTAR EN LA TABLA PREMIUM (Si aplica)
			// ==========================================
			// hay que iniciarla antes
			java.sql.Date sqlFechaCaducidad = null;
			if (tipo.equals("Premium")) {
				sqlFechaCaducidad = java.sql.Date.valueOf(limitePremium);

				String sqlInsertPremium = "INSERT INTO Premium (IdCliente, FechaCaducidad) VALUES (?, ?)";
				PreparedStatement psInsertPrem = con.prepareStatement(sqlInsertPremium);
				psInsertPrem.setString(1, nuevoId);
				psInsertPrem.setDate(2, sqlFechaCaducidad);
				psInsertPrem.executeUpdate();

				return new ClientePremium(nombre, apellido, idIdioma, usuario, contrasenia, sqlFechaNac, sqlFechaReg, sqlFechaCaducidad);
			} else {
				return new ClienteFree(nombre, apellido, idIdioma, usuario, contrasenia, sqlFechaNac, sqlFechaReg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null; 
	}
}
