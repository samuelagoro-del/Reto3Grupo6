package controlador;

import modelo.Modelo;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Artista;
import modelo.Cliente;

public class Controlador {
	private Modelo modelo;

	public Controlador() {
		modelo = new Modelo();
	}

	public int login(String usuarioInput, String passInput, String tipoElegido) {
		if (tipoElegido.equals("Admin")) {
			if (usuarioInput.equals("admin") && passInput.equals("1234")) {
				return 1; 
			} else {
				return 0; 
			}
		}

		if (tipoElegido.equals("Cliente")) {
			Cliente clienteEncontrado = modelo.loginCliente(usuarioInput, passInput);
			if (clienteEncontrado != null) {
				return 2; 
			} else {
				return 0; 
			}
		}
		return 0; 
	}

	public int registrarUsuario(String nombre, String apellido, String usuario, String contrasenia, LocalDate fechaNac, String fechaRegistro, String limitePremium, String idioma) {
		Cliente clienteRegistrado = modelo.registrarUsuarioModelo(nombre, apellido, usuario, contrasenia, fechaNac, fechaRegistro, limitePremium, idioma);
		if (clienteRegistrado != null) {
			return 1;
		}else {
			return 0;
		}
	}
		
	public Artista[] artistas() {
		Artista[] array = modelo.pedirArtistas().toArray(new Artista[0]);
		return array;
	}
		
	// ==========================================
	// MÉTODO NUEVO: Pide los álbumes como un array de Strings
	// ==========================================
	public String[] pedirAlbumsPorArtista(String idArtista) {
		return modelo.pedirAlbumPorArtista(idArtista).toArray(new String[0]);
	}
	public String[] pedirAlbumsPorArtistaDatos(String idArtista) {
		return modelo.pedirAlbumPorArtistaDatos(idArtista).toArray(new String[0]);
	}
	
        
        public String[] pedirAlbumPorArtistaDatosAlbum(String tituloAlbum) {
            ArrayList<String> lista = modelo.pedirAlbumPorArtistaDatosAlbum(tituloAlbum);
            return lista.toArray(new String[0]);
        }
        
        public String[] pedirCancionesDeAlbum(String tituloAlbum) {
            ArrayList<String> lista = modelo.pedirCancionesDeAlbum(tituloAlbum);
            return lista.toArray(new String[0]);
        }
        
     // Obtener todos los IDs o nombres de podcasters
        public String[] pedirPodcasters() {
            return modelo.pedirPodcasters().toArray(new String[0]);
        }

        // Obtener los nombres de los episodios de un podcaster
        public String[] pedirPodcastsPorPodcaster(String idPodcaster) {
            return modelo.pedirPodcastsPorPodcaster(idPodcaster).toArray(new String[0]);
        }
}