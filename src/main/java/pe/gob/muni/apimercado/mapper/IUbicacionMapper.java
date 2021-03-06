package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Ubicacion;

@Mapper
public interface IUbicacionMapper extends IBasicMapper<Ubicacion> {

	List<Ubicacion> getUbicacionesByMercado(int id) throws SQLException,SQLIntegrityConstraintViolationException;
}
