package pe.demo.apirest.model;

public class Persona {
	
	private String dni;
	private String apellidos;
	private String nombres;
	private int celular;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}

}
