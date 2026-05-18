package modelo;
import java.sql.Date;

public class Cliente {
    protected String nombre;
    protected String apellido;
    protected String Idioma;
    protected String usuario;
    protected String contraseña;
    protected Date fechaNacimiento;
    protected Date fechaRegistro;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdIdioma() {
		return Idioma;
	}

	public void setIdIdioma(String idIdioma) {
		this.Idioma = idIdioma;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
    
    
}