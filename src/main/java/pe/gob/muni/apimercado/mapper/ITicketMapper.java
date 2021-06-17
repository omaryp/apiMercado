package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Ticket;

@Mapper
public interface ITicketMapper extends IBasicMapper<Ticket> {
	void saveAllTickets(List<Ticket> list) throws SQLException,SQLIntegrityConstraintViolationException;
}
