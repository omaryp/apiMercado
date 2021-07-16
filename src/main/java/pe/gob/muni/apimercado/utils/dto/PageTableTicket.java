package pe.gob.muni.apimercado.utils.dto;


public class PageTableTicket extends BasicPageTable {

	
	private int comerciantes_id;
	private int mercados_id;
	private int ubicaciones_id;
	private int pagado;
	private String codigo_qr;
	
	public int getComerciantes_id() {
		return comerciantes_id;
	}
	public void setComerciantes_id(int comerciantes_id) {
		this.comerciantes_id = comerciantes_id;
	}
	public int getMercados_id() {
		return mercados_id;
	}
	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}
	public int getPagado() {
		return pagado;
	}
	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
	public int getUbicaciones_id() {
		return ubicaciones_id;
	}
	public void setUbicaciones_id(int ubicaciones_id) {
		this.ubicaciones_id = ubicaciones_id;
	}
	public String getCodigo_qr() {
		return codigo_qr;
	}
	public void setCodigo_qr(String codigo_qr) {
		this.codigo_qr = codigo_qr;
	}

}