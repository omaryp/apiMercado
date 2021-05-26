package pe.gob.muni.apimercado.model;

public class Comerciante extends Persona {
	
	private int idComerciante;
	private String ruc;
	private String razon_social;
	private int personas_id;
	
	public int getIdComerciante() {
		return idComerciante;
	}
	public void setIdComerciante(int idComerciante) {
		this.idComerciante = idComerciante;
	}
	
	public int getPersonas_id() {
		return personas_id;
	}
	public void setPersonas_id(int personas_id) {
		this.personas_id = personas_id;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	
			
}
