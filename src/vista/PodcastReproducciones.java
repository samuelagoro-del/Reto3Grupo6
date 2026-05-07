package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PodcastReproducciones extends JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JLabel lblNombrePodcast;
    private JLabel lblImagen;
    private JComboBox<String> comboVelocidad;

    public PodcastReproducciones(VentanaPrincipal principal) {
        this.principal = principal;
        
        setBounds(100, 100, 650, 450);
        setLayout(null);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        // Botón Atrás
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(10, 10, 85, 25);
        btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_LISTA_PODCASTS"));
        add(btnAtras);
        
        JButton btnUsuario = new JButton("Usuario");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.getPanelRegistro().prepararModo(false);
				principal.mostrarPantalla("PANTALLA_REGISTRO");
			}
		});
		btnUsuario.setBounds(351, 21, 89, 23);
		add(btnUsuario);

        // Título del Podcast
        lblNombrePodcast = new JLabel("Nombre del Podcast");
        lblNombrePodcast.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNombrePodcast.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombrePodcast.setBounds(100, 40, 450, 30);
        add(lblNombrePodcast);

        // Imagen del Podcaster/Podcast
        lblImagen = new JLabel("Cargando imagen...");
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblImagen.setBounds(200, 80, 250, 200);
        add(lblImagen);

        // Slider de reproducción (Simulado)
        JSlider sliderProgreso = new JSlider();
        sliderProgreso.setBounds(150, 300, 350, 25);
        sliderProgreso.setValue(0);
        add(sliderProgreso);

        // --- CONTROLES ---
        JButton btnPlay = new JButton("▶");
        btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnPlay.setBounds(290, 340, 60, 40);
        add(btnPlay);

        JButton btnStop = new JButton("■");
        btnStop.setBounds(360, 340, 60, 40);
        add(btnStop);

        // --- REQUISITO: VELOCIDAD DE REPRODUCCIÓN ---
        JLabel lblVel = new JLabel("Velocidad:");
        lblVel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblVel.setBounds(480, 340, 80, 20);
        add(lblVel);

        String[] velocidades = {"0.5x", "1x", "1.5x"};
        comboVelocidad = new JComboBox<>(velocidades);
        comboVelocidad.setSelectedIndex(1); // 1x por defecto
        comboVelocidad.setBounds(480, 360, 70, 25);
        add(comboVelocidad);

        // Acción de la velocidad
        comboVelocidad.addActionListener(e -> {
            String sel = (String) comboVelocidad.getSelectedItem();
            System.out.println("Velocidad ajustada a: " + sel);
        });
    }

    /**
     * Método para preparar la interfaz con los datos del podcast seleccionado
     */
    public void prepararReproduccion(String nombre, String urlImagen) {
        lblNombrePodcast.setText(nombre);
        
        // Carga de imagen en hilo secundario
        new Thread(() -> {
            try {
                if (urlImagen != null && !urlImagen.isEmpty()) {
                    URL url = new URL(urlImagen);
                    ImageIcon icon = new ImageIcon(url);
                    Image img = icon.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
                    lblImagen.setIcon(new ImageIcon(img));
                    lblImagen.setText("");
                }
            } catch (Exception e) {
                lblImagen.setText("Sin Imagen");
            }
        }).start();
    }
}