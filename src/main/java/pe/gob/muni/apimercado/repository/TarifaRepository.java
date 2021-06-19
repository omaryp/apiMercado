package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ITarifaMapper;
import pe.gob.muni.apimercado.model.Tarifa;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class TarifaRepository extends BasicRepository<Tarifa,ITarifaMapper> {
	
	public Tarifa getTarifaPuesto(int idPuesto) throws ApiException, Exception{
		try {
			return mapper.getTarifaPuesto(idPuesto);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}