package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IBasicMapper<T> {

	T getEntity(int id) throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<T> getAllEntitys() throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<T> pagingEntitys(String valorBusqueda, int inicio, int fin) throws SQLException,SQLIntegrityConstraintViolationException;
	
	int totalRecordEntity(String valorBusqueda) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void saveEntity(T perfil) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void updateEntity(T perfil) throws SQLException,SQLIntegrityConstraintViolationException;
		
	void deleteEntity(int id) throws SQLException,SQLIntegrityConstraintViolationException;
}
