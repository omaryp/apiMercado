package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.model.dto.TicketNoHabido;
import pe.gob.muni.apimercado.utils.dto.PageTableTicket;

@Mapper
public interface ITicketMapper extends IBasicMapper<Ticket> {
	
	void saveAllTickets(List<Ticket> list) throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<TicketDto> pagingTickets(PageTableTicket params) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void marcarTicketPagado(int id) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void marcarTicketNoHabido(TicketNoHabido ticket) throws SQLException,SQLIntegrityConstraintViolationException;
	
}
