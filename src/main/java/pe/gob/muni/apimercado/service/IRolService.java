package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Rol;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IRolService extends IBasicService<Rol>{
	
	List<Rol> getRolesByPerfil(int perfil) throws ApiException,Exception;
	
	List<Rol> getRolesByUsuario(String usuario) throws ApiException,Exception;
	
}
