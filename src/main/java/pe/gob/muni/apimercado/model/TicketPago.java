package pe.gob.muni.apimercado.model;


public class TicketPago extends BasicEntity {
	
	private int tickets_id;
	private int pagos_id;
	
	public int getTickets_id() {
		return tickets_id;
	}
	public void setTickets_id(int tickets_id) {
		this.tickets_id = tickets_id;
	}
	public int getPagos_id() {
		return pagos_id;
	}
	public void setPagos_id(int pagos_id) {
		this.pagos_id = pagos_id;
	}
	
	
		
}
