package pe.gob.muni.apimercado.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Mercado extends BasicEntity {
	
	@NotBlank(message="Atributo nombre no debe ser vacío.")
	@NotNull(message="Atributo nombre no debe ser vacío.")
	private String nombre;
	private String descripcion;
	@NotBlank(message="Atributo direccion no debe ser vacío.")
	@NotNull(message="Atributo direccion no debe ser vacío.")
	private String direccion;
	@NotBlank(message="Atributo zona no debe ser vacío.")
	@NotNull(message="Atributo zona no debe ser vacío.")
	private String zona;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	
}
