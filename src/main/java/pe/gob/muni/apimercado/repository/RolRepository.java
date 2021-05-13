package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IRolMapper;
import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class RolRepository extends BasicRepository<Rol, IRolMapper> {
		
	public List<Rol> getRolesByPerfil(int perfil) throws ApiException {
		try {
			return mapper.getRolesByPerfil(perfil);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	public List<Rol> getRolesByUsuario(String usuario) throws ApiException {
		try {
			return mapper.getRolesByUsuario(usuario);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}

}