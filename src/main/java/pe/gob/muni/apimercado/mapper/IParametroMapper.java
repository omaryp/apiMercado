package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Parametro;

@Mapper
public interface IParametroMapper {
	
	Parametro getEntity(int codigo, int subcodigo) throws SQLException,SQLIntegrityConstraintViolationException;
	
	List<Parametro> getAllEntitys(int codigo) throws SQLException,SQLIntegrityConstraintViolationException;
		
	void saveEntity(Parametro entity) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void saveAllEntity(List<Parametro> items) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void updateEntity(Parametro entity) throws SQLException,SQLIntegrityConstraintViolationException;
		
	void deleteEntity(int codigo, int subcodigo) throws SQLException,SQLIntegrityConstraintViolationException;
	
}
