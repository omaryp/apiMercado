package pe.demo.apirest.service;

import java.util.List;
import java.util.Map;

import pe.demo.apirest.model.RptaDataModel;
import pe.demo.apirest.utils.ApiException;
import pe.demo.apirest.utils.ValidatorException;

public interface IBasicService<T> {
	
	Object searchEntity(Map<String, String> params)throws ApiException,Exception;
		
	T getEntity(int id) throws ApiException,Exception;
	
	List<T> getAllEntitys() throws ApiException,Exception;
	
	RptaDataModel<T> pagingEntitys(String valorBusqueda,int tipoBusqueda, int inicio, int fin ) throws ApiException,Exception;
	
	void saveEntity(T entity) throws ApiException,Exception,ValidatorException;
	
	void updateEntity(T entity) throws ApiException,Exception,ValidatorException; 
	
	void deleteEntity(int id) throws ApiException,Exception;
}
