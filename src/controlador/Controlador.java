package controlador;

import modelo.Modelo;
import modelo.Cliente;

/**
 * Clase que actúa como intermediaria entre la vista y el modelo.
 * Gestiona la lógica de autenticación de usuarios.
 */
public class Controlador {
    private Modelo modelo;

    /**
     * Constructor que recibe el modelo.
     * @param modelo instancia del modelo que gestiona los datos
     */
    public Controlador(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * Gestiona el proceso de login según el tipo de usuario.
     *
     * @param usuarioInput nombre de usuario introducido
     * @param passInput contraseña introducida
     * @param tipoElegido tipo de usuario ("Admin" o "Cliente")
     * @return 1 si es administrador, 2 si es cliente, 0 si hay error
     */
    public int login(String usuarioInput, String passInput, String tipoElegido) {
        
        // Comprueba credenciales de administrador
        if (tipoElegido.equals("Admin")) {
            if (usuarioInput.equals("admin") && passInput.equals("1234")) {
                return 1;
            } else {
                return 0;
            }
        }

        // Consulta al modelo para validar cliente
        if (tipoElegido.equals("Cliente")) {
            Cliente clienteEncontrado = modelo.loginCliente(usuarioInput, passInput);
            
            if (clienteEncontrado != null) {
                return 2;
            } else {
                return 0;
            }
        }

        // Valor por defecto si ocurre algún caso no contemplado
        return 0;
    }
}
