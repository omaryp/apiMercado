package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Serie extends BasicEntity {

	@NotBlank(message="Atributo codigo no debe ser vacío.")
	@NotNull(message="Atributo codigo no debe ser vacío.")
	private String codigo;
	@NotBlank(message="Atributo descripcion no debe ser vacío.")
	@NotNull(message="Atributo descripcion no debe ser vacío.")
	private String descripcion;
	@Min(value=1, message="Atributo conceptos_id debe ser != 0")
	private int conceptos_id;
	
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
	public int getConceptos_id() {
		return conceptos_id;
	}
	public void setConceptos_id(int conceptos_id) {
		this.conceptos_id = conceptos_id;
	}
	
	
	
}
