package pe.gob.muni.apimercado.utils.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProcesoTicket {
	
	@NotNull(message="Atributo fecha proceso no debe ser vacío.")
	private Date fechaProceso;
	private int mercados_id;
	@Min(value=1, message="Atributo creado por debe ser != 0")
	private int creado_por;
	@NotNull(message="Atributo fecha creacion no debe ser vacío.")
	private Date fechaCreacion;

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

	public int getCreado_por() {
		return creado_por;
	}

	public void setCreado_por(int creado_por) {
		this.creado_por = creado_por;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
