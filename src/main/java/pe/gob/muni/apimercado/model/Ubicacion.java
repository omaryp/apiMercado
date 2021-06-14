package pe.gob.muni.apimercado.model;

import javax.validation.constraints.NotNull;

public class Ubicacion extends BasicEntity {

	@NotNull(message="Atributo descripcion no debe ser vacío.")
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
