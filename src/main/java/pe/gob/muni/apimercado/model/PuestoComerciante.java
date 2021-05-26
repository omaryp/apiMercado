package pe.gob.muni.apimercado.model;

import java.util.Date;

public class PuestoComerciante extends BasicEntity {
	
	private int puestos_id;
	private int comerciantes_id;
	private int correlativo;
	private Date fecha_fin;
	
	public int getPuestos_id() {
		return puestos_id;
	}
	public void setPuestos_id(int puestos_id) {
		this.puestos_id = puestos_id;
	}
	public int getComerciantes_id() {
		return comerciantes_id;
	}
	public void setComerciantes_id(int comerciantes_id) {
		this.comerciantes_id = comerciantes_id;
	}
	public int getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
		
}
