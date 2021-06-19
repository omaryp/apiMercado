package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.Serie;
import pe.gob.muni.apimercado.utils.ApiException;

public interface ISerieService extends IBasicService<Serie>{
	
	Serie getSeriePuesto(int idPuesto) throws ApiException,Exception;

}
