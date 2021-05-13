package pe.demo.apirest.repository;


import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import pe.demo.apirest.mapper.IUsuarioMapper;
import pe.demo.apirest.model.Usuario;
import pe.demo.apirest.utils.ApiException;

@Repository
public class UsuarioRespository extends BasicRepository<Usuario, IUsuarioMapper> {
	
	public Usuario findByUsername(String username) throws ApiException{
		try {
			return mapper.findByUsername(username);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(), e);
		}
		
	}

	public void disabledUserbyUsername(String username) throws ApiException{
		try {
			mapper.disabledUserbyUsername(username);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(), e);
		}
		
	}
	
	public void disabledUserbyCodigo(int idUsuario ) throws ApiException{
		try {
			mapper.disabledUserbyCodigo(idUsuario);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(), e);
		}
		
	}
	
	public void actualizarUserByUsername(Usuario user) throws ApiException{
		try {
			mapper.actualizarUserByUsername(user);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(), e);
		}
		
	}
	
}