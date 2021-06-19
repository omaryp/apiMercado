package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.Tarifa;
import pe.gob.muni.apimercado.utils.ApiException;

public interface ITarifaService extends IBasicService<Tarifa>{
	
	Tarifa getTarifaPuesto(int idPuesto) throws ApiException, Exception;

}
