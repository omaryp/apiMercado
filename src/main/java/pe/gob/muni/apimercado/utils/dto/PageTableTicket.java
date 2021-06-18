package pe.gob.muni.apimercado.utils.dto;

import java.util.Date;

public class PageTableTicket {
	
	private int page;
	private int offset = 0;
	private int limit = 10;
	private Date fecha_incio;
	private Date fecha_fin;
	private int comerciantes_id;
	private int mercados_id;
	private boolean pagado;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
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
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
		
}