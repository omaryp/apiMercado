package pe.gob.muni.apimercado.model;

import java.util.Date;

import javax.validation.constraints.Min;

public class PuestoComerciante extends BasicEntity {
	
	@Min(value=1, message="Atributo mercado_id debe ser != 0")
	private int mercados_id;
	@Min(value=1, message="Atributo puesto_id debe ser != 0")
	private int puestos_id;
	@Min(value=1, message="Atributo comerciante_id debe ser != 0")
	private int comerciantes_id;
	@Min(value=1, message="Atributo ubicacion_id debe ser != 0")
	private int ubicaciones_id;
	private long correlativo;
	private Date fecha_fin;
	
	public int getMercados_id() {
		return mercados_id;
	}
	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}
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
	
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public int getUbicaciones_id() {
		return ubicaciones_id;
	}
	public void setUbicaciones_id(int ubicaciones_id) {
		this.ubicaciones_id = ubicaciones_id;
	}
	public long getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}
	
		
}
