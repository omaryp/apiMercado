package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Serie;

@Mapper
public interface ISerieMapper extends IBasicMapper<Serie> {
	Serie getSeriePuesto(int idPuesto) throws SQLException,SQLIntegrityConstraintViolationException;
}
