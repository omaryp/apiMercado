package pe.gob.muni.apimercado.utils.dto;

public class BasicPageTable {
	
	private int page;
	private int offset = 0;
	private int limit = 10;
	private String sort;
	private String order;
	private int mercados_id;
	
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getMercados_id() {
		return mercados_id;
	}
	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}

}
