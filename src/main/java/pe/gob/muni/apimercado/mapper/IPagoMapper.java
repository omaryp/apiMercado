package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.TicketPago;

@Mapper
public interface IPagoMapper extends IBasicMapper<Pago> {

	void asociarTicketPago(TicketPago ticket) throws SQLException,SQLIntegrityConstraintViolationException;
}
