package pe.gob.muni.apimercado.service;

import java.util.List;

import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IPuestoComercianteService extends IBasicService<PuestoComerciante>{
	
	List<PuestoComerciante> getAllPuestosActive() throws ApiException; 
	
	List<PuestoComerciante> getAllPuestosMercado(int mercados_id) throws ApiException; 
}
