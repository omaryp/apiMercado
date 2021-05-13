package pe.demo.apirest.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;
import pe.demo.apirest.model.Usuario;

@Mapper
public interface IUsuarioMapper extends IBasicMapper<Usuario> {
	
	Usuario findByUsername(String username) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void disabledUserbyUsername(String username) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void disabledUserbyCodigo(int idUsuario ) throws SQLException,SQLIntegrityConstraintViolationException;

	void actualizarUserByUsername(Usuario user) throws SQLException,SQLIntegrityConstraintViolationException;

}
