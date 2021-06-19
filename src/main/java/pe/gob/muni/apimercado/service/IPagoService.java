package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.TicketPago;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IPagoService extends IBasicService<Pago>{
	
	 void asociarTicketPago(List<TicketPago> tickets) throws ApiException, Exception;
	 
	 void pagoTickets(List<Ticket> tickets) throws ApiException, Exception;
	 
}
