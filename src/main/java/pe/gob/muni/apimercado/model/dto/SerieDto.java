package pe.gob.muni.apimercado.model.dto;

import pe.gob.muni.apimercado.model.BasicEntity;

public class SerieDto extends BasicEntity {

	private String codigo;
	private String descripcion;
	private long correlativo;
	private int conceptos_id;
	private String desConcepto;
	private int mercados_id;
	private String desMercado;
	
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
	public long getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}
	public int getMercados_id() {
		return mercados_id;
	}
	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}
	public String getDesConcepto() {
		return desConcepto;
	}
	public void setDesConcepto(String desConcepto) {
		this.desConcepto = desConcepto;
	}
	public String getDesMercado() {
		return desMercado;
	}
	public void setDesMercado(String desMercado) {
		this.desMercado = desMercado;
	}

}
