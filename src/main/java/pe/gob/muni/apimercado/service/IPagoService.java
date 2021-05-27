package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.TicketPago;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IPagoService extends IBasicService<Pago>{
	
	 void asociarTicketPago(TicketPago ticket) throws ApiException;
}
