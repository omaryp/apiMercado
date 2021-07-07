package pe.gob.muni.apimercado.utils.dto;

import java.util.Date;

public class Monto {
	
	private Date fecha;
	private double total;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
