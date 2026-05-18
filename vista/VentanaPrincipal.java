package vista;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controlador.Controlador;
import modelo.Artista;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel contenedor;
    private PanelRegistro registro;
    private Controlador miControlador;
    
    // Paneles de Música
    private VentanaArtista panelArtista;
    private VentanaAlbum panelAlbum;
    private VentanaReproducion panelReproduccion;
    private PanelDescubrirMúsica descubrirMusica;

    // Paneles de Podcast
    private VentanaPoscasterLista panelDescubrirPodcast;
    private VentanaPoscastLista panelListaPodcasts;
    private PodcastReproducciones panelReproductorPodcast; 

    // Paneles de Playlist
    private VentanaPlaylist panelPlaylist;
    private VentanaPlaylsitPrincipal panelPlaylistDetalle;

    // Paneles de Administración
    private VentanaAdministración panelAdmin;
    private VentanaGestionMusica panelGestionMusica;
    private VentanaGestionPodcast panelGestionPodcast;
    private VentanaEstadisticas panelEstadisticas;

    public VentanaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 550); 
        setTitle("Reproductor de Música y Podcasts - Grupo 6");
        setLocationRelativeTo(null); 
        
        miControlador = new Controlador();
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // 1. Inicializar pantallas principales
        PanelLogin login = new PanelLogin(this, miControlador);
        registro = new PanelRegistro(this, miControlador);
        PanelMenu menu = new PanelMenu(this);
        
        // Carga inicial de artistas
        Artista[] arrayArtistas = miControlador.artistas();
        descubrirMusica = new PanelDescubrirMúsica(arrayArtistas, this);
        
        panelArtista = new VentanaArtista(this); 
        panelAlbum = new VentanaAlbum(this); 
        panelReproduccion = new VentanaReproducion(this);
        
        // 2. Inicializar pantallas de Playlist
        panelPlaylist = new VentanaPlaylist(this); 
        panelPlaylistDetalle = new VentanaPlaylsitPrincipal(this);

        // 3. Inicializar pantallas de Podcast
        panelDescubrirPodcast = new VentanaPoscasterLista(this);
        panelListaPodcasts = new VentanaPoscastLista(this);
        panelReproductorPodcast = new PodcastReproducciones(this); 

        // 4. Inicializar pantallas de Administración
        panelAdmin = new VentanaAdministración(this, miControlador);
        panelGestionMusica = new VentanaGestionMusica(this);
        panelGestionPodcast = new VentanaGestionPodcast(this);
        panelEstadisticas = new VentanaEstadisticas(this);

        // 5. Añadir al contenedor con nombres para el CardLayout
        contenedor.add(login, "PANTALLA_LOGIN");
        contenedor.add(registro, "PANTALLA_REGISTRO");
        contenedor.add(menu, "PANTALLA_MENU");
        contenedor.add(descubrirMusica, "PANTALLA_DESCUBRIR");
        contenedor.add(panelArtista, "PANTALLA_ARTISTA"); 
        contenedor.add(panelAlbum, "PANTALLA_ALBUM");     
        contenedor.add(panelReproduccion, "PANTALLA_REPRODUCCION");
        contenedor.add(panelPlaylist, "PANTALLA_PLAYLIST"); 
        contenedor.add(panelPlaylistDetalle, "PANTALLA_PLAYLIST_DETALLE");
        contenedor.add(panelDescubrirPodcast, "PANTALLA_DESCUBRIR_PODCASTERS");
        contenedor.add(panelListaPodcasts, "PANTALLA_LISTA_PODCASTS");
        contenedor.add(panelReproductorPodcast, "PANTALLA_REPRODUCCION_PODCAST");
        
        // Registro Paneles Admin
        contenedor.add(panelAdmin, "PANTALLA_ADMIN");
        contenedor.add(panelGestionMusica, "GESTION_MUSICA");
        contenedor.add(panelGestionPodcast, "GESTION_PODCAST");
        contenedor.add(panelEstadisticas, "VENTANA_ESTADISTICAS");

        getContentPane().add(contenedor);
        setVisible(true);
    }

    public void mostrarPantalla(String nombre) {
        
        // Actualizar datos del usuario activo si existe
        if (miControlador.getUsuarioActivo() != null) {
            String nick = miControlador.getUsuarioActivo().getUsuario();
            panelPlaylist.setNombreUsuario(nick);
            panelReproduccion.setNombreUsuario(nick);
            descubrirMusica.setNombreUsuario(nick);
        }

        // Carga dinámica de datos según la pantalla
        switch (nombre) {
            case "PANTALLA_DESCUBRIR_PODCASTERS":
                panelDescubrirPodcast.cargarPodcasters(miControlador.pedirPodcasters());
                break;
                
            case "PANTALLA_PLAYLIST":
                panelPlaylist.actualizarLista(miControlador.pedirPlaylistsUsuario());
                break;
                
            case "PANTALLA_DESCUBRIR":
                descubrirMusica.actualizarListaArtistas(miControlador.artistas());
                break;

            case "GESTION_MUSICA":
                panelGestionMusica.cargarDatos();
                break;

            case "GESTION_PODCAST":
                panelGestionPodcast.cargarDatos();
                break;

            case "VENTANA_ESTADISTICAS":
                panelEstadisticas.cargarEstadisticas();
                break;
        }

        cardLayout.show(contenedor, nombre);
    }

    // --- GETTERS ---
    public Controlador getControlador() { return miControlador; }
    public VentanaArtista getVentanaArtista() { return panelArtista; }
    public VentanaAlbum getPanelAlbum() { return panelAlbum; } 
    public PanelRegistro getPanelRegistro() { return registro; }
    public VentanaReproducion getVentanaReproduccion() { return panelReproduccion; }
    public VentanaPlaylist getVentanaPlaylist() { return panelPlaylist; } 
    public VentanaPlaylsitPrincipal getVentanaPlaylsitPrincipal() { return panelPlaylistDetalle; }
    public VentanaPoscasterLista getVentanaPoscasterLista() { return panelDescubrirPodcast; }
    public VentanaPoscastLista getVentanaPoscastLista() { return panelListaPodcasts; }
    public PodcastReproducciones getPanelReproductorPodcast() { return panelReproductorPodcast; }
    public PanelDescubrirMúsica getPanelDescubrirMusica() { return descubrirMusica; }
    public VentanaAdministración getPanelAdmin() { return panelAdmin; }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}