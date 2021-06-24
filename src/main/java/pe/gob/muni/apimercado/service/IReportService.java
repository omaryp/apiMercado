package pe.gob.muni.apimercado.service;

import java.util.Map;

import pe.gob.muni.apimercado.utils.ApiException;

public interface IReportService{

	byte[] generarReporte(String template,Map<String, Object> params) throws ApiException,Exception;
}
