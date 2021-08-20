package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;
import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.UsuarioSessionDto;

@Mapper
public interface IUsuarioMapper extends IBasicMapper<Usuario> {
	
	Usuario findByUsername(String username) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void disabledUserbyUsername(String username) throws SQLException,SQLIntegrityConstraintViolationException;
	
	void disabledUserbyCodigo(int idUsuario ) throws SQLException,SQLIntegrityConstraintViolationException;

	void actualizarUserByUsername(Usuario user) throws SQLException,SQLIntegrityConstraintViolationException;

	UsuarioSessionDto getDatosSession(int id) throws SQLException,SQLIntegrityConstraintViolationException;
}
