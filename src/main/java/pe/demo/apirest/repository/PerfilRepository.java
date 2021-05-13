package pe.demo.apirest.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.demo.apirest.mapper.IPerfilMapper;
import pe.demo.apirest.model.Perfil;
import pe.demo.apirest.model.RolPerfil;
import pe.demo.apirest.utils.ApiException;
        
@Repository
public class PerfilRepository extends BasicRepository<Perfil, IPerfilMapper> {
		
	public void guardarRolesPerfil(RolPerfil rol)throws ApiException {
		try {
			mapper.guardarRolesPerfil(rol);
		} catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		}
	}
	
}