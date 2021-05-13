package pe.gob.muni.apimercado.service;

import java.util.List;
import java.util.Map;

import pe.gob.muni.apimercado.model.BasicEntity;
import pe.gob.muni.apimercado.model.RptaDataModel;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ValidatorException;

public interface IBasicService<T extends BasicEntity> {
	
	Object searchEntity(Map<String, String> params)throws ApiException,Exception;
		
	T getEntity(int id) throws ApiException,Exception;
	
	List<T> getAllEntitys() throws ApiException,Exception;
	
	RptaDataModel<T> pagingEntitys(String valorBusqueda,int tipoBusqueda, int inicio, int fin ) throws ApiException,Exception;
	
	void saveEntity(T entity) throws ApiException,Exception,ValidatorException;
	
	void updateEntity(T entity) throws ApiException,Exception,ValidatorException; 
	
	void deleteEntity(int id) throws ApiException,Exception;
}
