package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Perfil;
import pe.gob.muni.apimercado.model.RolPerfil;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IPerfilService extends IBasicService<Perfil> {
	
	void guardarRolesPerfil(List<RolPerfil> entitys) throws ApiException,Exception;
	void eliminarRolesPerfil(List<RolPerfil> entitys) throws ApiException, Exception ;
}
