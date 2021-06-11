package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IParametroMapper;
import pe.gob.muni.apimercado.model.Parametro;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class ParametroRepository  {
	
	@Autowired
	protected IParametroMapper mapper;
	

	public Parametro getEntity(int codigo, int subcodigo) throws ApiException{
		try {
			return mapper.getEntity(codigo, subcodigo);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<Parametro> getAllEntitys(int codigo) throws ApiException{
		try {
			return mapper.getAllEntitys(codigo);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
		
	public void saveEntity(Parametro entity) throws ApiException{
		try {
			mapper.saveEntity(entity);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public void saveAllEntity(List<Parametro> items) throws ApiException{
		try {
			mapper.saveAllEntity(items);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public void updateEntity(Parametro entity) throws ApiException{
		try {
			mapper.updateEntity(entity);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
		
	public void deleteEntity(int codigo, int subcodigo) throws ApiException{
		try {
			mapper.deleteEntity(codigo, subcodigo);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}