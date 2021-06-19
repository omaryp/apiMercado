package pe.gob.muni.apimercado.service;


import java.util.Map;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.model.dto.TicketNoHabido;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.ProcesoTicket;

public interface ITicketService extends IBasicService<Ticket>{
	
	void generarTickets(ProcesoTicket params) throws ApiException,Exception;

	PageInfo<TicketDto> pagingTickets(Map<String, String> params) throws ApiException, Exception;
	
	void marcarTicketPagado(int id) throws ApiException, Exception;
	
	void marcarTicketNoHabido(TicketNoHabido ticket)  throws ApiException,Exception;
}
