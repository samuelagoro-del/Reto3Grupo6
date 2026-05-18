package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PanelMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JButton btnUsuario;

    public PanelMenu(VentanaPrincipal principal) {
        this.principal = principal; 
        
        // Ajustamos el tamaño al estándar de la aplicación
        setLayout(null);
        setBounds(100, 100, 800, 500);
        setBackground(new Color(245, 245, 245));

        JLabel lblELIGE = new JLabel("ELIGE UNA OPCIÓN");
        lblELIGE.setHorizontalAlignment(SwingConstants.CENTER);
        lblELIGE.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblELIGE.setBounds(250, 30, 300, 60);
        add(lblELIGE);
        
        // BOTÓN USUARIO (Perfil)
        btnUsuario = new JButton("Usuario");
        btnUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal.getPanelRegistro().prepararModo(false);
                principal.mostrarPantalla("PANTALLA_REGISTRO");
            }
        });
        btnUsuario.setBounds(650, 20, 110, 30);
        add(btnUsuario);
        
        // BOTÓN ATRÁS (Log out)
        JButton btnAtras = new JButton("Cerrar Sesión");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal.mostrarPantalla("PANTALLA_LOGIN");
            }
        });
        btnAtras.setBounds(40, 20, 130, 30);
        add(btnAtras);
        
        // --- BOTÓN MÚSICA ---
        JButton btnMusica = new JButton("Descubrir música");
        btnMusica.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMusica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal.mostrarPantalla("PANTALLA_DESCUBRIR");
            }
        });
        btnMusica.setBounds(250, 120, 300, 40);
        add(btnMusica);
        
        // --- BOTÓN PODCAST ---
        JButton btnPodcast = new JButton("Descubrir podcast");
        btnPodcast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnPodcast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal.mostrarPantalla("PANTALLA_DESCUBRIR_PODCASTERS");
            }
        });
        btnPodcast.setBounds(250, 190, 300, 40);
        add(btnPodcast);
        
        // --- BOTÓN PLAYLIST (Ahora Conectado) ---
        JButton btnPlaylist = new JButton("Mis Playlists");
        btnPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navegamos a la pantalla que registramos en VentanaPrincipal
                principal.mostrarPantalla("PANTALLA_PLAYLIST");
            }
        });
        btnPlaylist.setBounds(250, 260, 300, 40);
        add(btnPlaylist);

    }

    /**
     * Actualiza el nombre del usuario logueado en el botón
     */
    public void setNombreUsuario(String nombre) {
        btnUsuario.setText(nombre);
    }
}