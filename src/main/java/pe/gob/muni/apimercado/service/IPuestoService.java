package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ValidatorException;

public interface IPuestoService extends IBasicService<Puesto>{
	
	void asociarPuestoComerciante(PuestoComerciante puesto)throws ValidatorException,ApiException,Exception;
	
	List<Puesto> getAllPuestosMercado(int idMercado)throws ApiException;
}
