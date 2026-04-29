package controlador;

import modelo.Modelo;
import modelo.Cliente;

public class Controlador {
    private Modelo modelo;

    public Controlador(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * Método para gestionar el login.
     * Devuelve: 
     * 1 si es Admin, 
     * 2 si es Cliente, 
     * 0 si los datos son incorrectos.
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
} 	