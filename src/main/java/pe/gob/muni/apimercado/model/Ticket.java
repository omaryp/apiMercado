package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;

public class Ticket extends BasicEntity {
	
	@Min(value=1, message="Atributo mercado_id debe ser != 0")
	private int mercados_id;
	@Min(value=1, message="Atributo comerciante_id debe ser != 0")
	private int comerciantes_id;
	@Min(value=1, message="Atributo ubicaciones_id debe ser != 0")
	private int ubicaciones_id;
	@Min(value=1, message="Atributo puesto_id debe ser != 0")
	private int puestos_id;
	@Min(value=1, message="Atributo correlativo debe ser != 0")
	private long correlativo;
	private int pagado;
	
	private boolean no_habido;
	private String observaciones;

	public int getMercados_id() {
		return mercados_id;
	}

	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}

	public int getComerciantes_id() {
		return comerciantes_id;
	}

	public void setComerciantes_id(int comerciantes_id) {
		this.comerciantes_id = comerciantes_id;
	}

	public int getPuestos_id() {
		return puestos_id;
	}

	public void setPuestos_id(int puestos_id) {
		this.puestos_id = puestos_id;
	}

	public long getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}

	public boolean isNo_habido() {
		return no_habido;
	}

	public void setNo_habido(boolean no_habido) {
		this.no_habido = no_habido;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getUbicaciones_id() {
		return ubicaciones_id;
	}

	public void setUbicaciones_id(int ubicaciones_id) {
		this.ubicaciones_id = ubicaciones_id;
	}

	public int getPagado() {
		return pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}

		
}
