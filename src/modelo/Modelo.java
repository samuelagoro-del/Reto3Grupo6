package modelo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {
	private ArrayList<String> listaAlbumesInfo;

	public Cliente loginCliente(String usuario, String pass) {
		String sql = "SELECT C.*, P.FechaCaducidad FROM Cliente C LEFT JOIN Premium P ON C.IdCliente = P.IdCliente WHERE C.Usuario = ? AND C.Contraseña = ?";

		try {
			Connection con = BDConexion.getConexion();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String tipo = rs.getString("Tipo");

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

			String sql = "SELECT IdCliente FROM Cliente WHERE Usuario = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return null; 
			}

			String sql2 = "SELECT MAX(IdCliente) FROM Cliente";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();

			rs2.next(); 
			String ultimoId = rs2.getString(1); 
			int numero = Integer.parseInt(ultimoId.substring(1)); 
			String nuevoId = String.format("C%03d", numero + 1); 

			String idIdioma = "ES";
			if (idioma.equals("Euskera")) {
				idIdioma = "EU";
			}else if (idioma.equals("Inglés")) {
				idIdioma = "EN";
			}

			java.sql.Date sqlFechaNac = java.sql.Date.valueOf(fechaNac);
			java.sql.Date sqlFechaReg = java.sql.Date.valueOf(fechaRegistro);

			String tipo;
			if (limitePremium.equals("-")) {
				tipo = "Free";
			} else {
				tipo = "Premium";
			}

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

	public ArrayList<Artista> pedirArtistas() {
		ArrayList <Artista> lista = new ArrayList<>();
		try{ 
			Connection con = BDConexion.getConexion();
			String sql="Select IdArtista, NombreArtistico, Genero, Imagen, Descripcion from Artista";
			PreparedStatement pts= con.prepareStatement(sql);
			ResultSet rs =pts.executeQuery();

			while(rs.next()) {
				Artista a = new Artista(
						rs.getString("IdArtista"),
						rs.getString("NombreArtistico"),
						rs.getString("Genero"),
						rs.getString("Imagen"),
						rs.getString("Descripcion")
						);
				lista.add(a);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// ==========================================
	// MÉTODO NUEVO: Pide los álbumes filtrando por el ID del artista
	// ==========================================
	public ArrayList<String> pedirAlbumPorArtista(String idArtista) {
	    ArrayList<String> listaAlbumes = new ArrayList<>();
	    this.listaAlbumesInfo = new ArrayList<>(); 

	    String sql = "SELECT art.NombreArtistico, art.Genero AS GeneroArtista, art.Descripcion, art.Imagen AS ImagenArtista, " +
	                 "a.Titulo AS NombreAlbum, a.Año, COUNT(c.IdCancion) AS NumeroCanciones " +
	                 "FROM Artista art " +
	                 "INNER JOIN Album a ON art.IdArtista = a.IdMusico " +
	                 "LEFT JOIN Cancion c ON a.IdAlbum = c.IdAlbum " +
	                 "WHERE art.IdArtista = ? " +
	                 "GROUP BY a.IdAlbum, a.IdMusico, a.Titulo, a.Año, a.Genero, a.Imagen, " +
	                 "art.IdArtista, art.NombreArtistico, art.Genero, art.Imagen, art.Descripcion " +
	                 "ORDER BY a.Año DESC";

	    try (Connection con = BDConexion.getConexion();
	         PreparedStatement pts = con.prepareStatement(sql)) { 

	        pts.setString(1, idArtista); 
	        ResultSet rs = pts.executeQuery();

	        while(rs.next()) {
	        	if (listaAlbumesInfo.isEmpty()) {
	        		// Dentro del if (listaAlbumesInfo.isEmpty())
	        		listaAlbumesInfo.add(rs.getString("NombreArtistico")); // Posición 0
	        		listaAlbumesInfo.add(rs.getString("ImagenArtista"));    // Posición 1

	        		String bio = "Generoa: " + rs.getString("GeneroArtista") + "\n" +
	        		             "Descripción: " + rs.getString("Descripcion");
	        		listaAlbumesInfo.add(bio); // Posición 2;
	        	}

	            String fraseAlbum = rs.getString("NombreAlbum") + " - " + 
	                               rs.getInt("Año") + " (" + 
	                               rs.getInt("NumeroCanciones") + " cancion)";
	            listaAlbumes.add(fraseAlbum);
	        }
	    } catch (SQLException e) { e.printStackTrace(); }
	    return listaAlbumes;
	
	}
	public ArrayList<String> pedirAlbumPorArtistaDatos(String idArtista) {
	    return listaAlbumesInfo;
	}

	public ArrayList<String> pedirAlbumPorArtistaDatosAlbum(String tituloAlbum) {
	    ArrayList<String> datos = new ArrayList<>();
	    
	    // Consulta ajustada a tu esquema:
	    // Filtramos por el Título del álbum que viene de la JList
	    String sql = "SELECT a.Titulo, a.Año, a.Genero, a.Imagen, art.NombreArtistico " +
	                 "FROM Album a " +
	                 "JOIN Musico m ON a.IdMusico = m.IdMusico " +
	                 "JOIN Artista art ON m.IdMusico = art.IdArtista " +
	                 "WHERE a.Titulo = ?";

	    try (Connection con = BDConexion.getConexion();
	         PreparedStatement pts = con.prepareStatement(sql)) {

	        pts.setString(1, tituloAlbum);
	        ResultSet rs = pts.executeQuery();

	        if (rs.next()) {
	            // Guardamos los datos en orden para el Controlador
	            datos.add(rs.getString("Titulo"));          // 0
	            datos.add(rs.getString("Año"));             // 1
	            datos.add(rs.getString("Genero"));          // 2
	            datos.add(rs.getString("Imagen"));          // 3
	            datos.add(rs.getString("NombreArtistico")); // 4
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return datos;
	}
	public ArrayList<String> pedirCancionesDeAlbum(String tituloAlbum) {
	    ArrayList<String> listaCanciones = new ArrayList<>();
	    // Buscamos en Audio (para el nombre) cruzando con Cancion y Album
	    String sql = "SELECT au.Nombre " +
	                 "FROM Audio au " +
	                 "JOIN Cancion c ON au.IdAudio = c.IdCancion " +
	                 "JOIN Album al ON c.IdAlbum = al.IdAlbum " +
	                 "WHERE al.Titulo = ?";

	    try (Connection con = BDConexion.getConexion();
	         PreparedStatement pts = con.prepareStatement(sql)) {
	        
	        pts.setString(1, tituloAlbum);
	        ResultSet rs = pts.executeQuery();

	        while (rs.next()) {
	            listaCanciones.add(rs.getString("Nombre"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listaCanciones;
	}
	public ArrayList<String> pedirPodcasters() {
	    ArrayList<String> lista = new ArrayList<>();
	    String sql = "SELECT IdPodcaster FROM Podcaster";
	    try (Connection con = BDConexion.getConexion();
	         PreparedStatement pts = con.prepareStatement(sql);
	         ResultSet rs = pts.executeQuery()) {
	        while (rs.next()) {
	            lista.add(rs.getString("IdPodcaster"));
	        }
	    } catch (SQLException e) { e.printStackTrace(); }
	    return lista;
	}

	public ArrayList<String> pedirPodcastsPorPodcaster(String idPodcaster) {
	    ArrayList<String> lista = new ArrayList<>();
	    String sql = "SELECT a.Nombre FROM Audio a " +
	                 "JOIN Podcast p ON a.IdAudio = p.IdAudio " +
	                 "WHERE p.IdPodcaster = ?";
	    try (Connection con = BDConexion.getConexion();
	         PreparedStatement pts = con.prepareStatement(sql)) {
	        pts.setString(1, idPodcaster);
	        ResultSet rs = pts.executeQuery();
	        while (rs.next()) {
	            lista.add(rs.getString("Nombre"));
	        }
	    } catch (SQLException e) { e.printStackTrace(); }
	    return lista;
	}
}