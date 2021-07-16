package pe.gob.muni.apimercado.service;

import java.util.Map;

import pe.gob.muni.apimercado.model.dto.ConsultaDto;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IConsultaService{

	ConsultaDto consultarDatosComerciante(Map<String,String> params) throws ApiException, Exception ;

}
