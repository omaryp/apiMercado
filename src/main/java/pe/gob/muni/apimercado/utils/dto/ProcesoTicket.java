package pe.gob.muni.apimercado.utils.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ProcesoTicket {
	
	@NotNull(message="Atributo fecha proceso no debe ser vacío.")
	private Date fechaProceso;
	private int mercados_id;

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public int getMercados_id() {
		return mercados_id;
	}

	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}

	
}
