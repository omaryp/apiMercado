package pe.demo.apirest.service;

import java.util.List;

import pe.demo.apirest.model.Rol;
import pe.demo.apirest.utils.ApiException;

public interface IRolService extends IBasicService<Rol>{
	
	List<Rol> getRolesByPerfil(int perfil) throws ApiException,Exception;
	
	List<Rol> getRolesByUsuario(String usuario) throws ApiException,Exception;
	
}
