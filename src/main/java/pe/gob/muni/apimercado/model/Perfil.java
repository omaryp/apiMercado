package pe.gob.muni.apimercado.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Perfil{
	
	private int codigo;
	
	@NotBlank(message="Atributo nombre Perfil no debe ser vacío.")
	@NotNull(message="Atributo nombre Perfil no debe ser vacío.")
	private String nombre;
	private String descripcion;
	private boolean estado;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
