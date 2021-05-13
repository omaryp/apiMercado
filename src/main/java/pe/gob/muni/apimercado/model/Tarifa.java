package pe.gob.muni.apimercado.model;

public class Tarifa extends BasicEntity {
	
	private int codigo;
	private double monto;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
		
}
