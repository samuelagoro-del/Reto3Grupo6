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

    // Paneles de Podcast
    private VentanaPoscasterLista panelDescubrirPodcast;
    private VentanaPoscastLista panelListaPodcasts;
    private PodcastReproducciones panelReproductorPodcast; // <--- Nuevo

    public VentanaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450); 
        setTitle("Reproductor de Música y Podcasts");
        
        miControlador = new Controlador();
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // 1. Inicializar pantallas existentes
        PanelLogin login = new PanelLogin(this, miControlador);
        registro = new PanelRegistro(this, miControlador);
        PanelMenu menu = new PanelMenu(this);
        
        Artista[] arrayArtistas = miControlador.artistas();
        PanelDescubrirMúsica descubrirMusica = new PanelDescubrirMúsica(arrayArtistas, this);
        
        panelArtista = new VentanaArtista(this); 
        panelAlbum = new VentanaAlbum(this); 

        // 2. Inicializar pantallas de Podcast
        panelDescubrirPodcast = new VentanaPoscasterLista(this);
        panelListaPodcasts = new VentanaPoscastLista(this);
        panelReproductorPodcast = new PodcastReproducciones(this); // <--- Inicialización

        // 3. Añadir al contenedor con NOMBRES ÚNICOS
        contenedor.add(login, "PANTALLA_LOGIN");
        contenedor.add(registro, "PANTALLA_REGISTRO");
        contenedor.add(menu, "PANTALLA_MENU");
        contenedor.add(descubrirMusica, "PANTALLA_DESCUBRIR");
        contenedor.add(panelArtista, "PANTALLA_ARTISTA"); 
        contenedor.add(panelAlbum, "PANTALLA_ALBUM");     
        
        // Añadir pantallas de Podcast al CardLayout
        contenedor.add(panelDescubrirPodcast, "PANTALLA_DESCUBRIR_PODCASTERS");
        contenedor.add(panelListaPodcasts, "PANTALLA_LISTA_PODCASTS");
        contenedor.add(panelReproductorPodcast, "PANTALLA_REPRODUCCION_PODCAST"); // <--- Registro

        getContentPane().add(contenedor);
        setVisible(true);
    }

    public void mostrarPantalla(String nombre) {
        // Lógica especial: si vamos a la pantalla de podcasters, cargamos los datos
        if (nombre.equals("PANTALLA_DESCUBRIR_PODCASTERS")) {
            panelDescubrirPodcast.cargarPodcasters(miControlador.pedirPodcasters());
        }
        cardLayout.show(contenedor, nombre);
    }

    // --- GETTERS ---
    public Controlador getControlador() { return miControlador; }
    public VentanaArtista getVentanaArtista() { return panelArtista; }
    public VentanaAlbum getPanelAlbum() { return panelAlbum; } 
    public PanelRegistro getPanelRegistro() { return registro; }
    
    // Getters para Podcast
    public VentanaPoscasterLista getVentanaPoscasterLista() { return panelDescubrirPodcast; }
    public VentanaPoscastLista getVentanaPoscastLista() { return panelListaPodcasts; }
    public PodcastReproducciones getPanelReproductorPodcast() { return panelReproductorPodcast; } // <--- Nuevo Getter

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}