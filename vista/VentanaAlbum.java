package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VentanaAlbum extends JPanel implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JList<String> listaCanciones;
    
    private JLabel lblTitulo;
    private JLabel lblColab;
    private JLabel lblFecha;
    private JLabel lblNumCanciones;
    private JLabel lblDuracionTotal;
    private JLabel lblPortada;
    
    private String rutaImagenActual; 

    public VentanaAlbum(VentanaPrincipal principal) {
        this.principal = principal;
        
        setBounds(100, 100, 650, 450);
        setLayout(null);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnAtras.setBounds(10, 10, 85, 25);
        btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_ARTISTA")); 
        add(btnAtras);
        
        JButton btnUsuario = new JButton("Usuario");
        btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnUsuario.setBounds(346, 21, 78, 23);
        btnUsuario.addActionListener(e -> {
            principal.getPanelRegistro().prepararModo(false);
            principal.mostrarPantalla("PANTALLA_REGISTRO");
        });
        add(btnUsuario);

        lblTitulo = new JLabel("Álbum: ");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTitulo.setBounds(110, 15, 400, 20);
        add(lblTitulo);

        // --- PANEL IZQUIERDO: CANCIONES ---
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(null);
        panelIzquierdo.setBounds(15, 50, 260, 350);
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Lista de canciones"));
        
        listaCanciones = new JList<>();
        listaCanciones.addListSelectionListener(this);
        
        JScrollPane scrollLista = new JScrollPane(listaCanciones);
        scrollLista.setBounds(15, 25, 230, 310);
        panelIzquierdo.add(scrollLista);
        add(panelIzquierdo);

        // --- PANEL DERECHO: INFORMACIÓN ---
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(null);
        panelDerecho.setBounds(290, 50, 330, 350);
        panelDerecho.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Información del álbum"));

        lblColab = new JLabel("Colaboradores: ");
        lblColab.setBounds(20, 30, 280, 20);
        panelDerecho.add(lblColab);

        lblFecha = new JLabel("Fecha de publicación: ");
        lblFecha.setBounds(20, 55, 280, 20);
        panelDerecho.add(lblFecha);

        lblNumCanciones = new JLabel("Número de canciones: ");
        lblNumCanciones.setBounds(20, 80, 280, 20);
        panelDerecho.add(lblNumCanciones);

        lblDuracionTotal = new JLabel("Duración total: ");
        lblDuracionTotal.setBounds(20, 105, 280, 20);
        panelDerecho.add(lblDuracionTotal);

        lblPortada = new JLabel("Sin Imagen");
        lblPortada.setHorizontalAlignment(JLabel.CENTER);
        lblPortada.setBounds(40, 140, 250, 190);
        lblPortada.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDerecho.add(lblPortada);
        
        add(panelDerecho);
    }

    public void actualizarDatosAlbum(String[] datos) {
        if (datos != null && datos.length >= 5) {
            lblTitulo.setText("Álbum: " + datos[0]);
            lblColab.setText("Colaboradores: " + (datos[4] != null ? datos[4] : "Ninguno"));
            lblFecha.setText("Fecha de publicación: " + datos[1]);
            this.rutaImagenActual = datos[3]; 

            String[] canciones = principal.getControlador().pedirCancionesDeAlbum(datos[0]);
            
            if (canciones != null && canciones.length > 0) {
                listaCanciones.setListData(canciones);
                lblNumCanciones.setText("Número de canciones: " + canciones.length);
                int totalSegundos = canciones.length * 210; 
                lblDuracionTotal.setText(String.format("Duración total: %d:%02d", totalSegundos / 60, totalSegundos % 60));
            } else {
                listaCanciones.setListData(new String[]{"No hay canciones en la BD"});
            }

            try {
                if (rutaImagenActual != null && !rutaImagenActual.isEmpty()) {
                    ImageIcon icon = new ImageIcon(rutaImagenActual);
                    if (icon.getIconWidth() > 0) {
                        Image img = icon.getImage().getScaledInstance(250, 190, Image.SCALE_SMOOTH);
                        lblPortada.setIcon(new ImageIcon(img));
                        lblPortada.setText(""); 
                    } else {
                        lblPortada.setIcon(null);
                        lblPortada.setText("No encontrada");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String seleccion = listaCanciones.getSelectedValue();
            if (seleccion != null && !seleccion.equals("No hay canciones en la BD")) {
                
                String nombreAlbum = lblTitulo.getText().replace("Álbum: ", "");
                
                // 1. Pedimos los datos de la canción seleccionada
                String[] datosAudio = principal.getControlador().pedirDatosReproduccion(seleccion);
                
                // 2. IMPORTANTE: Obtenemos el array con TODAS las canciones del álbum
                // para que los botones Siguiente/Anterior funcionen
                String[] todasLasCanciones = principal.getControlador().pedirCancionesDeAlbum(nombreAlbum);
                
                if (datosAudio != null && datosAudio.length >= 3) {
                    int duracionReal = Integer.parseInt(datosAudio[1]); 
                    String rutaMp3 = datosAudio[2];                     

                    if (principal.getVentanaReproduccion() != null) {
                        // 3. Enviamos todos los datos incluyendo la lista completa (todasLasCanciones)
                        principal.getVentanaReproduccion().cargarDatosAudio(
                            seleccion, 
                            nombreAlbum, 
                            rutaImagenActual, 
                            duracionReal,
                            rutaMp3,
                            todasLasCanciones 
                        );
                        principal.mostrarPantalla("PANTALLA_REPRODUCCION");
                    }
                }
                listaCanciones.clearSelection();
            }
        }
    }
}