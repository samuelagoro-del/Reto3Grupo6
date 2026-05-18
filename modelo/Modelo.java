package modelo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Modelo {
	private ArrayList<String> listaAlbumesInfo;
	private String idUsuarioLogueado; 

	// ==========================================
	// GESTIÓN DE USUARIOS Y LOGIN
	// ==========================================
	public Cliente loginCliente(String usuario, String pass) {
		String sql = "SELECT C.*, P.FechaCaducidad FROM Cliente C LEFT JOIN Premium P ON C.IdCliente = P.IdCliente WHERE C.Usuario = ? AND C.Contraseña = ?";
		try {
			Connection con = BDConexion.getConexion();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				this.idUsuarioLogueado = rs.getString("IdCliente"); 
				String tipo = rs.getString("Tipo");
				
				if (tipo.equals("Premium")) {
					return new ClientePremium(rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("IdIdioma"), rs.getString("Usuario"), rs.getString("Contraseña"), rs.getDate("FechaNacimiento"), rs.getDate("FechaRegistro"), rs.getDate("FechaCaducidad"));
				} else {
					return new ClienteFree(rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("IdIdioma"), rs.getString("Usuario"), rs.getString("Contraseña"), rs.getDate("FechaNacimiento"), rs.getDate("FechaRegistro"));
				}
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	public Cliente registrarUsuarioModelo(String nombre, String apellido, String usuario, String contrasenia, LocalDate fechaNac, String fechaRegistro, String limitePremium, String idioma) {
		try {
			Connection con = BDConexion.getConexion();
			String sqlCheck = "SELECT IdCliente FROM Cliente WHERE Usuario = ?";
			PreparedStatement psCheck = con.prepareStatement(sqlCheck);
			psCheck.setString(1, usuario);
			if (psCheck.executeQuery().next()) return null;

			String sqlMax = "SELECT MAX(IdCliente) FROM Cliente";
			ResultSet rsMax = con.prepareStatement(sqlMax).executeQuery();
			rsMax.next();
			String ultimoId = rsMax.getString(1);
			int numero = Integer.parseInt(ultimoId.substring(1));
			String nuevoId = String.format("C%03d", numero + 1);

			String idIdioma = idioma.equals("Euskera") ? "EU" : (idioma.equals("Inglés") ? "EN" : "ES");
			String tipo = limitePremium.equals("-") ? "Free" : "Premium";

			String sqlIns = "INSERT INTO Cliente (IdCliente, Nombre, Apellido, IdIdioma, Usuario, Contraseña, FechaNacimiento, FechaRegistro, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement psIns = con.prepareStatement(sqlIns);
			psIns.setString(1, nuevoId);
			psIns.setString(2, nombre);
			psIns.setString(3, apellido);
			psIns.setString(4, idIdioma);
			psIns.setString(5, usuario);
			psIns.setString(6, contrasenia);
			psIns.setDate(7, java.sql.Date.valueOf(fechaNac));
			psIns.setDate(8, java.sql.Date.valueOf(fechaRegistro));
			psIns.setString(9, tipo);
			psIns.executeUpdate();

			if (tipo.equals("Premium")) {
				PreparedStatement psPrem = con.prepareStatement("INSERT INTO Premium (IdCliente, FechaCaducidad) VALUES (?, ?)");
				psPrem.setString(1, nuevoId);
				psPrem.setDate(2, java.sql.Date.valueOf(limitePremium));
				psPrem.executeUpdate();
			}
			this.idUsuarioLogueado = nuevoId;
			return tipo.equals("Premium") ? new ClientePremium(nombre, apellido, idIdioma, usuario, contrasenia, java.sql.Date.valueOf(fechaNac), java.sql.Date.valueOf(fechaRegistro), java.sql.Date.valueOf(limitePremium)) : new ClienteFree(nombre, apellido, idIdioma, usuario, contrasenia, java.sql.Date.valueOf(fechaNac), java.sql.Date.valueOf(fechaRegistro));
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	// ==========================================
	// CONSULTAS DE MÚSICA Y ARTISTAS
	// ==========================================
	public ArrayList<Artista> pedirArtistas() {
		ArrayList<Artista> lista = new ArrayList<>();
		try (Connection con = BDConexion.getConexion(); ResultSet rs = con.prepareStatement("Select IdArtista, NombreArtistico, Genero, Imagen, Descripcion from Artista limit 8").executeQuery()) {
			while (rs.next()) {
				lista.add(new Artista(rs.getString("IdArtista"), rs.getString("NombreArtistico"), rs.getString("Genero"), rs.getString("Imagen"), rs.getString("Descripcion")));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return lista;
	}

	public ArrayList<String> pedirAlbumPorArtista(String idArtista) {
		ArrayList<String> listaAlbumes = new ArrayList<>();
		this.listaAlbumesInfo = new ArrayList<>();
		String sql = "SELECT art.NombreArtistico, art.Genero AS GeneroArtista, art.Descripcion, art.Imagen AS ImagenArtista, a.Titulo AS NombreAlbum, a.Año, COUNT(c.IdCancion) AS NumeroCanciones FROM Artista art INNER JOIN Album a ON art.IdArtista = a.IdMusico LEFT JOIN Cancion c ON a.IdAlbum = c.IdAlbum WHERE art.IdArtista = ? GROUP BY a.IdAlbum, art.IdArtista ORDER BY a.Año DESC";
		try (Connection con = BDConexion.getConexion(); PreparedStatement pts = con.prepareStatement(sql)) {
			pts.setString(1, idArtista);
			ResultSet rs = pts.executeQuery();
			while (rs.next()) {
				if (listaAlbumesInfo.isEmpty()) {
					listaAlbumesInfo.add(rs.getString("NombreArtistico"));
					listaAlbumesInfo.add(rs.getString("ImagenArtista"));
					listaAlbumesInfo.add("Género: " + rs.getString("GeneroArtista") + "\n" + rs.getString("Descripcion"));
				}
				listaAlbumes.add(rs.getString("NombreAlbum") + " - " + rs.getInt("Año") + " (" + rs.getInt("NumeroCanciones") + " canciones)");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return listaAlbumes;
	}

	public ArrayList<String> pedirAlbumPorArtistaDatos(String idArtista) { return listaAlbumesInfo; }

	public ArrayList<String> pedirAlbumPorArtistaDatosAlbum(String tituloAlbum) {
		ArrayList<String> datos = new ArrayList<>();
		String sql = "SELECT a.Titulo, a.Año, a.Genero, a.Imagen, art.NombreArtistico FROM Album a JOIN Musico m ON a.IdMusico = m.IdMusico JOIN Artista art ON m.IdMusico = art.IdArtista WHERE a.Titulo = ?";
		try (Connection con = BDConexion.getConexion(); PreparedStatement pts = con.prepareStatement(sql)) {
			pts.setString(1, tituloAlbum);
			ResultSet rs = pts.executeQuery();
			if (rs.next()) {
				datos.add(rs.getString("Titulo")); datos.add(rs.getString("Año")); datos.add(rs.getString("Genero")); datos.add(rs.getString("Imagen")); datos.add(rs.getString("NombreArtistico"));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return datos;
	}

	public ArrayList<String> pedirCancionesDeAlbum(String tituloAlbum) {
		ArrayList<String> lista = new ArrayList<>();
		String sql = "SELECT au.Nombre FROM Audio au JOIN Cancion c ON au.IdAudio = c.IdCancion JOIN Album al ON c.IdAlbum = al.IdAlbum WHERE al.Titulo = ?";
		try (Connection con = BDConexion.getConexion(); PreparedStatement pts = con.prepareStatement(sql)) {
			pts.setString(1, tituloAlbum);
			ResultSet rs = pts.executeQuery();
			while (rs.next()) lista.add(rs.getString("Nombre"));
		} catch (SQLException e) { e.printStackTrace(); }
		return lista;
	}

	// ==========================================
	// GESTIÓN DE PODCASTS
	// ==========================================
	public ArrayList<String> pedirPodcasters() {
		ArrayList<String> lista = new ArrayList<>();
		try (Connection con = BDConexion.getConexion(); ResultSet rs = con.prepareStatement("SELECT IdPodcaster FROM Podcaster").executeQuery()) {
			while (rs.next()) lista.add(rs.getString("IdPodcaster"));
		} catch (SQLException e) { e.printStackTrace(); }
		return lista;
	}

	public ArrayList<String> pedirPodcastsPorPodcaster(String idPodcaster) {
		ArrayList<String> lista = new ArrayList<>();
		String sql = "SELECT a.Nombre FROM Audio a JOIN Podcast p ON a.IdAudio = p.IdAudio WHERE p.IdPodcaster = ?";
		try (Connection con = BDConexion.getConexion(); PreparedStatement pts = con.prepareStatement(sql)) {
			pts.setString(1, idPodcaster);
			ResultSet rs = pts.executeQuery();
			while (rs.next()) lista.add(rs.getString("Nombre"));
		} catch (SQLException e) { e.printStackTrace(); }
		return lista;
	}

	// ==========================================
	// MÉTODOS DE REPRODUCCIÓN
	// ==========================================
	public ArrayList<String> pedirDatosAudioPorNombre(String nombreAudio) {
		ArrayList<String> datos = new ArrayList<>();
		String sql = "SELECT Nombre, Duracion, Archivo FROM Audio WHERE Nombre = ?";
		try (Connection con = BDConexion.getConexion(); PreparedStatement pts = con.prepareStatement(sql)) {
			pts.setString(1, nombreAudio);
			ResultSet rs = pts.executeQuery();
			if (rs.next()) {
				datos.add(rs.getString("Nombre")); datos.add(rs.getString("Duracion")); datos.add(rs.getString("Archivo"));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return datos;
	}

	// ==========================================
	// GESTIÓN DE PLAYLISTS (CRUD COMPLETO)
	// ==========================================

	public ArrayList<String> obtenerPlaylistsBD() {
		ArrayList<String> listas = new ArrayList<>();
		String sql = "SELECT Titulo FROM Playlist WHERE IdCliente = ?"; 
		try (Connection con = BDConexion.getConexion(); PreparedStatement pts = con.prepareStatement(sql)) {
			pts.setString(1, idUsuarioLogueado);
			ResultSet rs = pts.executeQuery();
			while (rs.next()) listas.add(rs.getString("Titulo"));
		} catch (SQLException e) { e.printStackTrace(); }
		return listas;
	}

	public boolean crearPlaylistBD(String nombre) {
		try (Connection con = BDConexion.getConexion()) {
			// LIMITACIÓN FREE: Máximo 3 playlists
            String sqlCount = "SELECT COUNT(*) FROM Playlist WHERE IdCliente = ?";
            PreparedStatement psCount = con.prepareStatement(sqlCount);
            psCount.setString(1, idUsuarioLogueado);
            ResultSet rsCount = psCount.executeQuery();
            
            if (rsCount.next() && rsCount.getInt(1) >= 3) {
                String sqlTipo = "SELECT Tipo FROM Cliente WHERE IdCliente = ?";
                PreparedStatement psTipo = con.prepareStatement(sqlTipo);
                psTipo.setString(1, idUsuarioLogueado);
                ResultSet rsTipo = psTipo.executeQuery();
                if(rsTipo.next() && rsTipo.getString("Tipo").equals("Free")) {
                    return false; 
                }
            }

			ResultSet rs = con.prepareStatement("SELECT MAX(IdList) FROM Playlist").executeQuery();
			int nuevoId = rs.next() ? rs.getInt(1) + 1 : 1;
			PreparedStatement ps = con.prepareStatement("INSERT INTO Playlist (IdList, Titulo, FechaCreacion, IdCliente) VALUES (?, ?, ?, ?)");
			ps.setInt(1, nuevoId); 
			ps.setString(2, nombre); 
			ps.setDate(3, java.sql.Date.valueOf(LocalDate.now())); 
			ps.setString(4, idUsuarioLogueado);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) { e.printStackTrace(); return false; }
	}

	public boolean eliminarPlaylistBD(String nombre) {
		try (Connection con = BDConexion.getConexion()) {
			PreparedStatement psId = con.prepareStatement("SELECT IdList FROM Playlist WHERE Titulo = ? AND IdCliente = ?");
			psId.setString(1, nombre);
			psId.setString(2, idUsuarioLogueado);
			ResultSet rs = psId.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				PreparedStatement psRel = con.prepareStatement("DELETE FROM Playlist_Canciones WHERE IdList = ?");
				psRel.setInt(1, id); psRel.executeUpdate();
				PreparedStatement psPlay = con.prepareStatement("DELETE FROM Playlist WHERE IdList = ?");
				psPlay.setInt(1, id); return psPlay.executeUpdate() > 0;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return false;
	}

	public boolean insertarCancionEnPlaylist(String nombreCancion, String nombrePlaylist) {
		try (Connection con = BDConexion.getConexion()) {
			PreparedStatement psA = con.prepareStatement("SELECT IdAudio FROM Audio WHERE Nombre = ?");
			psA.setString(1, nombreCancion);
			ResultSet rsA = psA.executeQuery();
			PreparedStatement psP = con.prepareStatement("SELECT IdList FROM Playlist WHERE Titulo = ? AND IdCliente = ?");
			psP.setString(1, nombrePlaylist);
			psP.setString(2, idUsuarioLogueado);
			ResultSet rsP = psP.executeQuery();
			if (rsA.next() && rsP.next()) {
				PreparedStatement psIns = con.prepareStatement("INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_Cancion) VALUES (?, ?, ?)");
				psIns.setInt(1, rsP.getInt(1)); psIns.setString(2, rsA.getString(1)); psIns.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
				return psIns.executeUpdate() > 0;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return false;
	}

	public boolean eliminarCancionEnPlaylistBD(String nombreCancion, String nombrePlaylist) {
		String sql = "DELETE FROM Playlist_Canciones WHERE IdList = (SELECT IdList FROM Playlist WHERE Titulo = ? AND IdCliente = ?) " +
		             "AND IdCancion = (SELECT IdAudio FROM Audio WHERE Nombre = ?)";
		try (Connection con = BDConexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, nombrePlaylist);
			ps.setString(2, idUsuarioLogueado);
			ps.setString(3, nombreCancion);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) { e.printStackTrace(); }
		return false;
	}

	public void marcarComoFavorito(String nombreCancion) {
		try (Connection con = BDConexion.getConexion()) {
			PreparedStatement ps = con.prepareStatement("SELECT IdAudio FROM Audio WHERE Nombre = ?");
			ps.setString(1, nombreCancion);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				PreparedStatement psIns = con.prepareStatement("INSERT IGNORE INTO Gustos (IdCliente, IdAudio) VALUES (?, ?)");
				psIns.setString(1, idUsuarioLogueado); 
				psIns.setString(2, rs.getString(1)); 
				psIns.executeUpdate();
			}
		} catch (SQLException e) { System.out.println("Error al marcar favorito."); }
	}

	public ArrayList<String> obtenerCancionesDePlaylist(String nombrePlaylist) {
		ArrayList<String> lista = new ArrayList<>();
		String sql = "SELECT a.Nombre FROM Audio a JOIN Playlist_Canciones pc ON a.IdAudio = pc.IdCancion JOIN Playlist p ON pc.IdList = p.IdList WHERE p.Titulo = ? AND p.IdCliente = ?";
		try (Connection con = BDConexion.getConexion(); PreparedStatement pts = con.prepareStatement(sql)) {
			pts.setString(1, nombrePlaylist);
			pts.setString(2, idUsuarioLogueado);
			ResultSet rs = pts.executeQuery();
			while (rs.next()) lista.add(rs.getString("Nombre"));
		} catch (SQLException e) { e.printStackTrace(); }
		return lista;
	}

	public void importarDatosSQL() { 
		System.out.println("Lógica de importación ejecutada para el usuario: " + idUsuarioLogueado); 
	}

	public void exportarPlaylistAFichero(String nombrePlaylist) {
		ArrayList<String> canciones = obtenerCancionesDePlaylist(nombrePlaylist);
		String nombreFichero = nombrePlaylist.replaceAll("\\s+", "_") + "_export.txt";
		
		try (PrintWriter out = new PrintWriter(new FileWriter(nombreFichero))) {
			out.println("PLAYLIST: " + nombrePlaylist);
			out.println("USUARIO ID: " + idUsuarioLogueado);
			out.println("FECHA EXPORTACIÓN: " + LocalDate.now());
			out.println("------------------------------------------");
			if (canciones.isEmpty()) {
				out.println("La playlist está vacía.");
			} else {
				for (String cancion : canciones) {
					out.println("- " + cancion);
				}
			}
			JOptionPane.showMessageDialog(null, "Playlist exportada con éxito a: " + nombreFichero);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al exportar la playlist: " + e.getMessage());
			e.printStackTrace();
		}
	}
	// ==========================================
		// MÉTODOS EXCLUSIVOS DEL ADMINISTRADOR (CRUD)
		// ==========================================

		/**
		 * Elimina un artista de la base de datos. 
		 * Nota: Por integridad referencial, esto podría fallar si tiene álbumes asociados 
		 * a menos que la BD esté configurada en ON DELETE CASCADE.
		 */
		public boolean eliminarArtistaBD(String idArtista) {
			String sql = "DELETE FROM Artista WHERE IdArtista = ?";
			try (Connection con = BDConexion.getConexion(); 
				 PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, idArtista);
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * Inserta una nueva canción. 
		 * Primero debe crear el registro en la tabla genérica 'Audio' y luego en 'Cancion'.
		 */
		public boolean insertarCancionBD(String nombre, String duracion, String archivo, String idAlbum) {
			Connection con = null;
			try {
				con = BDConexion.getConexion();
				con.setAutoCommit(false); // Transacción para asegurar que se crean ambos registros

				// 1. Obtener nuevo ID para Audio
				String sqlMax = "SELECT MAX(IdAudio) FROM Audio";
				ResultSet rsMax = con.prepareStatement(sqlMax).executeQuery();
				rsMax.next();
				String ultimoId = rsMax.getString(1);
				int numero = Integer.parseInt(ultimoId.substring(1));
				String nuevoId = String.format("A%03d", numero + 1);

				// 2. Insertar en tabla Audio
				String sqlAudio = "INSERT INTO Audio (IdAudio, Nombre, Duracion, Archivo) VALUES (?, ?, ?, ?)";
				PreparedStatement psA = con.prepareStatement(sqlAudio);
				psA.setString(1, nuevoId);
				psA.setString(2, nombre);
				psA.setString(3, duracion);
				psA.setString(4, archivo);
				psA.executeUpdate();

				// 3. Insertar en tabla Cancion vinculando al álbum
				String sqlCancion = "INSERT INTO Cancion (IdCancion, IdAlbum) VALUES (?, ?)";
				PreparedStatement psC = con.prepareStatement(sqlCancion);
				psC.setString(1, nuevoId);
				psC.setString(2, idAlbum);
				psC.executeUpdate();

				con.commit();
				return true;
			} catch (SQLException e) {
				try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * Obtiene el TOP 10 de canciones más reproducidas para la ventana de estadísticas.
		 */
		public ArrayList<String[]> consultarEstadisticasBD(String tipoEstadistica) {
		    ArrayList<String[]> stats = new ArrayList<>();
		    String sql = "";


		    switch (tipoEstadistica) {

		    case "Top Canciones Favoritas":
		        sql = "SELECT a.Nombre AS Cancion, " +
		              "ar.NombreArtistico AS Artista, " +
		              "COUNT(g.IdAudio) AS Total " +
		              "FROM Audio a " +
		              "JOIN Gustos g ON a.IdAudio = g.IdAudio " +
		              "JOIN Cancion c ON a.IdAudio = c.IdCancion " +
		              "JOIN Album al ON c.IdAlbum = al.IdAlbum " +
		              "JOIN Musico m ON al.IdMusico = m.IdMusico " +
		              "JOIN Artista ar ON m.IdMusico = ar.IdArtista " +
		              "WHERE a.Tipo = 'cancion' " +
		              "GROUP BY a.IdAudio, ar.NombreArtistico " + 
		              "ORDER BY Total DESC LIMIT 10";
		        break;

		    case "Top Podcasts Favoritos":
		        sql = "SELECT a.Nombre AS Podcast, " +
		              "ar.NombreArtistico AS Podcaster, " +
		              "COUNT(g.IdAudio) AS Total " +
		              "FROM Audio a " +
		              "JOIN Gustos g ON a.IdAudio = g.IdAudio " +
		              "JOIN Podcast p ON a.IdAudio = p.IdAudio " +
		              "JOIN Podcaster po ON p.IdPodcaster = po.IdPodcaster " +
		              "JOIN Artista ar ON po.IdPodcaster = ar.IdArtista " +
		              "WHERE a.Tipo = 'podcast' " +
		              "GROUP BY a.IdAudio, ar.NombreArtistico " +
		              "ORDER BY Total DESC LIMIT 10";
		        break;

		    case "Top Más Escuchados":
		        sql = "SELECT a.Nombre AS Audio, " +
		              "ar.NombreArtistico AS Artista, " +
		              "a.NReproducciones, " +
		              "a.Tipo " +
		              "FROM Audio a " +
		              "LEFT JOIN Cancion c ON a.IdAudio = c.IdCancion " +
		              "LEFT JOIN Album al ON c.IdAlbum = al.IdAlbum " +
		              "LEFT JOIN Musico m ON al.IdMusico = m.IdMusico " +
		              "LEFT JOIN Podcast p ON a.IdAudio = p.IdAudio " +
		              "LEFT JOIN Podcaster po ON p.IdPodcaster = po.IdPodcaster " +
		              "LEFT JOIN Artista ar ON ar.IdArtista = COALESCE(m.IdMusico, po.IdPodcaster) " +
		              "ORDER BY a.NReproducciones DESC LIMIT 10";
		        break;

		    case "Top Playlists":
		        sql = "SELECT p.Titulo AS Playlist, " +
		              "cl.Usuario AS Creador, " +
		              "COUNT(pc.IdCancion) AS TotalCanciones " +
		              "FROM Playlist p " +
		              "LEFT JOIN Playlist_Canciones pc ON p.IdList = pc.IdList " +
		              "JOIN Cliente cl ON p.IdCliente = cl.IdCliente " +
		              "GROUP BY p.IdList, cl.Usuario " +
		              "ORDER BY TotalCanciones DESC LIMIT 10";
		        break;
		
		    }


		    try (Connection con = BDConexion.getConexion();
		         PreparedStatement ps = con.prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {

		        while (rs.next()) {

		            String titulo = rs.getString(1);
		            String autor  = rs.getString(2);
		            String total  = rs.getString(3);

		            stats.add(new String[]{
		                autor,                // Artista/Autor
		                titulo,               // Título
		                total                 // Reproducciones / Total
		            });

		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return stats;
		}
		
		
		// ==========================================
	    // GESTIÓN AVANZADA ADMIN (ÁLBUMES Y PODCASTS)
	    // ==========================================

	    /**
	     * Elimina un álbum y todas sus canciones asociadas de forma atómica.
	     */
	    public boolean eliminarAlbumBD(String tituloAlbum) {
	        Connection con = null;
	        try {
	            con = BDConexion.getConexion();
	            con.setAutoCommit(false); // Iniciar transacción

	            // 1. Obtener el ID del álbum mediante el título
	            String sqlId = "SELECT IdAlbum FROM Album WHERE Titulo = ?";
	            PreparedStatement psId = con.prepareStatement(sqlId);
	            psId.setString(1, tituloAlbum);
	            ResultSet rs = psId.executeQuery();

	            if (rs.next()) {
	                String idAlbum = rs.getString("IdAlbum");

	                // 2. Borrar registros de la tabla Cancion y Audio vinculados
	                // Primero borramos de Cancion (hija) y luego de Audio (padre)
	                String sqlDelCancion = "DELETE FROM Cancion WHERE IdAlbum = ?";
	                PreparedStatement ps1 = con.prepareStatement(sqlDelCancion);
	                ps1.setString(1, idAlbum);
	                ps1.executeUpdate();

	                // 3. Borrar el álbum
	                String sqlDelAlbum = "DELETE FROM Album WHERE IdAlbum = ?";
	                PreparedStatement ps2 = con.prepareStatement(sqlDelAlbum);
	                ps2.setString(1, idAlbum);
	                ps2.executeUpdate();

	                con.commit();
	                return true;
	            }
	            return false;
	        } catch (SQLException e) {
	            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
	            e.printStackTrace();
	            return false;
	        }
	    }

	    /**
	     * Elimina un podcast específico del sistema (Audio + Podcast)
	     */
	    public boolean eliminarPodcastSistemaBD(String nombrePodcast) {
	        String sql = "DELETE FROM Audio WHERE Nombre = ? AND IdAudio IN (SELECT IdAudio FROM Podcast)";
	        try (Connection con = BDConexion.getConexion(); 
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nombrePodcast);
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    /**
	     * Modifica los datos básicos de un artista existente.
	     */
	    public boolean modificarArtistaBD(String idArtista, String nuevoNombre, String nuevoGenero) {
	        String sql = "UPDATE Artista SET NombreArtistico = ?, Genero = ? WHERE IdArtista = ?";
	        try (Connection con = BDConexion.getConexion(); 
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nuevoNombre);
	            ps.setString(2, nuevoGenero);
	            ps.setString(3, idArtista);
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    /**
	     * Elimina una canción específica de la base de datos global.
	     */
	    public boolean eliminarCancionSistemaBD(String nombreCancion) {
	        String sql = "DELETE FROM Audio WHERE Nombre = ? AND IdAudio IN (SELECT IdCancion FROM Cancion)";
	        try (Connection con = BDConexion.getConexion(); 
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nombreCancion);
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}