package modelo;
import java.sql.Date;

/**
 * Clase que representa a un cliente genérico dentro del sistema.
 * Contiene la información personal y los datos de la cuenta del usuario.
 * Sus atributos tienen visibilidad 'protected' para permitir que clases hijas 
 * (como ClienteFree) puedan heredarlos y acceder a ellos.
 */
public class Cliente {
    
    /** Nombre real del cliente. */
    protected String nombre;
    
    /** Apellido del cliente. */
    protected String apellido;
    
    /** Idioma de preferencia del cliente. */
    protected String Idioma;
    
    /** Nombre de usuario (nickname) utilizado para iniciar sesión. */
    protected String usuario;
    
    /** Contraseña de acceso a la cuenta del cliente. */
    protected String contraseña;
    
    /** Fecha de nacimiento del cliente. */
    protected Date fechaNacimiento;
    
    /** Fecha en la que el cliente se registró en el sistema. */
    protected Date fechaRegistro;

    /**
     * Constructor de la clase Cliente.
     * * @param nombre El nombre real del cliente.
     * @param apellido El apellido del cliente.
     * @param idIdioma El identificador o nombre del idioma de preferencia.
     * @param usuario El nombre de usuario para el inicio de sesión.
     * @param contraseña La contraseña de la cuenta.
     * @param fechaNacimiento La fecha de nacimiento del cliente.
     * @param fechaRegistro La fecha en la que se realiza el registro.
     */
    public Cliente(String nombre, String apellido, String idIdioma, 
                   String usuario, String contraseña, Date fechaNacimiento, Date fechaRegistro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.Idioma = idIdioma;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
	public String getNombre() {
		return nombre;
	}

    /**
     * Establece el nombre del cliente.
     * @param nombre El nuevo nombre del cliente.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    /**
     * Obtiene el apellido del cliente.
     * @return El apellido del cliente.
     */
	public String getApellido() {
		return apellido;
	}

    /**
     * Establece el apellido del cliente.
     * @param apellido El nuevo apellido del cliente.
     */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

    /**
     * Obtiene el idioma preferido del cliente.
     * @return El idioma del cliente.
     */
	public String getIdIdioma() {
		return Idioma;
	}

    /**
     * Establece el idioma preferido del cliente.
     * @param idIdioma El nuevo idioma.
     */
	public void setIdIdioma(String idIdioma) {
		this.Idioma = idIdioma;
	}

    /**
     * Obtiene el nombre de usuario del cliente.
     * @return El nombre de usuario.
     */
	public String getUsuario() {
		return usuario;
	}

    /**
     * Establece el nombre de usuario del cliente.
     * @param usuario El nuevo nombre de usuario.
     */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

    /**
     * Obtiene la contraseña de la cuenta del cliente.
     * @return La contraseña del cliente.
     */
	public String getContraseña() {
		return contraseña;
	}

    /**
     * Establece la contraseña de la cuenta del cliente.
     * @param contraseña La nueva contraseña.
     */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

    /**
     * Obtiene la fecha de nacimiento del cliente.
     * @return La fecha de nacimiento.
     */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

    /**
     * Establece la fecha de nacimiento del cliente.
     * @param fechaNacimiento La nueva fecha de nacimiento.
     */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

    /**
     * Obtiene la fecha de registro del cliente.
     * @return La fecha de registro en el sistema.
     */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

    /**
     * Establece la fecha de registro del cliente.
     * @param fechaRegistro La nueva fecha de registro.
     */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}