package pe.gob.muni.apimercado.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Modulo extends BasicEntity {
	

	@NotBlank(message="Atributo nombre Rol no debe ser vacío.")
	@NotNull(message="Atributo nombre Rol no debe ser vacío.")
	private String nombre;
	private String descripcion;

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


}
