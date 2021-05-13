package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.muni.apimercado.mapper.IBasicMapper;
import pe.gob.muni.apimercado.utils.ApiException;

public class BasicRepository<T,M extends IBasicMapper<T>> {

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
	
	public int totalRecordsEntity(String valorBusqueda) throws ApiException{
		try {
			return mapper.totalRecordEntity(valorBusqueda);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public List<T> pagingEntitys(String valorBusqueda, int inicio, int fin ) throws ApiException{
		try {
			return mapper.pagingEntitys(valorBusqueda, inicio, fin );
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
