package pe.gob.muni.apimercado.model;

import java.util.Date;

public class Pago extends BasicEntity {
	
	private Date fecha_pago;
	private String serie;
	private long correlativo;
	private double monto_pagado;
	private String concepto;
	private String observaciones;
	

	public Date getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public long getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}
	public double getMonto_pagado() {
		return monto_pagado;
	}
	public void setMonto_pagado(double monto_pagado) {
		this.monto_pagado = monto_pagado;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
}
