package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPerfilMapper;
import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.RolPerfil;
import pe.gob.muni.apimercado.utils.ApiException;
        
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