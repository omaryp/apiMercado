package pe.gob.muni.apimercado.model.dto;

import java.util.Date;

public class TicketDto {

	private int id;
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
	private int estado;
	private int pagado;
	private Date fecha_ticket;
	private Date fecha_creacion;
	private int estado_visita;
	private String descripcion_visita;
	private String observaciones;
	private Date fecha_visita;
	private String correo;
	private String telefono;
	private double tarifa;
	
	public String getKeyOrder() {
		return comerciantes_id+"-"+puestos_id;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getPagado() {
		return pagado;
	}
	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_ticket() {
		return fecha_ticket;
	}
	public void setFecha_ticket(Date fecha_ticket) {
		this.fecha_ticket = fecha_ticket;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getDescripcion_visita() {
		return descripcion_visita;
	}
	public void setDescripcion_visita(String descripcion_visita) {
		this.descripcion_visita = descripcion_visita;
	}
	public int getEstado_visita() {
		return estado_visita;
	}
	public void setEstado_visita(int estado_visita) {
		this.estado_visita = estado_visita;
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

	public Date getFecha_visita() {
		return fecha_visita;
	}

	public void setFecha_visita(Date fecha_visita) {
		this.fecha_visita = fecha_visita;
	}

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

}
