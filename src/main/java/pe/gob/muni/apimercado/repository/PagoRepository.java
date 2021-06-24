package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPagoMapper;
import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.TicketPago;
import pe.gob.muni.apimercado.model.dto.PagoDto;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.PageTablePago;

@Repository
public class PagoRepository extends BasicRepository<Pago,IPagoMapper> {
	
	public void asociarTicketPago(List<TicketPago> tickets) throws ApiException {
		try {
			mapper.asociarTicketPago(tickets);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public void saveAllPagos(List<Pago> pagos) throws ApiException {
		try {
			mapper.saveAllPagos(pagos);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public List<PagoDto> pagingPagos(PageTablePago params) throws ApiException {
		try {
			return mapper.pagingPagos(params);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public PagoDto getEntityPagoDto(int id)throws ApiException {
		try {
			return mapper.getEntityPagoDto(id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public PagoDto getEntityPagoDtoTicket(int id)throws ApiException {
		try {
			return mapper.getEntityPagoDtoTicket(id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}