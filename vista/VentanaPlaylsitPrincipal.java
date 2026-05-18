package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPlaylsitPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JTable tablaCanciones;
    private DefaultTableModel modeloTabla;
    private JLabel lblNombrePlaylist;
    private String playlistActual;

    public VentanaPlaylsitPrincipal(VentanaPrincipal principal) {
        this.principal = principal;
        
        setBounds(100, 100, 800, 500);
        setLayout(null);
        setBackground(new Color(240, 240, 240));

        // --- CABECERA ---
        JButton btnAtzera = new JButton("Atras");
        btnAtzera.setBounds(10, 11, 89, 23);
        btnAtzera.addActionListener(e -> principal.mostrarPantalla("PANTALLA_PLAYLIST"));
        add(btnAtzera);

        lblNombrePlaylist = new JLabel();
        lblNombrePlaylist.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNombrePlaylist.setBounds(110, 11, 400, 23);
        add(lblNombrePlaylist);

        JButton btnOler = new JButton("Usuario");
        btnOler.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                principal.mostrarPantalla("PANTALLA_REGISTRO");

        	}
        });
        btnOler.setBounds(685, 11, 89, 23);
        add(btnOler);

        // --- TABLA DE CANCIONES ---
        // Definimos las columnas: Título y Acción (el botón de borrar)
        String[] columnas = {"Canción", "Eliminar"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No queremos editar el texto directamente
            }
        };

        tablaCanciones = new JTable(modeloTabla);
        tablaCanciones.setRowHeight(35);
        tablaCanciones.getColumnModel().getColumn(1).setMaxWidth(100); // Columna pequeña para el botón

        // Evento de clic en la tabla
        tablaCanciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaCanciones.getSelectedRow();
                int columna = tablaCanciones.getSelectedColumn();
                
                if (fila != -1) {
                    String nombreCancion = (String) modeloTabla.getValueAt(fila, 0);
                    
                    // Si pulsa en la columna 1 (donde está el símbolo + o borrar)
                    if (columna == 1) {
                        eliminarCancionDeLista(nombreCancion);
                    } else {
                        // Si pulsa en el nombre, va a reproducción
                        irAReproduccion(nombreCancion);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaCanciones);
        scrollPane.setBounds(10, 50, 764, 400);
        add(scrollPane);
    }

    /**
     * Carga las canciones de la playlist seleccionada
     */
    public void cargarDatos(String nombrePlaylist) {
        this.playlistActual = nombrePlaylist;
        lblNombrePlaylist.setText(nombrePlaylist);
        modeloTabla.setRowCount(0); // Limpiar tabla

        ArrayList<String> canciones = principal.getControlador().getModelo().obtenerCancionesDePlaylist(nombrePlaylist);
        
        for (String c : canciones) {
            // Añadimos la fila: [Nombre de canción, Símbolo/Botón]
            modeloTabla.addRow(new Object[]{c, "   -   "}); 
        }
    }

    private void eliminarCancionDeLista(String cancion) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar " + cancion + " de la lista?");
        if (confirm == JOptionPane.YES_OPTION) {
            // Aquí llamarías a un método del controlador para borrar la relación en la BD
            // principal.getControlador().eliminarCancionDePlaylist(cancion, playlistActual);
            cargarDatos(playlistActual); // Refrescar
        }
    }

    private void irAReproduccion(String cancion) {
        // Obtenemos todas las canciones actuales de la tabla para la cola de reproducción
        String[] cola = new String[modeloTabla.getRowCount()];
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            cola[i] = (String) modeloTabla.getValueAt(i, 0);
        }
        
        principal.getVentanaReproduccion().actualizarListaReproduccion(cola);
        // Opcional: buscar la posición específica de la canción pulsada
        principal.mostrarPantalla("PANTALLA_REPRODUCCION");
    }
}