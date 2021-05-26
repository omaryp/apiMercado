package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Concepto extends BasicEntity {
	
	@NotBlank(message="Atributo descripcion no debe ser vacío.")
	@NotNull(message="Atributo descripcion no debe ser vacío.")
	private String descripcion;
	@Min(value=1, message="Atributo partida_id debe ser != 0")
	private int partida_id;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPartida_id() {
		return partida_id;
	}
	public void setPartida_id(int partida_id) {
		this.partida_id = partida_id;
	}
	
}
