package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IPuestoService extends IBasicService<Puesto>{
	
	void asociarPuestoComerciante(PuestoComerciante puesto)throws ApiException;
}
