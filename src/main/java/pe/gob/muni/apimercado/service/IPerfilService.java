package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IPerfilService extends IBasicService<Perfil> {
	
	void guardarRolesPerfil(int perfil, List<Rol> roles)throws ApiException,Exception;
}
