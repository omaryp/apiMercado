package pe.gob.muni.apimercado.model;

import java.util.Date;

public class BasicEntity {
	
	int id;
	Date fecha_creacion;
	int creado_por;
	Date fecha_modificacion;
	int modificado_por;
	int eliminado_por;
	int estado;
	
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
	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public int getModificado_por() {
		return modificado_por;
	}
	public void setModificado_por(int modificado_por) {
		this.modificado_por = modificado_por;
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
	
}
