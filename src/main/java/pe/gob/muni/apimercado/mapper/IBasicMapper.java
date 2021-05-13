package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.BasicEntity;

@Mapper
public interface IBasicMapper<T extends BasicEntity> {

	T getEntity(int id) throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<T> getAllEntitys() throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<T> pagingEntitys(String valorBusqueda, int inicio, int fin) throws SQLException,SQLIntegrityConstraintViolationException;
	
	int totalRecordEntity(String valorBusqueda) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void saveEntity(T perfil) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void updateEntity(T perfil) throws SQLException,SQLIntegrityConstraintViolationException;
		
	void deleteEntity(int id) throws SQLException,SQLIntegrityConstraintViolationException;
}
