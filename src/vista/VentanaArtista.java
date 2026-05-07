package vista;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class VentanaArtista extends JPanel implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JList<String> list_discos;
    private JTextArea txt_Info;
    private JLabel lblFoto;
    private JLabel lblNombreArtistaTop;

    public VentanaArtista(VentanaPrincipal principal) {
        this.principal = principal; 
        setBackground(new Color(240, 240, 240));
		setBounds(100, 100, 441, 280);
        setLayout(null);
        
        // Botón Atrás
        JButton btnAtras = new JButton("Atzera");
        btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnAtras.setBounds(10, 10, 75, 20);
        btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_DESCUBRIR"));
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

        // Nombre del Artista (Título)
        lblNombreArtistaTop = new JLabel("");
        lblNombreArtistaTop.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNombreArtistaTop.setBounds(100, 8, 300, 25);
        add(lblNombreArtistaTop);

        // --- PANEL IZQUIERDO: DISCOS ---
        JPanel panelDiscos = new JPanel();
        panelDiscos.setLayout(null);
        panelDiscos.setBounds(10, 40, 200, 245);
        panelDiscos.setBorder(new TitledBorder(new LineBorder(new Color(180, 200, 230)), "Lista de álbumes"));
        add(panelDiscos);

        list_discos = new JList<>();
        list_discos.setFont(new Font("Tahoma", Font.PLAIN, 11));
        list_discos.addListSelectionListener(this); // Listener para el clic
        
        JScrollPane scrollDiscos = new JScrollPane(list_discos);
        scrollDiscos.setBounds(10, 20, 180, 215);
        panelDiscos.add(scrollDiscos);

        // --- PANEL DERECHO: INFO ---
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setBounds(220, 40, 215, 245);
        panelInfo.setBorder(new TitledBorder(new LineBorder(new Color(180, 200, 230)), "Información"));
        add(panelInfo);

        txt_Info = new JTextArea();
        txt_Info.setEditable(false);
        txt_Info.setLineWrap(true);
        txt_Info.setWrapStyleWord(true);
        txt_Info.setBackground(new Color(255, 255, 255));
        txt_Info.setFont(new Font("Tahoma", Font.PLAIN, 10));
        
        JScrollPane scrollInfo = new JScrollPane(txt_Info);
        scrollInfo.setBounds(10, 20, 195, 90); 
        panelInfo.add(scrollInfo);

        lblFoto = new JLabel("Cargando...");
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto.setBorder(new LineBorder(new Color(200, 200, 200)));
        lblFoto.setBounds(10, 115, 195, 120); 
        panelInfo.add(lblFoto);
    }

    public void actualizarDatos(String[] albumes, String[] infoExtra) {
        list_discos.setListData(albumes);

        if (infoExtra != null && infoExtra.length >= 3) {
            lblNombreArtistaTop.setText(infoExtra[0]); 
            txt_Info.setText(infoExtra[2]);           

            // La ruta que viene de la base de datos (ej: src/imagenes/artistas/...)
            String rutaRelativa = infoExtra[1]; 
            
            try {
                // Cargamos la imagen localmente
                ImageIcon icon = new ImageIcon(rutaRelativa);
                
                // Verificamos si la imagen existe y se ha cargado
                if (icon.getImageLoadStatus() == MediaTracker.COMPLETE || icon.getIconWidth() > 0) {
                    // Escalamos a 195x120 (el tamaño que definiste en el setBounds del lblFoto)
                    Image img = icon.getImage().getScaledInstance(195, 120, Image.SCALE_SMOOTH);
                    lblFoto.setIcon(new ImageIcon(img));
                    lblFoto.setText(""); 
                } else {
                    lblFoto.setIcon(null);
                    lblFoto.setText("Imagen no encontrada");
                    System.out.println("Error: No se encontró el archivo en: " + rutaRelativa);
                }
            } catch (Exception e) {
                lblFoto.setIcon(null);
                lblFoto.setText("Error al cargar");
                e.printStackTrace();
            }
            
            lblFoto.revalidate();
            lblFoto.repaint();
        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String seleccion = list_discos.getSelectedValue();
            
            if (seleccion != null) {
                // 1. Limpiamos el texto por si tiene el formato "Nombre - Año"
                String nombreLimpio = seleccion.split(" - ")[0].trim(); 

                // 2. Pedimos los datos del álbum al controlador
                String[] datosAlbum = principal.getControlador().pedirAlbumPorArtistaDatosAlbum(nombreLimpio);
                
                // 3. Pasamos los datos a la VentanaAlbum
                if (principal.getPanelAlbum() != null) {
                    principal.getPanelAlbum().actualizarDatosAlbum(datosAlbum);
                    
                    // 4. Cambiamos a la pantalla del álbum
                    // OJO: Asegúrate de que en VentanaPrincipal el nombre sea "PANTALLA_ALBUM"
                    principal.mostrarPantalla("PANTALLA_ALBUM");
                }
                
                // 5. Limpiamos para poder volver a elegirlo luego
                list_discos.clearSelection();
            }
        }
    }
}