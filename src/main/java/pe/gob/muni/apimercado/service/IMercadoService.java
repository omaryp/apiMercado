package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Mercado;
import pe.gob.muni.apimercado.model.UbicacionMercado;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IMercadoService extends IBasicService<Mercado>{
	
	void guardarUbicacionMercado(List<UbicacionMercado> entitys) throws ApiException,Exception;
	
	void deleteUbicacionMercado(List<UbicacionMercado> entitys) throws ApiException,Exception;

}
