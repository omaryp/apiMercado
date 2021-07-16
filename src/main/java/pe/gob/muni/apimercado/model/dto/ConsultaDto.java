package pe.gob.muni.apimercado.model.dto;

import java.util.List;

public class ConsultaDto {

	private String nombres;
	private String apellidos;
	private List<PagoDto> pagos;
	private List<TicketDto> deudas;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public List<PagoDto> getPagos() {
		return pagos;
	}
	public void setPagos(List<PagoDto> pagos) {
		this.pagos = pagos;
	}
	public List<TicketDto> getDeudas() {
		return deudas;
	}
	public void setDeudas(List<TicketDto> deudas) {
		this.deudas = deudas;
	}
	
}
