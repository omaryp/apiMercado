package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Concepto extends BasicEntity {
	
	@NotBlank(message="Atributo descripcion no debe ser vacío.")
	@NotNull(message="Atributo descripcion no debe ser vacío.")
	private String descripcion;
	@Min(value=1, message="Atributo partidas_id debe ser != 0")
	private int partidas_id;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPartidas_id() {
		return partidas_id;
	}
	public void setPartidas_id(int partidas_id) {
		this.partidas_id = partidas_id;
	}
	
	
	
}
