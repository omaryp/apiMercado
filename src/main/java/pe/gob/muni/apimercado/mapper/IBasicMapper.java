package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.BasicEntity;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;

@Mapper
public interface IBasicMapper<T extends BasicEntity> {

	T getEntity(int id) throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<T> getAllEntitys() throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<T> pagingEntitys(GeneralPageTable params) throws SQLException,SQLIntegrityConstraintViolationException;
		
	void saveEntity(T entity) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void updateEntity(T entity) throws SQLException,SQLIntegrityConstraintViolationException;
		
	void deleteEntity(int id) throws SQLException,SQLIntegrityConstraintViolationException;
}
