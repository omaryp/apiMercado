package pe.gob.muni.apimercado.model;

public class Puesto extends BasicEntity {
	
	private int codigo;
	private int mercado_id;
	private int comerciantes_id;
	private int tarifas_id;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getMercado_id() {
		return mercado_id;
	}
	public void setMercado_id(int mercado_id) {
		this.mercado_id = mercado_id;
	}
	public int getComerciantes_id() {
		return comerciantes_id;
	}
	public void setComerciantes_id(int comerciantes_id) {
		this.comerciantes_id = comerciantes_id;
	}
	public int getTarifas_id() {
		return tarifas_id;
	}
	public void setTarifas_id(int tarifas_id) {
		this.tarifas_id = tarifas_id;
	}
	
}
