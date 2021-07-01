package pe.gob.muni.apimercado.service;

import java.util.Map;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IComercianteService extends IBasicService<Comerciante>{

	byte[] reporteAsistencia(Map<String, String> params) throws ApiException, Exception;

	byte[] reporteDeuda(Map<String, String> params) throws ApiException, Exception;

	byte[] reporteComericantes(Map<String, String> datos) throws ApiException, Exception;
	
}
