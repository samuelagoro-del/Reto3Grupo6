package vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Controlador;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class PanelRegistro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldUsuario;
	private JPasswordField textFieldContrasenia;
	private JPasswordField textFieldContrasenia2;
	private JTextField textFieldFechaNac;
	private JTextField textFieldFechaReg;
	private JTextField textFieldLimitePremium;
	private JTextField textFieldApellido;
	private JComboBox<String> comboBoxIdioma;
	private JButton btnEditar;
	private JCheckBox chkPremium;
	private VentanaPrincipal principal;
	private LocalDate fechaActual;
	private Controlador controlador;

	/**
	 * Create the panel.
	 */
	public PanelRegistro(VentanaPrincipal principal, Controlador controlador) {
		this.principal = principal;
		this.controlador = controlador;
		setLayout(null);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.mostrarPantalla("PANTALLA_LOGIN");
			}
		});
		btnAtras.setBounds(10, 11, 86, 23);
		add(btnAtras);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(29, 45, 67, 14);
		add(lblNombre);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(29, 80, 67, 14);
		add(lblUsuario);

		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setBounds(29, 105, 86, 14);
		add(lblContrasenia);

		JLabel lblConfirmacion = new JLabel("Confirmación:");
		lblConfirmacion.setBounds(29, 130, 86, 14);
		add(lblConfirmacion);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setBounds(29, 155, 123, 14);
		add(lblFechaDeNacimiento);

		JLabel lblFechaDeRegistro = new JLabel("Fecha de registro:");
		lblFechaDeRegistro.setBounds(29, 180, 123, 14);
		add(lblFechaDeRegistro);

		JLabel lblVencimiento = new JLabel("Límite de premium:");
		lblVencimiento.setBounds(29, 205, 123, 14);
		add(lblVencimiento);

		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(29, 230, 46, 14);
		add(lblIdioma);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(268, 45, 73, 14);
		add(lblApellido);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(156, 42, 102, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBounds(156, 77, 254, 20);
		add(textFieldUsuario);

		textFieldContrasenia = new JPasswordField();
		textFieldContrasenia.setColumns(10);
		textFieldContrasenia.setBounds(156, 102, 254, 20);
		add(textFieldContrasenia);

		textFieldContrasenia2 = new JPasswordField();
		textFieldContrasenia2.setColumns(10);
		textFieldContrasenia2.setBounds(156, 127, 254, 20);
		add(textFieldContrasenia2);

		textFieldFechaNac = new JTextField();
		textFieldFechaNac.setColumns(10);
		textFieldFechaNac.setBounds(156, 152, 254, 20);
		add(textFieldFechaNac);



		fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		textFieldFechaReg = new JTextField();
		textFieldFechaReg.setEditable(false);
		textFieldFechaReg.setText(fechaActual.format(formato));
		textFieldFechaReg.setColumns(10);
		textFieldFechaReg.setBounds(156, 177, 254, 20);
		add(textFieldFechaReg);

		textFieldLimitePremium = new JTextField();
		textFieldLimitePremium.setEditable(false);
		textFieldLimitePremium.setText("-");
		textFieldLimitePremium.setColumns(10);
		textFieldLimitePremium.setBounds(156, 202, 254, 20);
		add(textFieldLimitePremium);

		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(324, 42, 86, 20);
		add(textFieldApellido);

		comboBoxIdioma = new JComboBox<String>();
		comboBoxIdioma.addItem("Español");
		comboBoxIdioma.addItem("Euskera");
		comboBoxIdioma.addItem("Inglés");
		comboBoxIdioma.setBounds(157, 226, 86, 22);
		add(comboBoxIdioma);




		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarEstadoCampos(true); // Desbloquea los campos al pulsarlo
				btnEditar.setEnabled(false); // Opcional: deshabilitar el botón editar una vez pulsado
			}
		});
		btnEditar.setBounds(29, 266, 67, 23);
		add(btnEditar);







		chkPremium = new JCheckBox("Comprar Premium");
		chkPremium.setBounds(268, 266, 142, 23);
		chkPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si el usuario marca la casilla
				if (chkPremium.isSelected()) {
					LocalDate fechaMesQueViene = LocalDate.now().plusMonths(1);
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					textFieldLimitePremium.setText(fechaMesQueViene.format(formato));
				} else {
					// Si el usuario la desmarca, volvemos a poner el guion
					textFieldLimitePremium.setText("-");
				}
			}
		});
		add(chkPremium);









		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText();
				String apellido = textFieldApellido.getText();
				String usuario = textFieldUsuario.getText();
				String contrasenia = new String(textFieldContrasenia.getPassword());
				String contrasenia2 = new String(textFieldContrasenia2.getPassword());

				// Validación básica de contraseñas
				if (!contrasenia.equals(contrasenia2)) {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					return;
				}

				String idioma = (String) comboBoxIdioma.getSelectedItem(); 
				
				// Sacamos los textos tal cual están en la pantalla (formato Español)
				String fechaRegistroTexto = textFieldFechaReg.getText(); 
				String limitePremiumTexto = textFieldLimitePremium.getText(); 
				String fechaNacimientoTexto = textFieldFechaNac.getText();

				// Preparamos los conversores de fecha
				DateTimeFormatter formatoEspanol = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				DateTimeFormatter formatoIngles = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				try {
					// 1. FECHA NACIMIENTO: De Texto a LocalDate
					LocalDate fechaNac = LocalDate.parse(fechaNacimientoTexto, formatoEspanol);
					
					// 2. FECHA REGISTRO: De Español a Inglés/SQL
					LocalDate fRegLocal = LocalDate.parse(fechaRegistroTexto, formatoEspanol);
					String fechaRegistroSQL = fRegLocal.format(formatoIngles); // Pasa a YYYY-MM-DD
					
					// 3. LÍMITE PREMIUM: De Español a Inglés/SQL (si no es un guion)
					String limitePremiumSQL = "-";
					if (!limitePremiumTexto.equals("-")) {
						LocalDate fPremLocal = LocalDate.parse(limitePremiumTexto, formatoEspanol);
						limitePremiumSQL = fPremLocal.format(formatoIngles); // Pasa a YYYY-MM-DD
					}

					// LLAMAMOS AL CONTROLADOR PASANDO LAS FECHAS YA TRADUCIDAS (fechaRegistroSQL y limitePremiumSQL)
					int resultado = controlador.registrarUsuario(nombre, apellido, usuario, contrasenia, fechaNac, fechaRegistroSQL, limitePremiumSQL, idioma);

					if (resultado == 1) {
			            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
			            principal.mostrarPantalla("PANTALLA_LOGIN");
			        } else {
			            JOptionPane.showMessageDialog(null, "Error al registrar. El usuario ya existe o los datos son incorrectos.");
			        }

				} catch (Exception ex) {
					// Si el usuario escribió mal la fecha, salta este error
					JOptionPane.showMessageDialog(null, "Por favor, introduce una fecha real con el formato dd/MM/yyyy", "Error en la fecha", JOptionPane.ERROR_MESSAGE);
					return; 
				}

			}
		});
		btnAceptar.setBounds(108, 266, 86, 23);
		add(btnAceptar);
		btnAceptar.setBounds(108, 266, 86, 23);
		add(btnAceptar);

	}




	public void prepararModo(boolean desdeLogin) {
		if (desdeLogin) {
			cambiarEstadoCampos(true);
			btnEditar.setEnabled(false); // No tiene sentido "Editar" si ya estás creando uno nuevo
			// Si es un registro nuevo, SÍ ponemos la fecha de hoy
			textFieldFechaReg.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			textFieldLimitePremium.setText("-");
		} else {
			cambiarEstadoCampos(false);
			btnEditar.setEnabled(true); // Habilitamos el botón para que pueda decidir editar
		}
	}

	private void cambiarEstadoCampos(boolean editable) {
		textFieldNombre.setEditable(editable);
		textFieldUsuario.setEditable(editable);
		textFieldContrasenia.setEditable(editable);
		textFieldContrasenia2.setEditable(editable);
		textFieldFechaNac.setEditable(editable);
		textFieldApellido.setEditable(editable);
		comboBoxIdioma.setEnabled(editable);
		if (chkPremium != null) {
			chkPremium.setEnabled(editable);
		}
	}
	
	
	public void cargarDatosUsuario(String nombre, String apellido, String usuario, String fNac, String fReg, String fPrem, String idioma) {
	    textFieldNombre.setText(nombre);
	    textFieldApellido.setText(apellido);
	    textFieldUsuario.setText(usuario);
	    textFieldFechaNac.setText(fNac);
	    textFieldFechaReg.setText(fReg); // Aquí cargamos la fecha REAL de la BD
	    textFieldLimitePremium.setText(fPrem);
	    comboBoxIdioma.setSelectedItem(idioma);
	}
}
