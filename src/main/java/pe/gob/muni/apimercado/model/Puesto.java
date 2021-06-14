package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Puesto extends BasicEntity {
	
	@NotBlank(message="Atributo codigo no debe ser vacío.")
	@NotNull(message="Atributo codigo no debe ser vacío.")
	private String codigo;
	@Min(value=1, message="Atributo mercado debe ser != 0")
	private int mercado_id;
	@Min(value=1, message="Atributo concepto debe ser != 0")
	private int conceptos_id;
	@Min(value=1, message="Atributo cobrador debe ser != 0")
	private int cobradores_id;
	@Min(value=1, message="Atributo ubicación debe ser != 0")
	private int ubicaciones_id;
	@Min(value=1, message="Atributo giro debe ser != 0")
	private int giro_id;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getMercado_id() {
		return mercado_id;
	}
	public void setMercado_id(int mercado_id) {
		this.mercado_id = mercado_id;
	}
	public int getConceptos_id() {
		return conceptos_id;
	}
	public void setConceptos_id(int conceptos_id) {
		this.conceptos_id = conceptos_id;
	}
	public int getCobradores_id() {
		return cobradores_id;
	}
	public void setCobradores_id(int cobradores_id) {
		this.cobradores_id = cobradores_id;
	}
	public int getUbicaciones_id() {
		return ubicaciones_id;
	}
	public void setUbicaciones_id(int ubicaciones_id) {
		this.ubicaciones_id = ubicaciones_id;
	}
	public int getGiro_id() {
		return giro_id;
	}
	public void setGiro_id(int giro_id) {
		this.giro_id = giro_id;
	}
	
	
	
}
