package pe.demo.apirest.mapper;

import java.sql.SQLException;

import pe.demo.apirest.model.Perfil;
import pe.demo.apirest.model.RolPerfil;

public interface IPerfilMapper extends IBasicMapper<Perfil> {
	
	void guardarRolesPerfil(RolPerfil rol) throws SQLException;

}
