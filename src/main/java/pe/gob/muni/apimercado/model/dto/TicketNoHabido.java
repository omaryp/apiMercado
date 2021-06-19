package pe.gob.muni.apimercado.model.dto;

import java.util.Date;

public class TicketNoHabido {

	private int id;
	private boolean no_habido;
	private String observaciones;
	private Date fecha_obs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isNo_habido() {
		return no_habido;
	}
	public void setNo_habido(boolean no_habido) {
		this.no_habido = no_habido;
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
