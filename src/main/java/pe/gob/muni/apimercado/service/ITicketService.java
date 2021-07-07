package pe.gob.muni.apimercado.service;

import java.util.Date;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.model.dto.TicketVisita;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.ProcesoTicket;

public interface ITicketService extends IBasicService<Ticket>{

	void generarTickets(ProcesoTicket params) throws ApiException,Exception;

	PageInfo<TicketDto> pagingTickets(Map<String, String> params) throws ApiException, Exception;
	
	void marcarTicketPagado(int id,int estado_visita) throws ApiException, Exception;
	
	void marcarEstadoVisitaTicket(TicketVisita ticket)  throws ApiException,Exception;
	
	int getTicketsFechaMercado(int mercados_id, Date fecha_ticket) throws ApiException, Exception;
	
	byte[] deudaConsolidado(Map<String, String> params) throws ApiException, Exception;
	
}
