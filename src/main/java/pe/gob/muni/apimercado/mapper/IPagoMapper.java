package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.TicketPago;

@Mapper
public interface IPagoMapper extends IBasicMapper<Pago> {

	void asociarTicketPago(List<TicketPago> list) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void saveAllPagos(List<Pago> pagos) throws SQLException,SQLIntegrityConstraintViolationException;
}
