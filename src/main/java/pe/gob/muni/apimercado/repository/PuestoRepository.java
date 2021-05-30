package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPuestoMapper;
import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class PuestoRepository extends BasicRepository<Puesto,IPuestoMapper> {
	
	public List<Puesto> getAllPuestosMercado(int idMercado)throws ApiException {
		try {
			return mapper.getAllPuestosMercado(idMercado);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}