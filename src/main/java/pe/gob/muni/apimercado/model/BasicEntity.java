package pe.gob.muni.apimercado.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BasicEntity {
	
	private int id;
	@NotNull(message="Atributo fecha_creacion no debe ser vacío.")
	private Date fecha_creacion;
	@Min(value=1, message="Atributo creado_por debe ser != 0")
	private int creado_por;
	private Date fecha_modifcacion;
	private int modifcado_por;
	private int eliminado_por;
	private int estado;
	
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
	public Date getFecha_modifcacion() {
		return fecha_modifcacion;
	}
	public void setFecha_modificacion(Date fecha_modifcacion) {
		this.fecha_modifcacion = fecha_modifcacion;
	}
	public int getModificado_por() {
		return modifcado_por;
	}
	public void setModificado_por(int modificado_por) {
		this.modifcado_por = modificado_por;
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
