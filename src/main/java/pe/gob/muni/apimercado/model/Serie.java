package pe.gob.muni.apimercado.model;

public class Serie extends BasicEntity {

	private String descripcion;
	private int mercados_id;
	private int partidas_id;
	
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
	public int getPartidas_id() {
		return partidas_id;
	}
	public void setPartidas_id(int partidas_id) {
		this.partidas_id = partidas_id;
	}
	
}
