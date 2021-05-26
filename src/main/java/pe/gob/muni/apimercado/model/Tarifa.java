package pe.gob.muni.apimercado.model;

import java.sql.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Tarifa extends BasicEntity {
	
	@NotBlank(message="Atributo descripcion no debe ser vacío.")
	@NotNull(message="Atributo descripcion no debe ser vacío.")
	private String descripcion;
	@DecimalMin(value = "0.001", message = "Atributo monto debe ser != 0" )
	private double monto;
	@Min(value=1, message="Atributo conceptos_id debe ser != 0")
	private int conceptos_id;
	@NotNull(message="Atributo fecha_incio no debe ser vacío.")
	private Date fecha_incio;
	@NotNull(message="Atributo fecha_fin no debe ser vacío.")
	private Date fecha_fin;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public int getConceptos_id() {
		return conceptos_id;
	}
	public void setConceptos_id(int conceptos_id) {
		this.conceptos_id = conceptos_id;
	}
	public Date getFecha_incio() {
		return fecha_incio;
	}
	public void setFecha_incio(Date fecha_incio) {
		this.fecha_incio = fecha_incio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	

	
}
