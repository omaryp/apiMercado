package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;

public class Ticket extends BasicEntity {
	
	@Min(value=1, message="Atributo comerciante_id debe ser != 0")
	private int comerciante_id;
	@Min(value=1, message="Atributo puesto_id debe ser != 0")
	private int puesto_id;
	@Min(value=1, message="Atributo correlativo debe ser != 0")
	private int correlativo;
	
	private boolean habido;

	public int getComerciante_id() {
		return comerciante_id;
	}

	public void setComerciante_id(int comerciante_id) {
		this.comerciante_id = comerciante_id;
	}

	public int getPuesto_id() {
		return puesto_id;
	}

	public void setPuesto_id(int puesto_id) {
		this.puesto_id = puesto_id;
	}

	public int getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}

	public boolean isHabido() {
		return habido;
	}

	public void setHabido(boolean habido) {
		this.habido = habido;
	}
	
		
}
