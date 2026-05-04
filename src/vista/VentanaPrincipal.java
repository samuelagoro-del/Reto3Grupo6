package vista;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.Controlador;
import modelo.Modelo;

public class VentanaPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel contenedor;
    private PanelRegistro registro;

    public VentanaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350); // Un poco más alta para que quepa todo bien
        
        Modelo miModelo = new Modelo();
        Controlador miControlador = new Controlador(miModelo);
        
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Pasamos "this" para que los paneles puedan llamar a mostrarPantalla
        // 2. SE LO PASAMOS AL PANEL LOGIN (He añadido miControlador)
        PanelLogin login = new PanelLogin(this, miControlador);
        registro = new PanelRegistro(this, miControlador);
        PanelMenu menu = new PanelMenu(this);

        contenedor.add(login, "PANTALLA_LOGIN");
        contenedor.add(registro, "PANTALLA_REGISTRO");
        contenedor.add(menu, "PANTALLA_MENU");

        getContentPane().add(contenedor);	//Le estamos diciendo a la ventana: "Oye, ¿ves ese espacio que tú llamas contentPane? Olvídalo. A partir de ahora, usa mi contenedor como tu panel principal".
        setVisible(true);
    }

    //Esto lo llamarán los JPanel
    public void mostrarPantalla(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    //Main
    public static void main(String[] args) {
        new VentanaPrincipal();
    }
    
    public PanelRegistro getPanelRegistro() {
        return registro; // Devuelve la variable donde tengas instanciado tu PanelRegistro
    }
}
