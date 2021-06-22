package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.muni.apimercado.mapper.IBasicMapper;
import pe.gob.muni.apimercado.model.BasicEntity;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

public class BasicRepository<T extends BasicEntity,M extends IBasicMapper<T>> {

	@Autowired
	protected M mapper;
	
	public T getEntity(int id) throws ApiException {
		try {
			return mapper.getEntity(id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	public List<T> getAllEntitys() throws ApiException {
		try {
			return mapper.getAllEntitys();
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public void saveEntity(T entity)throws ApiException {
		try {
			mapper.saveEntity(entity);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public void updateEntity(T entity)throws ApiException {
		try {
			mapper.updateEntity(entity);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
		
	public List<T> pagingEntitys(GeneralPageTable parms) throws ApiException{
		try {
			return mapper.pagingEntitys(parms);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public void deleteEntity(int id)throws ApiException {
		try {
			mapper.deleteEntity(id);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
}
