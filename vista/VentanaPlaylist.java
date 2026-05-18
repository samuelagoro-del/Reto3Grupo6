package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VentanaPlaylist extends JPanel implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JList<String> listaPlaylists;
    private JButton btnAtras, btnUsuario, btnCrear, btnBorrar, btnImportar, btnExportar;

    public VentanaPlaylist(VentanaPrincipal principal) {
        this.principal = principal;
        
        setBounds(100, 100, 800, 500);
        setLayout(null);
        setBackground(new Color(230, 230, 230));

        JLabel lblTitulo = new JLabel("Mi lista de Playlists");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(20, 10, 200, 25);
        add(lblTitulo);

        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(40, 50, 100, 30);
        btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_MENU")); 
        add(btnAtras);

        btnUsuario = new JButton("Usuario");
        btnUsuario.setBounds(650, 50, 100, 30);
        btnUsuario.addActionListener(e -> {
            principal.mostrarPantalla("PANTALLA_REGISTRO");
        });
        add(btnUsuario);

        // --- LISTADO DE PLAYLISTS ---
        listaPlaylists = new JList<>();
        listaPlaylists.addListSelectionListener(this); 
        
        JScrollPane scrollPane = new JScrollPane(listaPlaylists);
        scrollPane.setBounds(40, 100, 350, 330);
        scrollPane.setBorder(new LineBorder(Color.DARK_GRAY));
        add(scrollPane);

        // --- BOTÓN CREAR (Con validación de límite de 3 para Free) ---
        btnCrear = new JButton("Crear nueva");
        btnCrear.setBounds(450, 120, 180, 35);
        btnCrear.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre de la nueva playlist:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                // El controlador ahora devuelve un boolean si se permite crear (limite de 3)
                boolean exito = principal.getControlador().crearNuevaPlaylist(nombre);
                
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Playlist '" + nombre + "' creada.");
                    refrescarInterfaz();
                } else {
                    JOptionPane.showMessageDialog(this, "Límite de 3 playlists alcanzado. ¡Pásate a Premium!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(btnCrear);

        // --- BOTÓN BORRAR ---
        btnBorrar = new JButton("Borrar playlist");
        btnBorrar.setBounds(450, 180, 180, 35);
        btnBorrar.addActionListener(e -> {
            String seleccionada = listaPlaylists.getSelectedValue();
            if (seleccionada != null) {
                int confirmar = JOptionPane.showConfirmDialog(this, "¿Borrar " + seleccionada + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    principal.getControlador().borrarPlaylist(seleccionada);
                    refrescarInterfaz();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una playlist primero");
            }
        });
        add(btnBorrar);

        // --- BOTÓN IMPORTAR ---
        btnImportar = new JButton("Importar");
        btnImportar.setBounds(450, 240, 180, 35);
        btnImportar.addActionListener(e -> {
            principal.getControlador().importarPlaylistsDesdeFichero();
            refrescarInterfaz();
        });
        add(btnImportar);

        // --- BOTÓN EXPORTAR ---
        btnExportar = new JButton("Exportar");
        btnExportar.setBounds(450, 300, 180, 35);
        btnExportar.addActionListener(e -> {
            String seleccionada = listaPlaylists.getSelectedValue();
            if (seleccionada != null) {
                principal.getControlador().exportarPlaylistAFichero(seleccionada);
                JOptionPane.showMessageDialog(this, "Playlist exportada correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una playlist para exportar");
            }
        });
        add(btnExportar);
    }

    /**
     * Método centralizado para pedir las playlists al controlador y actualizar el JList
     */
    public void refrescarInterfaz() {
        String[] playlists = principal.getControlador().pedirPlaylistsUsuario();
        actualizarLista(playlists);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String playlistSeleccionada = listaPlaylists.getSelectedValue();
            if (playlistSeleccionada != null) {
                // 1. Cargamos los datos en la ventana de detalle (VentanaPlaylsitPrincipal)
                principal.getVentanaPlaylsitPrincipal().cargarDatos(playlistSeleccionada);
                
                // 2. Cambiamos de pantalla
                principal.mostrarPantalla("PANTALLA_PLAYLIST_DETALLE");
                
                // Limpiamos la selección para que al volver la lista esté limpia
                listaPlaylists.clearSelection();
            }
        }
    }

    public void cargarPlaylists(String[] nombres) {
        actualizarLista(nombres);
    }

    public void actualizarLista(String[] nombres) {
        if (nombres != null) {
            listaPlaylists.setListData(nombres);
        } else {
            listaPlaylists.setListData(new String[0]);
        }
    }

    public void setNombreUsuario(String nombre) {
        btnUsuario.setText(nombre);
    }
}