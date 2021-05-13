package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Modulo;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IModuloService extends IBasicService<Modulo> {
	
	List<Modulo> getModulosByUsuario(String usuario) throws ApiException,Exception;

}
