package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Ubicacion;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IUbicacionService extends IBasicService<Ubicacion>{
	
	List<Ubicacion> getUbicacionesByMercado(int idMercado)throws ApiException,Exception;
}
