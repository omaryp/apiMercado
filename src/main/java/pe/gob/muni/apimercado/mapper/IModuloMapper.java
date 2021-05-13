package pe.demo.apirest.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pe.demo.apirest.model.Modulo;

@Mapper
public interface IModuloMapper extends IBasicMapper<Modulo> {

	List<Modulo> getModulosByUsuario(String usuario) throws SQLException,SQLIntegrityConstraintViolationException;
	List<Modulo> getAllModulos() throws SQLException,SQLIntegrityConstraintViolationException;
}
