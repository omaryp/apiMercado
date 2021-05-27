package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.PuestoComerciante;

@Mapper
public interface IPuestoMapper extends IBasicMapper<Puesto> {
	
	void asociarPuestoComerciante(PuestoComerciante puesto) throws SQLException,SQLIntegrityConstraintViolationException; 
}
