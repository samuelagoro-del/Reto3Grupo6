package controlador;

import modelo.Modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Artista;
import modelo.Cliente;
import modelo.ClientePremium;

public class Controlador {
    private Modelo modelo;
    private Cliente usuarioActivo; 
    private long ultimaVezSaltado = 0;
    private final long DIEZ_MINUTOS = 600000; // 10 minutos en milisegundos

    public Controlador() {
        modelo = new Modelo();
    }
    
    public Modelo getModelo() { return modelo; }
    public Cliente getUsuarioActivo() { return usuarioActivo; }

    // --- SESIÓN Y REGISTRO ---
    public int login(String usuarioInput, String passInput, String tipoElegido) {
        if (tipoElegido.equals("Admin")) {
            if (usuarioInput.equals("admin") && passInput.equals("1234")) return 1; 
            return 0;
        }

        if (tipoElegido.equals("Cliente")) {
            Cliente clienteEncontrado = modelo.loginCliente(usuarioInput, passInput);
            if (clienteEncontrado != null) {
                this.usuarioActivo = clienteEncontrado;
                this.ultimaVezSaltado = 0; 
                return 2; 
            }
        }
        return 0; 
    }

    public int registrarUsuario(String nombre, String apellido, String usuario, String contrasenia, LocalDate fechaNac,
            String fechaRegistroSQL, String limitePremiumSQL, String idioma) {
        
        Cliente nuevo = modelo.registrarUsuarioModelo(nombre, apellido, usuario, contrasenia, fechaNac, fechaRegistroSQL, limitePremiumSQL, idioma);
        
        if (nuevo != null) {
            this.usuarioActivo = nuevo;
            return 1;
        }
        return 0;
    }

    public void logout() {
        this.usuarioActivo = null;
        this.ultimaVezSaltado = 0;
    }

    // --- LIMITACIONES FREE ---
    public boolean esPremium() { 
        return usuarioActivo instanceof ClientePremium; 
    }

    public boolean puedeSaltarCancion() {
        if (esPremium()) return true;
        
        long ahora = System.currentTimeMillis();
        if (ahora - ultimaVezSaltado >= DIEZ_MINUTOS) {
            ultimaVezSaltado = ahora;
            return true;
        }
        return false;
    }

    public long segundosParaSiguienteSalto() {
        if (esPremium()) return 0;
        long tiempoRestante = DIEZ_MINUTOS - (System.currentTimeMillis() - ultimaVezSaltado);
        return Math.max(0, tiempoRestante / 1000);
    }

    // --- GESTIÓN DE MÚSICA ---
    public Artista[] artistas() { 
        return modelo.pedirArtistas().toArray(new Artista[0]); 
    }
    
    public String[] pedirAlbumsPorArtista(String idArtista) { 
        return modelo.pedirAlbumPorArtista(idArtista).toArray(new String[0]); 
    }

    public String[] pedirAlbumsPorArtistaDatos(String idArtista) { 
        return modelo.pedirAlbumPorArtistaDatos(idArtista).toArray(new String[0]); 
    }

    public String[] pedirAlbumPorArtistaDatosAlbum(String tituloAlbum) {
        ArrayList<String> datos = modelo.pedirAlbumPorArtistaDatosAlbum(tituloAlbum);
        return (datos != null) ? datos.toArray(new String[0]) : new String[0];
    }

    public String[] pedirCancionesDeAlbum(String tituloAlbum) { 
        return modelo.pedirCancionesDeAlbum(tituloAlbum).toArray(new String[0]); 
    }

    public String[] pedirDatosReproduccion(String nombreAudio) {
        ArrayList<String> datos = modelo.pedirDatosAudioPorNombre(nombreAudio);
        return (datos != null) ? datos.toArray(new String[0]) : null;
    }

    // --- GESTIÓN DE PODCASTS ---
    public String[] pedirPodcasters() {
        ArrayList<String> lista = modelo.pedirPodcasters();
        return (lista != null) ? lista.toArray(new String[0]) : new String[0];
    }

    public String[] pedirPodcastsPorPodcaster(String nombrePodcaster) {
        ArrayList<String> lista = modelo.pedirPodcastsPorPodcaster(nombrePodcaster);
        return (lista != null) ? lista.toArray(new String[0]) : new String[0];
    }

    // --- PLAYLISTS ---
    public String[] pedirPlaylistsUsuario() { 
        ArrayList<String> lista = modelo.obtenerPlaylistsBD();
        return (lista != null) ? lista.toArray(new String[0]) : new String[0]; 
    }

    public boolean crearNuevaPlaylist(String nombre) {
        return modelo.crearPlaylistBD(nombre);
    }

    public void añadirCancionAPlaylist(String nombreCancion, String nombrePlaylist) {
        modelo.insertarCancionEnPlaylist(nombreCancion, nombrePlaylist);
    }
    
    public void eliminarCancionDePlaylist(String nombreCancion, String nombrePlaylist) {
        modelo.eliminarCancionEnPlaylistBD(nombreCancion, nombrePlaylist);
    }

    public void borrarPlaylist(String nombre) {
        modelo.eliminarPlaylistBD(nombre);
    }

    public void guardarFavorito(String nombreCancion) { 
        modelo.marcarComoFavorito(nombreCancion); 
    }
    
    // --- IMPORT / EXPORT ---
    public void importarPlaylistsDesdeFichero() {
        modelo.importarDatosSQL();
    }

    public void exportarPlaylistAFichero(String nombrePlaylist) {
        modelo.exportarPlaylistAFichero(nombrePlaylist);
    }
    
 // ==========================================
    // MÉTODOS EXCLUSIVOS DEL ADMINISTRADOR (CRUD)
    // ==========================================

    // --- GESTIÓN DE MÚSICA (Artistas, Álbumes, Canciones) ---

    public boolean eliminarArtista(String idArtista) {
        return modelo.eliminarArtistaBD(idArtista);
    }

    public boolean modificarArtista(String id, String nombre, String genero) {
        return modelo.modificarArtistaBD(id, nombre, genero);
    }

    public boolean eliminarAlbum(String tituloAlbum) {
        return modelo.eliminarAlbumBD(tituloAlbum);
    }

    public boolean eliminarCancion(String nombreCancion) {
        return modelo.eliminarCancionSistemaBD(nombreCancion);
    }

    public boolean subirCancion(String nombre, String duracion, String archivo, String idAlbum) {
        return modelo.insertarCancionBD(nombre, duracion, archivo, idAlbum);
    }

    // --- GESTIÓN DE PODCASTS (Creadores y Episodios) ---

    public boolean eliminarPodcaster(String idPodcaster) {
        return modelo.eliminarPodcastSistemaBD(idPodcaster);
    }

    public boolean eliminarPodcast(String nombrePodcast) {
        return modelo.eliminarPodcastSistemaBD(nombrePodcast);
    }

    // --- ESTADÍSTICAS ---
    public String[][] obtenerEstadisticas(String tipo) {
        ArrayList<String[]> lista = modelo.consultarEstadisticasBD(tipo);
        return lista.toArray(new String[0][0]);
    }
}