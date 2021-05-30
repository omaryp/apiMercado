package pe.gob.muni.apimercado.service;


import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.ProcesoTicket;

public interface ITicketService extends IBasicService<Ticket>{
	
	void generarTickets(ProcesoTicket params) throws ApiException,Exception;
}
