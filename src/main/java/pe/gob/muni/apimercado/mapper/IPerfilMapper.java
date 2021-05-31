package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.RolPerfil;

public interface IPerfilMapper extends IBasicMapper<Perfil> {
	
	void guardarRolesPerfil(List<RolPerfil> entitys) throws SQLException,SQLIntegrityConstraintViolationException;
	void deleteRolPerfil(RolPerfil entity) throws SQLException,SQLIntegrityConstraintViolationException ;

}
