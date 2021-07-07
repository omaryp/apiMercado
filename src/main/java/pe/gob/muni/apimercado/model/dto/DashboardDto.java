package pe.gob.muni.apimercado.model.dto;

import java.util.List;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.utils.dto.Monto;

public class DashboardDto {
	
	private long comerciantes;
	private long puestos;
	private List<Comerciante> top_10_puntuales;
	private List<Comerciante> top_10_deudores;
	private List<Monto> cobrado;
	private List<Monto> pendiente;
	
	public long getComerciantes() {
		return comerciantes;
	}
	public void setComerciantes(long comerciantes) {
		this.comerciantes = comerciantes;
	}
	public long getPuestos() {
		return puestos;
	}
	public void setPuestos(long puestos) {
		this.puestos = puestos;
	}
	public List<Comerciante> getTop_10_puntuales() {
		return top_10_puntuales;
	}
	public void setTop_10_puntuales(List<Comerciante> top_10_puntuales) {
		this.top_10_puntuales = top_10_puntuales;
	}
	public List<Comerciante> getTop_10_deudores() {
		return top_10_deudores;
	}
	public void setTop_10_deudores(List<Comerciante> top_10_deudores) {
		this.top_10_deudores = top_10_deudores;
	}
	public List<Monto> getCobrado() {
		return cobrado;
	}
	public void setCobrado(List<Monto> cobrado) {
		this.cobrado = cobrado;
	}
	public List<Monto> getPendiente() {
		return pendiente;
	}
	public void setPendiente(List<Monto> pendiente) {
		this.pendiente = pendiente;
	}
	
}
