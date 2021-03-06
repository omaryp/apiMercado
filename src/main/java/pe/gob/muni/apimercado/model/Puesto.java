package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Puesto extends BasicEntity {
	
	@NotNull(message="Atributo codigo no debe ser vacío.")
	private String codigo;
	@Min(value=1, message="Atributo mercado debe ser != 0")
	private int mercados_id;
	@Min(value=1, message="Atributo concepto debe ser != 0")
	private int conceptos_id;
	@Min(value=1, message="Atributo ubicación debe ser != 0")
	private int ubicaciones_id;
	@Min(value=1, message="Atributo giro debe ser != 0")
	private int giro_id;
	
	private String codigo_qr;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getMercados_id() {
		return mercados_id;
	}
	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}
	public int getConceptos_id() {
		return conceptos_id;
	}
	public void setConceptos_id(int conceptos_id) {
		this.conceptos_id = conceptos_id;
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
	public String getCodigo_qr() {
		return codigo_qr;
	}
	public void setCodigo_qr(String codigo_qr) {
		this.codigo_qr = codigo_qr;
	}
	
}
