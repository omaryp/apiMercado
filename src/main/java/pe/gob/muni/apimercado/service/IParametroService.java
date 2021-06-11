package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.Parametro;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ValidatorException;

public interface IParametroService{
	
	Parametro getEntity(int codigo, int subcodigo) throws ApiException;
	
	List<Parametro> getAllEntitys(int codigo) throws ApiException;
		
	void saveEntity(Parametro entity) throws ApiException,Exception,ValidatorException;
	
	void saveAllEntity(List<Parametro> items) throws ApiException,Exception;
	
	void updateEntity(Parametro entity) throws ApiException,Exception,ValidatorException;
		
	void deleteEntity(int codigo, int subcodigo) throws ApiException;
}
