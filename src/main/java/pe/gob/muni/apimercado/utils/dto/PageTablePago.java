package pe.gob.muni.apimercado.utils.dto;

import java.util.Date;

public class PageTablePago extends BasicPageTable {

	private Date fecha_incio;
	private Date fecha_fin;
	private int comerciantes_id;
	private int ubicaciones_id;
	private int codigo;
	
	public Date getFecha_incio() {
		return fecha_incio;
	}
	public void setFecha_incio(Date fecha_incio) {
		this.fecha_incio = fecha_incio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public int getComerciantes_id() {
		return comerciantes_id;
	}
	public void setComerciantes_id(int comerciantes_id) {
		this.comerciantes_id = comerciantes_id;
	}
	public int getUbicaciones_id() {
		return ubicaciones_id;
	}
	public void setUbicaciones_id(int ubicaciones_id) {
		this.ubicaciones_id = ubicaciones_id;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}