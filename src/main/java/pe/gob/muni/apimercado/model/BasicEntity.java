package pe.gob.muni.apimercado.model;

import java.util.Date;

public class BasicEntity {
	
	private int id;
	private Date fecha_creacion;
	private int creado_por;
	private Date fecha_modifcacion;
	private int modifcado_por;
	private int eliminado_por;
	private int estado = 1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public int getCreado_por() {
		return creado_por;
	}
	public void setCreado_por(int creado_por) {
		this.creado_por = creado_por;
	}
	public int getEliminado_por() {
		return eliminado_por;
	}
	public void setEliminado_por(int eliminado_por) {
		this.eliminado_por = eliminado_por;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFecha_modifcacion() {
		return fecha_modifcacion;
	}
	public void setFecha_modifcacion(Date fecha_modifcacion) {
		this.fecha_modifcacion = fecha_modifcacion;
	}
	public int getModifcado_por() {
		return modifcado_por;
	}
	public void setModifcado_por(int modifcado_por) {
		this.modifcado_por = modifcado_por;
	}
	
	
	
}
