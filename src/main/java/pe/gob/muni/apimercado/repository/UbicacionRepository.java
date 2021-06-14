package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IUbicacionMapper;
import pe.gob.muni.apimercado.model.Ubicacion;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class UbicacionRepository extends BasicRepository<Ubicacion,IUbicacionMapper> {
	
	public List<Ubicacion> getUbicacionesByMercado(int idMercado)throws ApiException {
		try {
			return mapper.getUbicacionesByMercado(idMercado);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
}