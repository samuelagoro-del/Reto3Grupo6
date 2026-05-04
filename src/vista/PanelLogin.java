package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que representa el panel de inicio de sesión (Login) de la interfaz gráfica.
 * Hereda de {@link JPanel} y contiene los campos de texto, desplegables y botones 
 * necesarios para que un usuario (Cliente o Administrador) pueda acceder al sistema 
 * o navegar hacia la pantalla de registro.
 */
public class PanelLogin extends JPanel {

	/** Campo de texto para introducir el nombre de usuario. */
	private JTextField textFieldUsuario;
	
	/** Campo de texto con ocultación de caracteres para introducir la contraseña. */
	private JPasswordField textFieldContrasenia;
	
	/** Referencia a la ventana principal de la aplicación para poder cambiar entre paneles. */
	private VentanaPrincipal principal; 
	
	/** Referencia al controlador para gestionar la lógica de negocio del inicio de sesión. */
	private Controlador controlador;

	/**
	 * Constructor de la clase PanelLogin.
	 * Inicializa y posiciona todos los componentes visuales (etiquetas, campos de texto, 
	 * botones y desplegables) y define las acciones de los botones.
	 * * @param principal El marco o ventana principal de la aplicación que contiene este panel.
	 * @param controlador El objeto Controlador encargado de procesar la lógica de negocio.
	 */
	public PanelLogin(VentanaPrincipal principal, Controlador controlador) {
		this.principal = principal; // Guardamos la referencia
		this.controlador = controlador; // Guardamos la referencia al controlador (buena práctica añadirlo)

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuario.setBounds(90, 69, 132, 25);
		add(lblUsuario);

		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContrasenia.setBounds(90, 121, 132, 19);
		add(lblContrasenia);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldUsuario.setBounds(197, 72, 150, 20);
		add(textFieldUsuario);

		textFieldContrasenia = new JPasswordField();
		textFieldContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldContrasenia.setBounds(197, 121, 150, 20);
		add(textFieldContrasenia);

		JComboBox<String> elegirTipo = new JComboBox<String>();
		elegirTipo.addItem("Cliente");
		elegirTipo.addItem("Admin");
		elegirTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		elegirTipo.setBounds(262, 152, 85, 22);
		add(elegirTipo);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textFieldUsuario.getText();
                String contrasenia = new String(textFieldContrasenia.getPassword());
                String seleccion = (String) elegirTipo.getSelectedItem();

                // USAMOS TU CONTROLADOR (el que devuelve 1, 2 o 0)
                int resultado = controlador.login(usuario, contrasenia, seleccion);

                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "Bienvenido Admin");
                    // principal.mostrarPantalla("PANTALLA_ADMIN");
                } else if (resultado == 2) {
                    JOptionPane.showMessageDialog(null, "Login Cliente Correcto");
                    principal.mostrarPantalla("PANTALLA_MENU");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(90, 201, 115, 23);
		add(btnLogin);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.getPanelRegistro().prepararModo(true);
				principal.mostrarPantalla("PANTALLA_REGISTRO");
				/* no se si cuando le das a boton de registrar los atributos puestos en estos text, 
				se deben de poner automaticamente en la siguiente pestaña (PanelRegistro)*/
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegistrar.setBounds(222, 201, 125, 23);
		add(btnRegistrar);
	}
}