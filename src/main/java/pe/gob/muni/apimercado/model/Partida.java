package pe.gob.muni.apimercado.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Partida extends BasicEntity {
	
	@NotBlank(message="Atributo codigo no debe ser vacío.")
	@NotNull(message="Atributo codigo no debe ser vacío.")
	private String codigo;
	@NotBlank(message="Atributo descripcion no debe ser vacío.")
	@NotNull(message="Atributo descripcion no debe ser vacío.")
	private String descripcion;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
