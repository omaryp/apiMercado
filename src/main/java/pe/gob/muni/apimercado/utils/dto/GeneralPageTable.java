package pe.gob.muni.apimercado.utils.dto;

public class GeneralPageTable extends BasicPageTable {
	
	private String search;
	private int filter;
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getFilter() {
		return filter;
	}

	public void setFilter(int filter) {
		this.filter = filter;
	}
	
}