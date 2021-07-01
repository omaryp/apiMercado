package pe.gob.muni.apimercado.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Puesto;
import pe.gob.muni.apimercado.model.PuestoComerciante;
import pe.gob.muni.apimercado.model.dto.PuestoDto;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ValidatorException;

public interface IPuestoService extends IBasicService<Puesto>{
	
	void asociarPuestoComerciante(PuestoComerciante puesto)throws ValidatorException,ApiException,Exception;

	PageInfo<PuestoDto> pagingDtoEntitys(Map<String, String> params) throws ApiException,Exception;

	List<PuestoDto> getAllPuestosDtoMercado(int idMercado) throws ApiException;

	void eliminarPuestoComerciante(int comerciante, int puesto) throws ApiException, Exception;

	byte[] reportePuestos(Map<String, String> datos) throws ApiException, Exception;
}
