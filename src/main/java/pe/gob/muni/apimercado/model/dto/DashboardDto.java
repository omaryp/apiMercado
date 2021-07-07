package pe.gob.muni.apimercado.model.dto;

import java.util.List;

import pe.gob.muni.apimercado.utils.dto.Monto;

public class DashboardDto {
	
	private long comerciantes;
	private long puestos;
	private List<ComercianteMontoDto> mayor_recaudacion;
	private List<ComercianteMontoDto> top_10_deudores;
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
	public List<ComercianteMontoDto> getMayor_recaudacion() {
		return mayor_recaudacion;
	}
	public void setMayor_recaudacion(List<ComercianteMontoDto> mayor_recaudacion) {
		this.mayor_recaudacion = mayor_recaudacion;
	}
	public List<ComercianteMontoDto> getTop_10_deudores() {
		return top_10_deudores;
	}
	public void setTop_10_deudores(List<ComercianteMontoDto> top_10_deudores) {
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
