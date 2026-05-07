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

public class VentanaPoscasterLista extends JPanel implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal principal;
    private JList<String> listaPodcasters;

    public VentanaPoscasterLista(VentanaPrincipal principal) {
        this.principal = principal;
        
        setBounds(100, 100, 441, 280);
        setLayout(null);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnAtras.setBounds(10, 21, 78, 23);
        btnAtras.addActionListener(e -> principal.mostrarPantalla("PANTALLA_MENU"));
        add(btnAtras);

        JLabel lblTitulo = new JLabel("Descubrir Podcasters");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTitulo.setBounds(110, 25, 200, 14);
        add(lblTitulo);

        // Lista de Podcasters
        listaPodcasters = new JList<>();
        listaPodcasters.addListSelectionListener(this);
        
        JScrollPane scrollPane = new JScrollPane(listaPodcasters);
        scrollPane.setBounds(98, 50, 215, 173);
        add(scrollPane);
        
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

    // Método para cargar los datos desde el controlador
    public void cargarPodcasters(String[] podcasters) {
        if (podcasters != null) {
            listaPodcasters.setListData(podcasters);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String podcasterSeleccionado = listaPodcasters.getSelectedValue();
            if (podcasterSeleccionado != null) {
                // 1. Pedir los episodios de ese podcaster al controlador
                String[] episodios = principal.getControlador().pedirPodcastsPorPodcaster(podcasterSeleccionado);
                
                // 2. Usamos el getter correcto (getVentanaPoscastLista) 
                // y el método correcto (actualizarEpisodios) que definimos antes
                if (principal.getVentanaPoscastLista() != null) {
                    principal.getVentanaPoscastLista().actualizarEpisodios(podcasterSeleccionado, episodios);
                    
                    // 3. Cambiamos a la pantalla de la lista de episodios
                    principal.mostrarPantalla("PANTALLA_LISTA_PODCASTS");
                }
                
                // Limpiamos la selección para poder volver a clicar
                listaPodcasters.clearSelection();
            }
        }
    }
}