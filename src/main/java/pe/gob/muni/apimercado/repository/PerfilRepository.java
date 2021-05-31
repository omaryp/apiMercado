package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.IPerfilMapper;
import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.RolPerfil;
import pe.gob.muni.apimercado.utils.ApiException;
        
@Repository
public class PerfilRepository extends BasicRepository<Perfil, IPerfilMapper> {
		
	public void guardarRolesPerfil(List<RolPerfil> entitys)throws ApiException {
		try {
			mapper.guardarRolesPerfil(entitys);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
	
	public void deleteRolPerfil(RolPerfil entity)throws ApiException {
		try {
			mapper.deleteRolPerfil(entity);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}