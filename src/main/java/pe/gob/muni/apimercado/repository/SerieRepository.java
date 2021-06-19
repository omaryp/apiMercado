package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ISerieMapper;
import pe.gob.muni.apimercado.model.Serie;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class SerieRepository extends BasicRepository<Serie,ISerieMapper> {
	
	public Serie getSeriePuesto(int idPuesto) throws ApiException,Exception{
		try {
			return mapper.getSeriePuesto(idPuesto);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}