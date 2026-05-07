package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal principal;

	/**
	 * Create the panel.
	 */
	public PanelMenu(VentanaPrincipal principal) {
		this.principal = principal; 
		setLayout(null);
		setBounds(100, 100, 441, 280);

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
		btnMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.mostrarPantalla("PANTALLA_DESCUBRIR");
			}
		});
		btnMusica.setBounds(95, 71, 253, 23);
		add(btnMusica);
		
		// --- BOTÓN CONECTADO PARA PODCAST ---
		JButton btnPodcast = new JButton("Descubrir podcast");
		btnPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamamos a la pantalla de la lista de podcasters
				principal.mostrarPantalla("PANTALLA_DESCUBRIR_PODCASTERS");
			}
		});
		btnPodcast.setBounds(95, 114, 253, 23);
		add(btnPodcast);
		
		JButton btnPlaylist = new JButton("Mis Playlist");
		btnPlaylist.setBounds(95, 155, 253, 23);
		add(btnPlaylist);

	}
}