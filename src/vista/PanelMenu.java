package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que representa el panel del menú principal de la aplicación.
 * Hereda de {@link JPanel} y se muestra una vez que el usuario ha iniciado sesión correctamente.
 * Contiene las opciones de navegación hacia otras secciones como perfil de usuario, 
 * música, podcasts y listas de reproducción.
 */
public class PanelMenu extends JPanel {

	/** * Identificador de versión para la serialización de la clase (requerido por heredar de JPanel).
	 */
	private static final long serialVersionUID = 1L;
	
	/** * Referencia a la ventana principal de la aplicación para gestionar el cambio entre paneles. 
	 */
	private VentanaPrincipal principal;

	/**
	 * Constructor de la clase PanelMenu.
	 * Inicializa la interfaz gráfica del menú principal, posicionando las etiquetas y botones.
	 * También define los eventos para navegar al perfil del usuario o volver a la pantalla de login.
	 * * @param principal El marco o ventana principal de la aplicación que contiene este panel.
	 */
	public PanelMenu(VentanaPrincipal principal) {
		this.principal = principal; // Guardamos la referencia
		setLayout(null);
		
		JLabel lblELIGE = new JLabel("ELIGE UNA OPCIÓN");
		lblELIGE.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblELIGE.setBounds(125, 0, 197, 60);
		add(lblELIGE);
		
		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.getPanelRegistro().prepararModo(false);
				principal.mostrarPantalla("PANTALLA_REGISTRO");
			}
		});
		btnUsuario.setBounds(351, 21, 89, 23);
		add(btnUsuario);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.mostrarPantalla("PANTALLA_LOGIN");
			}
		});
		btnAtras.setBounds(10, 21, 89, 23);
		add(btnAtras);
		
		JButton btnMusica = new JButton("Descubrir música");
		btnMusica.setBounds(95, 71, 253, 23);
		add(btnMusica);
		
		JButton btnPodcast = new JButton("Descubrir podcast");
		btnPodcast.setBounds(95, 114, 253, 23);
		add(btnPodcast);
		
		JButton btnPlaylist = new JButton("Mis Playlist");
		btnPlaylist.setBounds(95, 155, 253, 23);
		add(btnPlaylist);

	}

}