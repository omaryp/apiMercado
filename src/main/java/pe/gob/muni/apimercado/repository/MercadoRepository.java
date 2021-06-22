package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IMercadoMapper;
import pe.gob.muni.apimercado.model.Mercado;
import pe.gob.muni.apimercado.model.UbicacionMercado;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class MercadoRepository extends BasicRepository<Mercado,IMercadoMapper> {
	
	public void guardarUbicacionMercado(List<UbicacionMercado> entitys)throws ApiException {
		try {
			mapper.guardarUbicacionMercado(entitys);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public void deleteUbicacionMercado(UbicacionMercado entity)throws ApiException {
		try {
			mapper.deleteUbicacionMercado(entity);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<Mercado> getMercadoUserCobrador(int id) throws ApiException {
		try {
			return mapper.getMercadoUserCobrador(id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
}