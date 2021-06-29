package pe.gob.muni.apimercado.model;

import javax.validation.constraints.NotNull;

public class Ubicacion extends BasicEntity {

	@NotNull(message="Atributo descripcion no debe ser vacío.")
	private String descripcion;
	private int mercados_id;
	private String nombre_mercado;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getMercados_id() {
		return mercados_id;
	}

	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}

	public String getNombre_mercado() {
		return nombre_mercado;
	}

	public void setNombre_mercado(String nombre_mercado) {
		this.nombre_mercado = nombre_mercado;
	}
	
}
