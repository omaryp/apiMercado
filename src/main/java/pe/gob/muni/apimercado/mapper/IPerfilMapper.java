package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;

import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.RolPerfil;

public interface IPerfilMapper extends IBasicMapper<Perfil> {
	
	void guardarRolesPerfil(RolPerfil rol) throws SQLException;

}
