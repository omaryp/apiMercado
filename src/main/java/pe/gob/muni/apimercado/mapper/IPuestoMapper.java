package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Puesto;

@Mapper
public interface IPuestoMapper extends IBasicMapper<Puesto> {
	
	
	List<Puesto> getAllPuestosMercado(int id) throws SQLException,SQLIntegrityConstraintViolationException; 
	
}
