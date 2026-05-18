package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Artista;

public class VentanaGestionMusica extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tablaArtistas;
    private DefaultTableModel modeloTabla;
    private VentanaPrincipal principal;

    public VentanaGestionMusica(VentanaPrincipal principal) {
        this.principal = principal;
        
        setLayout(null);
        setBounds(100, 100, 800, 500);
        setBackground(new Color(240, 240, 240));

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Gestión de Música (Admin)");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(50, 20, 600, 40);
        add(lblTitulo);

        // --- TABLA ---
        String[] columnas = {"ID / Titulo", "Nombre / Info", "Género / Año"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaArtistas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaArtistas);
        scrollPane.setBounds(50, 70, 700, 250);
        add(scrollPane);

        // --- BOTONES ELIMINAR ---
        JButton btnEliminarArtista = new JButton("Eliminar Artista");
        btnEliminarArtista.setBackground(new Color(255, 150, 150));
        btnEliminarArtista.setBounds(50, 330, 150, 35);
        btnEliminarArtista.addActionListener(e -> accionEliminar());
        add(btnEliminarArtista);

        JButton btnEliminarAlbum = new JButton("Eliminar Álbum");
        btnEliminarAlbum.setBackground(new Color(255, 200, 150));
        btnEliminarAlbum.setBounds(210, 330, 150, 35);
        btnEliminarAlbum.addActionListener(e -> accionEliminarAlbum());
        add(btnEliminarAlbum);

        JButton btnEliminarCancion = new JButton("Eliminar Canción");
        btnEliminarCancion.setBackground(new Color(255, 230, 150));
        btnEliminarCancion.setBounds(370, 330, 150, 35);
        btnEliminarCancion.addActionListener(e -> accionEliminarCancion());
        add(btnEliminarCancion);

        // --- BOTÓN MODIFICAR (Nuevo) ---
        JButton btnModificar = new JButton("Modificar Artista");
        btnModificar.setBackground(new Color(150, 200, 255));
        btnModificar.setBounds(530, 330, 150, 35);
        btnModificar.addActionListener(e -> accionModificar());
        add(btnModificar);

        // --- BOTONES NAVEGACIÓN ---
        JButton btnRefrescar = new JButton("Refrescar Tabla");
        btnRefrescar.setBounds(50, 400, 150, 40);
        btnRefrescar.addActionListener(e -> cargarDatos());
        add(btnRefrescar);

        JButton btnVolver = new JButton("Volver al Menú Admin");
        btnVolver.setBounds(550, 400, 200, 40);
        btnVolver.addActionListener(e -> principal.mostrarPantalla("PANTALLA_ADMIN"));
        add(btnVolver);
    }

    public void cargarDatos() {
        modeloTabla.setRowCount(0);
        Artista[] artistas = principal.getControlador().artistas();
        for (Artista a : artistas) {
            modeloTabla.addRow(new Object[]{ a.getIdArtista(), a.getNombreArtistico(), a.getGenero() });
        }
    }

    private void accionEliminar() {
        int fila = tablaArtistas.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Selecciona un Artista."); return; }
        String id = (String) modeloTabla.getValueAt(fila, 0);
        if (principal.getControlador().eliminarArtista(id)) {
            JOptionPane.showMessageDialog(this, "Artista borrado.");
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Verifique integridad (¿Tiene álbumes?).");
        }
    }

    private void accionEliminarAlbum() {
        String titulo = JOptionPane.showInputDialog(this, "Título del álbum a borrar:");
        if (titulo != null && !titulo.isEmpty()) {
            if (principal.getControlador().eliminarAlbum(titulo)) {
                JOptionPane.showMessageDialog(this, "Álbum eliminado.");
                cargarDatos();
            }
        }
    }

    private void accionEliminarCancion() {
        String cancion = JOptionPane.showInputDialog(this, "Nombre de la canción a borrar:");
        if (cancion != null && !cancion.isEmpty()) {
            if (principal.getControlador().eliminarCancion(cancion)) {
                JOptionPane.showMessageDialog(this, "Canción eliminada del sistema.");
            }
        }
    }

    private void accionModificar() {
        int fila = tablaArtistas.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Selecciona un artista de la tabla."); return; }
        
        String id = (String) modeloTabla.getValueAt(fila, 0);
        String nombre = JOptionPane.showInputDialog(this, "Nuevo Nombre Artístico:");
        String genero = JOptionPane.showInputDialog(this, "Nuevo Género:");

        if (nombre != null && genero != null) {
            if (principal.getControlador().modificarArtista(id, nombre, genero)) {
                JOptionPane.showMessageDialog(this, "Datos actualizados.");
                cargarDatos();
            }
        }
    }
}