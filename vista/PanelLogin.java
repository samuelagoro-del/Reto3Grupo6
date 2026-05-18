package vista;

import javax.swing.*;
import controlador.Controlador;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUsuario;
	private JPasswordField textFieldContrasenia;
	private VentanaPrincipal principal; 
	private Controlador controlador;

	public PanelLogin(VentanaPrincipal principal, Controlador controlador) {
		this.principal = principal; 
		this.controlador = controlador; // IMPORTANTE: Asignar el controlador recibido

		setBounds(100, 100, 441, 280);
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

                // Llamada al método login del controlador
                int resultado = controlador.login(usuario, contrasenia, seleccion);

                if (resultado == 1) {
                    // SI ES ADMIN -> Descomentamos la navegación
                    JOptionPane.showMessageDialog(null, "Bienvenido Admin");
                    principal.mostrarPantalla("PANTALLA_ADMIN"); 
                } else if (resultado == 2) {
                    // SI ES CLIENTE
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
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegistrar.setBounds(222, 201, 125, 23);
		add(btnRegistrar);
	}
}