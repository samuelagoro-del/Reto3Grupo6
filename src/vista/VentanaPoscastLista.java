package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VentanaPoscastLista extends JPanel implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JList<String> listaEpisodios;
    private JLabel lblPodcaster;

    public VentanaPoscastLista(VentanaPrincipal principal) {
        this.principal = principal;
        
        setBounds(100, 100, 441, 280);
        setLayout(null);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        // --- BOTÓN ATRÁS ---
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnAtras.setBounds(10, 10, 85, 25);
        btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_DESCUBRIR_PODCASTERS"));
        add(btnAtras);

        // --- TÍTULO DINÁMICO ---
        lblPodcaster = new JLabel("Podcaster: ");
        lblPodcaster.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPodcaster.setBounds(110, 15, 300, 20);
        add(lblPodcaster);

        // --- PANEL DE EPISODIOS ---
        JPanel panelEpisodios = new JPanel();
        panelEpisodios.setLayout(null);
        panelEpisodios.setBounds(15, 50, 410, 200);
        panelEpisodios.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Episodios disponibles"));
        
        listaEpisodios = new JList<>();
        listaEpisodios.addListSelectionListener(this);
        
        JScrollPane scrollLista = new JScrollPane(listaEpisodios);
        scrollLista.setBounds(15, 25, 380, 160);
        panelEpisodios.add(scrollLista);
        add(panelEpisodios);
        
        JButton btnUsuario = new JButton("Usuario");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.getPanelRegistro().prepararModo(false);
				principal.mostrarPantalla("PANTALLA_REGISTRO");
			}
		});
		btnUsuario.setBounds(351, 21, 89, 23);
		add(btnUsuario);
    }

   
	/**
     * Método para actualizar la lista de episodios desde el controlador.
     */
    public void actualizarEpisodios(String nombrePodcaster, String[] episodios) {
        this.principal = principal; 

    	
        lblPodcaster.setText("Podcaster: " + nombrePodcaster);
        if (episodios != null && episodios.length > 0) {
            listaEpisodios.setListData(episodios);
        } else {
            listaEpisodios.setListData(new String[]{"No hay episodios disponibles"});
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String seleccion = listaEpisodios.getSelectedValue();
            if (seleccion != null && !seleccion.equals("No hay episodios disponibles")) {
                // Al seleccionar un episodio, vamos a la pantalla de reproducción
                // principal.getVentanaReproduccion().prepararReproduccion(seleccion, "podcast");
                principal.mostrarPantalla("PANTALLA_REPRODUCCION_PODCAST");
                listaEpisodios.clearSelection();
            }
        }
    }
}