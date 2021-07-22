package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.dto.ConsultaDto;
import pe.gob.muni.apimercado.model.dto.RequestDto;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IConsultaService{

	ConsultaDto consultarDatosComerciante(RequestDto params) throws ApiException, Exception ;

}
