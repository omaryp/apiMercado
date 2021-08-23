package pe.gob.muni.apimercado.repository;


import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IUsuarioMapper;
import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.UsuarioSessionDto;
import pe.gob.muni.apimercado.utils.ApiException;

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
	
	public UsuarioSessionDto getDatosSession(int id) throws ApiException{
		try {
			return mapper.getDatosSession(id);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}
	
	public void updateEntityUsuario(Usuario datos) throws ApiException{
		try {
			mapper.updateEntityUsuario(datos);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}
	
}