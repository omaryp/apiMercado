package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ITicketMapper;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.utils.ApiException;

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
}