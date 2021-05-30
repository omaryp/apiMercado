package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPuestoComercianteMapper;
import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class PuestoComercianteRepository extends BasicRepository<PuestoComerciante,IPuestoComercianteMapper> {
	

	public List<PuestoComerciante> getAllPuestosActive()throws ApiException {
		try {
			return mapper.getAllPuestosActive();
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<PuestoComerciante> getAllPuestosMercado(int mercados_id)throws ApiException {
		try {
			return mapper.getAllPuestosMercado(mercados_id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}