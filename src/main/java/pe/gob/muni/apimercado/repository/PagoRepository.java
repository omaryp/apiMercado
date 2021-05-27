package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPagoMapper;
import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.TicketPago;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class PagoRepository extends BasicRepository<Pago,IPagoMapper> {
	
	public void asociarTicketPago(TicketPago ticket) throws ApiException {
		try {
			mapper.asociarTicketPago(ticket);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
}