package controlador;

import modelo.Modelo;
import java.time.LocalDate;
import modelo.Cliente;

/**
 * Clase Controlador que actúa como intermediario entre la vista y la base de datos (Modelo).
 * Se encarga de gestionar la lógica de negocio, como el inicio de sesión y el registro de nuevos usuarios.
 */
public class Controlador {
	
	/**
	 * Instancia del modelo para interactuar con la lógica de acceso a datos.
	 */
	private Modelo modelo;

	/**
	 * Constructor de la clase Controlador.
	 * * @param modelo Objeto de la clase Modelo que permite el acceso a la base de datos.
	 */
	public Controlador(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * Gestiona el proceso de inicio de sesión verificando las credenciales según el tipo de usuario.
	 * * @param usuarioInput El nombre de usuario introducido por el usuario.
	 * @param passInput La contraseña introducida por el usuario.
	 * @param tipoElegido El rol seleccionado en la interfaz (por ejemplo, "Admin" o "Cliente").
	 * @return Un número entero que representa el estado del login:
	 * 1 si el inicio de sesión es correcto como Administrador.
	 * 2 si el inicio de sesión es correcto como Cliente.
	 * 0 si las credenciales son incorrectas o el cliente no existe.
	 */
	public int login(String usuarioInput, String passInput, String tipoElegido) {

		// PRIMERO: Comprobamos si ha elegido Admin en el JComboBox
		if (tipoElegido.equals("Admin")) {
			if (usuarioInput.equals("admin") && passInput.equals("1234")) {
				return 1; // Código para Administrador
			} else {
				return 0; // Datos de admin incorrectos
			}
		}

		// SEGUNDO: Si ha elegido Cliente, preguntamos al Modelo (Base de Datos)
		if (tipoElegido.equals("Cliente")) {
			Cliente clienteEncontrado = modelo.loginCliente(usuarioInput, passInput);

			if (clienteEncontrado != null) {
				return 2; // Código para Cliente encontrado
			} else {
				return 0; // Cliente no encontrado en la BD
			}
		}

		return 0; // Por si acaso algo falla
	}

	/**
	 * Solicita al modelo el registro de un nuevo usuario con los datos proporcionados.
	 * * @param nombre El nombre real del cliente.
	 * @param apellido El apellido del cliente.
	 * @param usuario El nombre de usuario (nickname) para el inicio de sesión.
	 * @param contrasenia La contraseña elegida por el usuario.
	 * @param fechaNac La fecha de nacimiento del cliente.
	 * @param fechaRegistro La fecha en la que se efectúa el registro.
	 * @param limitePremium La fecha en la que expira la suscripción premium (si aplica).
	 * @param idioma El idioma de preferencia seleccionado por el cliente.
	 * @return 1 si el cliente se ha registrado correctamente en la base de datos, 
	 * 0 si ha ocurrido un error o no se ha podido registrar.
	 */
	public int registrarUsuario(String nombre, String apellido, String usuario, String contrasenia, LocalDate fechaNac, String fechaRegistro, String limitePremium, String idioma) {

		Cliente clienteRegistrado = modelo.registrarUsuarioModelo(nombre, apellido, usuario, contrasenia, fechaNac, fechaRegistro, limitePremium, idioma);
		
		if (clienteRegistrado != null) {
			return 1;
		} else {
			return 0;
		}
	}
}