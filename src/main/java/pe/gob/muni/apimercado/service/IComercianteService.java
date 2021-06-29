package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IComercianteService extends IBasicService<Comerciante>{

	byte[] reporteAsistencia(int id) throws ApiException, Exception;
	

}
