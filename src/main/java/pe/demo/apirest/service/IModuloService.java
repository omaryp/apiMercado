package pe.demo.apirest.service;

import java.util.List;

import pe.demo.apirest.model.Modulo;
import pe.demo.apirest.utils.ApiException;

public interface IModuloService extends IBasicService<Modulo> {
	
	List<Modulo> getModulosByUsuario(String usuario) throws ApiException,Exception;

}
