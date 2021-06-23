package pe.gob.muni.apimercado.model.dto;

import java.util.Date;

public class TicketVisita {

	private int id;
	private int estado_visita;
	private String observaciones;
	private Date fecha_obs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEstado_visita() {
		return estado_visita;
	}
	public void setEstado_visita(int estado_visita) {
		this.estado_visita = estado_visita;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Date getFecha_obs() {
		return fecha_obs;
	}
	public void setFecha_obs(Date fecha_obs) {
		this.fecha_obs = fecha_obs;
	}
	
}
