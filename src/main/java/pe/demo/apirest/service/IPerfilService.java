package pe.demo.apirest.service;

import java.util.List;

import pe.demo.apirest.model.Perfil;
import pe.demo.apirest.model.Rol;
import pe.demo.apirest.utils.ApiException;

public interface IPerfilService extends IBasicService<Perfil> {
	
	void guardarRolesPerfil(int perfil, List<Rol> roles)throws ApiException,Exception;
}
