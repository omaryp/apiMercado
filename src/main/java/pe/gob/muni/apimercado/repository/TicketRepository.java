package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ITicketMapper;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.model.dto.TicketVisita;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.PageTableTicket;

@Repository
public class TicketRepository extends BasicRepository<Ticket,ITicketMapper> {

	public void saveAllTickets(List<Ticket> tickets) throws ApiException {
		try {
			mapper.saveAllTickets(tickets);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<TicketDto> pagingTickets(PageTableTicket params) throws ApiException {
		try {
			return mapper.pagingTickets(params);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public void marcarTicketPagado(int id)  throws ApiException {
		try {
			mapper.marcarTicketPagado(id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public void marcarEstadoVisitaTicket(TicketVisita ticket)  throws ApiException {
		try {
			mapper.marcarEstadoVisitaTicket(ticket);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public int getTicketsFechaMercado(int mercados_id, Date fecha_ticket) throws ApiException {
		try {
			return mapper.getTicketsFechaMercado(mercados_id,fecha_ticket);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
}