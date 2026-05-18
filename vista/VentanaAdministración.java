package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controlador.Controlador;

public class VentanaAdministración extends JPanel {

    private static final long serialVersionUID = 1L;
    private Controlador miControlador;
    private VentanaPrincipal principal;

    public VentanaAdministración(VentanaPrincipal principal, Controlador miControlador) {
        this.principal = principal;
        this.miControlador = miControlador;
        
        setLayout(null);
        setBounds(100, 100, 800, 500); 
        setBackground(new Color(240, 240, 240));

        // --- BOTÓN ATRÁS (LOGOUT) ---
        JButton btnAtras = new JButton("Cerrar Sesión");
        btnAtras.setBounds(30, 30, 130, 30);
        btnAtras.addActionListener(e -> {
            miControlador.logout(); // Limpiamos la sesión
            principal.mostrarPantalla("PANTALLA_LOGIN");
        });
        add(btnAtras);

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Menú de Administración");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(200, 80, 400, 50);
        add(lblTitulo);

        // --- BOTÓN GESTIONAR MÚSICA ---
        JButton btnMusica = new JButton("Gestionar Música");
        btnMusica.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnMusica.setBounds(250, 180, 300, 40);
        btnMusica.addActionListener(e -> principal.mostrarPantalla("GESTION_MUSICA"));
        add(btnMusica);

        // --- BOTÓN GESTIONAR PODCAST ---
        JButton btnPodcast = new JButton("Gestionar Podcast");
        btnPodcast.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnPodcast.setBounds(250, 250, 300, 40);
        btnPodcast.addActionListener(e -> principal.mostrarPantalla("GESTION_PODCAST"));
        add(btnPodcast);

        // --- BOTÓN ESTADÍSTICAS ---
        JButton btnEstadisticas = new JButton("Ver Estadísticas");
        btnEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnEstadisticas.setBounds(250, 320, 300, 40);
        btnEstadisticas.addActionListener(e -> principal.mostrarPantalla("VENTANA_ESTADISTICAS"));
        add(btnEstadisticas);
    }
}