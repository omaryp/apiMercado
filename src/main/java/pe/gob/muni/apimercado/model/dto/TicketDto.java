package pe.gob.muni.apimercado.model.dto;

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
	
	
}
