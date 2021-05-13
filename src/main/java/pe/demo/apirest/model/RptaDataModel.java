package pe.demo.apirest.model;

import java.util.List;

public class RptaDataModel<T> {
	
	private int total;
	private List<T> datos;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getDatos() {
		return datos;
	}
	public void setDatos(List<T> datos) {
		this.datos = datos;
	}

}
