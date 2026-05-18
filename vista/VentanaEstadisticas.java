package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaEstadisticas extends JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JPanel panelMenuEstadisticas;
    private JPanel panelTablaDetalle;
    
    // Componentes de la tabla
    private JTable tablaDatos;
    private DefaultTableModel modeloTabla;
    private JLabel lblTituloTabla;

    public VentanaEstadisticas(VentanaPrincipal principal) {
        this.principal = principal;
        setLayout(null);
        setBounds(100, 100, 800, 500);
        setBackground(new Color(240, 240, 240));

        // INICIALIZAR LOS DOS SUB-PANELES
        crearPanelMenu();
        crearPanelTablaDetalle();

        // Mostrar el menú por defecto
        mostrarMenu();
    }

    private void crearPanelMenu() {
        panelMenuEstadisticas = new JPanel();
        panelMenuEstadisticas.setLayout(null);
        panelMenuEstadisticas.setBounds(0, 0, 800, 500);
        panelMenuEstadisticas.setBackground(new Color(240, 240, 240));

        JButton btnAtras = new JButton("Volver");
        btnAtras.setBounds(30, 30, 120, 30);
        btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_ADMIN"));
        panelMenuEstadisticas.add(btnAtras);

        JLabel lblTitulo = new JLabel("Estadísticas de Uso");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitulo.setBounds(200, 30, 400, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelMenuEstadisticas.add(lblTitulo);

        // Botones de opciones traducidos
        String[] opciones = {
            "Top Canciones Favoritas", 
            "Top Podcasts Favoritos", 
            "Top Más Escuchados", 
            "Top Playlists"
        };
        
        int yPos = 120;
        for (String opcion : opciones) {
            JButton btn = new JButton(opcion);
            btn.setBounds(250, yPos, 300, 40);
            btn.addActionListener(e -> cargarYMostrarDetalle(opcion));
            panelMenuEstadisticas.add(btn);
            yPos += 60;
        }
        add(panelMenuEstadisticas);
    }

    private void crearPanelTablaDetalle() {
        panelTablaDetalle = new JPanel();
        panelTablaDetalle.setLayout(null);
        panelTablaDetalle.setBounds(0, 0, 800, 500);
        panelTablaDetalle.setBackground(new Color(240, 240, 240));

        JButton btnVolverMenu = new JButton("Atrás");
        btnVolverMenu.setBounds(30, 20, 100, 30);
        btnVolverMenu.addActionListener(e -> mostrarMenu());
        panelTablaDetalle.add(btnVolverMenu);

        lblTituloTabla = new JLabel("Top Más Escuchados");
        lblTituloTabla.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTituloTabla.setBounds(150, 20, 500, 30);
        lblTituloTabla.setHorizontalAlignment(SwingConstants.CENTER);
        panelTablaDetalle.add(lblTituloTabla);

        // Botones de filtro de tiempo
        JButton btnSemana = new JButton("Semana");
        btnSemana.setBounds(350, 60, 90, 25);
        panelTablaDetalle.add(btnSemana);

        JButton btnMes = new JButton("Mes");
        btnMes.setBounds(450, 60, 100, 25);
        panelTablaDetalle.add(btnMes);

        JButton btnAnio = new JButton("Año");
        btnAnio.setBounds(560, 60, 90, 25);
        panelTablaDetalle.add(btnAnio);

        // Tabla con cabeceras en español
        String[] columnas = {"Posición", "Artista/Autor", "Título", "Reproducciones"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaDatos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaDatos);
        scroll.setBounds(50, 100, 700, 300);
        panelTablaDetalle.add(scroll);

        add(panelTablaDetalle);
    }

    private void mostrarMenu() {
        panelMenuEstadisticas.setVisible(true);
        panelTablaDetalle.setVisible(false);
        repaint();
    }

    private void cargarYMostrarDetalle(String tipo) {
        lblTituloTabla.setText(tipo);
        modeloTabla.setRowCount(0); 
        
        // Pasamos el "tipo" (el nombre del botón) al controlador
        String[][] datos = principal.getControlador().obtenerEstadisticas(tipo);
        
        int pos = 1;
        for (String[] fila : datos) {
            // Adaptamos las columnas según lo que devuelve el SQL
            modeloTabla.addRow(new Object[]{ pos++, fila[0], fila[1], fila[2] });
        }

        panelMenuEstadisticas.setVisible(false);
        panelTablaDetalle.setVisible(true);
        repaint();
    }
    // Método para resetear la vista al entrar
    public void cargarEstadisticas() {
        mostrarMenu();
    }
}