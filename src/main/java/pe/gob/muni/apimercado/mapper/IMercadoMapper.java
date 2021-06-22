package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Mercado;
import pe.gob.muni.apimercado.model.UbicacionMercado;

@Mapper
public interface IMercadoMapper extends IBasicMapper<Mercado> {

	void guardarUbicacionMercado(List<UbicacionMercado> entitys) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void deleteUbicacionMercado(UbicacionMercado entity) throws SQLException,SQLIntegrityConstraintViolationException;

	List<Mercado> getMercadoUserCobrador(int id)throws SQLException,SQLIntegrityConstraintViolationException;
}
