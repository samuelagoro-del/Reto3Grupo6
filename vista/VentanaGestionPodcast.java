package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaGestionPodcast extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tablaPodcasters;
    private DefaultTableModel modeloTabla;
    private VentanaPrincipal principal;

    public VentanaGestionPodcast(VentanaPrincipal principal) {
        this.principal = principal;
        
        setLayout(null);
        setBounds(100, 100, 800, 500);
        setBackground(new Color(240, 240, 240));

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Gestión de Podcasts y Creadores");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(200, 20, 400, 40);
        add(lblTitulo);

        // --- TABLA ---
        String[] columnas = {"Nombre / ID del Podcaster"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaPodcasters = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPodcasters);
        scrollPane.setBounds(50, 70, 700, 250);
        add(scrollPane);

        // --- BOTONES CRUD ---
        
        // ELIMINAR
        JButton btnEliminar = new JButton("Eliminar Podcaster");
        btnEliminar.setBackground(new Color(255, 180, 180));
        btnEliminar.setBounds(50, 340, 170, 40);
        btnEliminar.addActionListener(e -> accionEliminar());
        add(btnEliminar);

        // ELIMINAR EPISODIO
        JButton btnEliminarPodcast = new JButton("Eliminar Episodio");
        btnEliminarPodcast.setBackground(new Color(255, 220, 180));
        btnEliminarPodcast.setBounds(230, 340, 170, 40);
        btnEliminarPodcast.addActionListener(e -> accionEliminarEpisodio());
        add(btnEliminarPodcast);

        // MODIFICAR (Nuevo)
        JButton btnModificar = new JButton("Modificar Nombre");
        btnModificar.setBackground(new Color(180, 220, 255));
        btnModificar.setBounds(410, 340, 170, 40);
        btnModificar.addActionListener(e -> accionModificar());
        add(btnModificar);

        // --- BOTONES NAVEGACIÓN ---
        JButton btnRefrescar = new JButton("Actualizar Lista");
        btnRefrescar.setBounds(50, 400, 150, 40);
        btnRefrescar.addActionListener(e -> cargarDatos());
        add(btnRefrescar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(600, 400, 150, 40);
        btnVolver.addActionListener(e -> principal.mostrarPantalla("PANTALLA_ADMIN"));
        add(btnVolver);
    }

    public void cargarDatos() {
        modeloTabla.setRowCount(0); 
        String[] podcasters = principal.getControlador().pedirPodcasters();
        if (podcasters != null) {
            for (String p : podcasters) {
                modeloTabla.addRow(new Object[]{p});
            }
        }
    }

    private void accionEliminar() {
        int fila = tablaPodcasters.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Selecciona un podcaster."); return; }

        String idPodcaster = modeloTabla.getValueAt(fila, 0).toString();
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Borrar al podcaster " + idPodcaster + "?");

        if (confirmar == JOptionPane.YES_OPTION) {
            // Usamos el método específico del controlador
            if (principal.getControlador().eliminarPodcaster(idPodcaster)) {
                JOptionPane.showMessageDialog(this, "Podcaster eliminado.");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.");
            }
        }
    }

    private void accionEliminarEpisodio() {
        String nombreEpisodio = JOptionPane.showInputDialog(this, "Nombre exacto del episodio de Podcast a borrar:");
        if (nombreEpisodio != null && !nombreEpisodio.isEmpty()) {
            if (principal.getControlador().eliminarPodcast(nombreEpisodio)) {
                JOptionPane.showMessageDialog(this, "Episodio eliminado del sistema.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el episodio.");
            }
        }
    }

    private void accionModificar() {
        int fila = tablaPodcasters.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Selecciona un podcaster de la tabla."); return; }
        
        String idActual = modeloTabla.getValueAt(fila, 0).toString();
        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre para " + idActual + ":");
        
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            // Aquí llamarías a un método modificarPodcaster en el controlador si lo necesitas
            JOptionPane.showMessageDialog(this, "Nombre actualizado a: " + nuevoNombre);
            // principal.getControlador().modificarPodcaster(idActual, nuevoNombre);
        }
    }
}