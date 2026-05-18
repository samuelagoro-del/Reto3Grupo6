package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Artista;

public class PanelDescubrirMúsica extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal principal;
	private JList<Artista> lista;
	private Artista artistaSeleccionado;
	private JButton btnUsuario;

	public PanelDescubrirMúsica(Artista[] array, VentanaPrincipal principal) {
		this.principal = principal; 
		
		setBounds(100, 100, 800, 500);
		setLayout(null);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(240, 240, 240));
		
		// --- BOTÓN ATRÁS ---
		JButton btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_MENU"));
		btnAtras.setBounds(20, 20, 100, 30);
		add(btnAtras);

		// --- BOTÓN USUARIO ---
		btnUsuario = new JButton("Usuario");
		btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnUsuario.addActionListener(e -> principal.mostrarPantalla("PANTALLA_REGISTRO"));
		btnUsuario.setBounds(660, 20, 100, 30);
		add(btnUsuario);

		JLabel lblTitulo = new JLabel("DESCUBRIR ARTISTAS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));	
		lblTitulo.setBounds(250, 25, 300, 25);
		add(lblTitulo);

		// --- LISTA DE ARTISTAS ---
		lista = new JList<>(array);
		lista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lista.addListSelectionListener(this); 
		
		JScrollPane scrollPane = new JScrollPane(lista);
		scrollPane.setBounds(150, 80, 500, 350);
		add(scrollPane);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting()) return;
	    
	    artistaSeleccionado = lista.getSelectedValue();
	    if (artistaSeleccionado == null) return;
	    
	    String idArtista = artistaSeleccionado.getIdArtista();
	    
	    // 1. Pedir discos e info al controlador
	    String[] discos = principal.getControlador().pedirAlbumsPorArtista(idArtista);
	    String[] info = principal.getControlador().pedirAlbumsPorArtistaDatos(idArtista);
	    
	    // 2. Preparar la siguiente ventana con los datos cargados
	    if (principal.getVentanaArtista() != null) {
	        principal.getVentanaArtista().actualizarDatos(discos, info);
	    }
	    
	    // 3. Cambiar de pantalla
	    principal.mostrarPantalla("PANTALLA_ARTISTA");
	    
	    // Limpiar selección para que el usuario pueda volver a entrar al mismo artista si quiere
	    lista.clearSelection();
	}

	/**
	 * Permite actualizar la lista de artistas sin recrear el panel
	 */
	public void actualizarListaArtistas(Artista[] nuevosArtistas) {
		if (nuevosArtistas != null) {
			lista.setListData(nuevosArtistas);
		}
	}
	
	public void setNombreUsuario(String nombre) {
		btnUsuario.setText(nombre);
	}
}