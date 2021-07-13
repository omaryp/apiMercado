package pe.gob.muni.apimercado.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.TicketPago;
import pe.gob.muni.apimercado.model.dto.PagoDto;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.EnvioDto;

public interface IPagoService extends IBasicService<Pago>{
	
	 void asociarTicketPago(List<TicketPago> tickets) throws ApiException, Exception;
	 
	 void pagoTickets(List<Ticket> tickets) throws ApiException, Exception;
	 
	 PageInfo<PagoDto> pagingTickets(Map<String, String> params) throws ApiException, Exception;
	 
	 byte[] reporteTicketPago(Map<String,String> params) throws ApiException, Exception;
	 
	 PagoDto getEntityPagoDto(int id) throws ApiException, Exception;
	 
	 PagoDto getEntityPagoDtoTicket(int id) throws ApiException, Exception;
	 
	 void enviarCorreo(EnvioDto entity) throws ApiException, Exception;

	 byte[] reportePagos(Map<String, String> params) throws ApiException, Exception;
	 
	 byte[] reporteConsolidadoPagos(Map<String, String> params) throws ApiException, Exception;
}
