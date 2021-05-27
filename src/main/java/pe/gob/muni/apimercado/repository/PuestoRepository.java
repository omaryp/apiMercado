package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPuestoMapper;
import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class PuestoRepository extends BasicRepository<Puesto,IPuestoMapper> {
	
	public void asociarPuestoComerciante(PuestoComerciante puesto)throws ApiException {
		try {
			mapper.asociarPuestoComerciante(puesto);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
}