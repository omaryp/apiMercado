package pe.gob.muni.apimercado.service;

import java.util.Map;

import pe.gob.muni.apimercado.model.dto.DashboardDto;
import pe.gob.muni.apimercado.utils.ApiException;

public interface IDashBoardService {
	
	DashboardDto datosDashBoard(Map<String, String> params) throws ApiException, Exception;
}
