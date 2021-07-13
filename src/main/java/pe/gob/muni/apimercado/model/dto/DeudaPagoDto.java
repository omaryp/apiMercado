package pe.gob.muni.apimercado.model.dto;

public class DeudaPagoDto {
	
	private String dni;
	private String nombres;
	private String apellidos;	
	private String mercado;
	private String puesto;
	private double tarifa;
	private int nroTickets;
	private double total;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getMercado() {
		return mercado;
	}
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public double getTarifa() {
		return tarifa;
	}
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getNroTickets() {
		return nroTickets;
	}
	public void setNroTickets(int nroTickets) {
		this.nroTickets = nroTickets;
	}
	
}
