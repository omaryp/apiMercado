package pe.gob.muni.apimercado.model.dto;

import java.util.Date;


public class PagoDto{
	
	private int id;
	private Date fecha_pago;
	private String serie;
	private long correlativo;
	private double monto_pagado;
	private String observaciones;
	private int tickets_id;
	private int puestos_id;
	private String codigo_puesto;
	private int mercados_id;
	private String descripcion_mercado;
	private int conceptos_id;
	private String descripcion_concepto;
	private int ubicaciones_id;
	private String descripcion_ubicacion;
	private int giro_id;
	private String descripcion_giro;
	private int comerciantes_id;
	private String dni;
	private String nombres;
	private String apellidos;
	private String correo;
	private String telefono;
	private Date fecha_ticket;
	private double monto;
	 
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
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPuestos_id() {
		return puestos_id;
	}
	public void setPuestos_id(int puestos_id) {
		this.puestos_id = puestos_id;
	}
	public String getCodigo_puesto() {
		return codigo_puesto;
	}
	public void setCodigo_puesto(String codigo_puesto) {
		this.codigo_puesto = codigo_puesto;
	}
	public int getMercados_id() {
		return mercados_id;
	}
	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}
	public String getDescripcion_mercado() {
		return descripcion_mercado;
	}
	public void setDescripcion_mercado(String descripcion_mercado) {
		this.descripcion_mercado = descripcion_mercado;
	}
	public int getConceptos_id() {
		return conceptos_id;
	}
	public void setConceptos_id(int conceptos_id) {
		this.conceptos_id = conceptos_id;
	}
	public String getDescripcion_concepto() {
		return descripcion_concepto;
	}
	public void setDescripcion_concepto(String descripcion_concepto) {
		this.descripcion_concepto = descripcion_concepto;
	}
	public int getUbicaciones_id() {
		return ubicaciones_id;
	}
	public void setUbicaciones_id(int ubicaciones_id) {
		this.ubicaciones_id = ubicaciones_id;
	}
	public String getDescripcion_ubicacion() {
		return descripcion_ubicacion;
	}
	public void setDescripcion_ubicacion(String descripcion_ubicacion) {
		this.descripcion_ubicacion = descripcion_ubicacion;
	}
	public int getGiro_id() {
		return giro_id;
	}
	public void setGiro_id(int giro_id) {
		this.giro_id = giro_id;
	}
	public String getDescripcion_giro() {
		return descripcion_giro;
	}
	public void setDescripcion_giro(String descripcion_giro) {
		this.descripcion_giro = descripcion_giro;
	}
	public int getComerciantes_id() {
		return comerciantes_id;
	}
	public void setComerciantes_id(int comerciantes_id) {
		this.comerciantes_id = comerciantes_id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getTickets_id() {
		return tickets_id;
	}
	public void setTickets_id(int tickets_id) {
		this.tickets_id = tickets_id;
	}
	public Date getFecha_ticket() {
		return fecha_ticket;
	}
	public void setFecha_ticket(Date fecha_ticket) {
		this.fecha_ticket = fecha_ticket;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}

}
