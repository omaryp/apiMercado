package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Tarifa;

@Mapper
public interface ITarifaMapper extends IBasicMapper<Tarifa> {
	
	Tarifa getTarifaPuesto(int idPuesto) throws SQLException,SQLIntegrityConstraintViolationException;
	
}
