package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Artista;

public class PanelDescubrirMúsica extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal principal;
	private JList<Artista> lista;
	private Artista artistaSeleccionado;

	public PanelDescubrirMúsica(Artista[] array, VentanaPrincipal principal) {
		this.principal = principal; 
		
		setBounds(100, 100, 441, 280);
		setLayout(null);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.mostrarPantalla("PANTALLA_MENU");
			}
		});
		btnAtras.setBounds(10, 21, 78, 23);
		add(btnAtras);

		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.getPanelRegistro().prepararModo(false);
				principal.mostrarPantalla("PANTALLA_REGISTRO");
			}
		});
		btnUsuario.setBounds(346, 21, 78, 23);
		add(btnUsuario);

		JLabel lblNewLabel = new JLabel("Lista de artistas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));	
		lblNewLabel.setBounds(109, 25, 204, 14);
		add(lblNewLabel);

		// Lista de artistas
		lista = new JList<>(array);
		lista.addListSelectionListener(this); // Aquí "enchufamos" el evento del clic
		
		JScrollPane scrollPane = new JScrollPane(lista);
		scrollPane.setBounds(98, 50, 215, 173);
		add(scrollPane);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    // 1. Evitamos el doble evento
	    if (e.getValueIsAdjusting()) return;
	    
	    // 2. Obtenemos el artista de la lista
	    artistaSeleccionado = lista.getSelectedValue();
	    if (artistaSeleccionado == null) return;
	    
	    // Debug para consola
	    System.out.println("Cargando perfil de: " + artistaSeleccionado.getNombreArtistico());

	    String idArtista = artistaSeleccionado.getIdArtista();
	    
	    // 3. Obtenemos los datos necesarios desde el controlador
	    // Los nombres de los álbumes para la JList de la VentanaArtista
	    String[] discos = principal.getControlador().pedirAlbumsPorArtista(idArtista);
	    // La info del artista (Nombre, Imagen, Bio)
	    String[] info = principal.getControlador().pedirAlbumsPorArtistaDatos(idArtista);
	    
	    // 4. PASO CLAVE: Actualizamos la VentanaArtista ANTES de mostrarla
	    // Usamos el panelArtista que tienes en la Principal
	    principal.getVentanaArtista().actualizarDatos(discos, info);
	    
	    // 5. CAMBIO DE PANTALLA: Usamos el nombre que pusiste en VentanaPrincipal
	    // En tu VentanaPrincipal pusiste: contenedor.add(panelArtista, "PANTALLA_ALBUMES");
	    principal.mostrarPantalla("PANTALLA_ARTISTA");
	    
	    // 6. Limpiamos la lista para que el usuario pueda volver a pulsar
	    lista.clearSelection();
	}
}