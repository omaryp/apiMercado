package pe.gob.muni.apimercado.model;

import java.util.Date;


public class Ticket extends BasicEntity {
	
	private int mercados_id;
	private int comerciantes_id;
	private int ubicaciones_id;
	private int puestos_id;
	private long correlativo;
	private int pagado;
	private Date fecha_ticket;
	private boolean no_habido;
	private String observaciones;
	private Date fecha_obs;

	public int getMercados_id() {
		return mercados_id;
	}

	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}

	public int getComerciantes_id() {
		return comerciantes_id;
	}

	public void setComerciantes_id(int comerciantes_id) {
		this.comerciantes_id = comerciantes_id;
	}

	public int getPuestos_id() {
		return puestos_id;
	}

	public void setPuestos_id(int puestos_id) {
		this.puestos_id = puestos_id;
	}

	public long getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
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

	public int getUbicaciones_id() {
		return ubicaciones_id;
	}

	public void setUbicaciones_id(int ubicaciones_id) {
		this.ubicaciones_id = ubicaciones_id;
	}

	public int getPagado() {
		return pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}

	public Date getFecha_ticket() {
		return fecha_ticket;
	}

	public void setFecha_ticket(Date fecha_ticket) {
		this.fecha_ticket = fecha_ticket;
	}

	public Date getFecha_obs() {
		return fecha_obs;
	}

	public void setFecha_obs(Date fecha_obs) {
		this.fecha_obs = fecha_obs;
	}

}
