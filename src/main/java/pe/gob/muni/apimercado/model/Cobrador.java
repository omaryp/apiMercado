package pe.gob.muni.apimercado.model;

public class Cobrador extends Persona {
	
	private int idCobrador;
	private int personas_id;
	private int mercados_id;
	private String descripcion;
	
	public int getIdCobrador() {
		return idCobrador;
	}
	public void setIdCobrador(int idCobrador) {
		this.idCobrador = idCobrador;
	}
	public int getPersonas_id() {
		return personas_id;
	}
	public void setPersonas_id(int personas_id) {
		this.personas_id = personas_id;
	}
	public int getMercados_id() {
		return mercados_id;
	}
	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
