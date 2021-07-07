package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.dto.DashboardDto;
import pe.gob.muni.apimercado.service.DashBoardService;
import pe.gob.muni.apimercado.utils.ApiException;

@RestController
@RequestMapping("/dashboard")
public class DashBoardApi{
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private DashBoardService service;
	
	@GetMapping(path="/datos")
	public ResponseEntity<?> reportePagos(@RequestParam Map<String,String> params) {
		logger.info("Se recibió parámetro para carga de datos de dashboard - {}",params);
		try {
			DashboardDto datos = service.datosDashBoard(params);
			return respuestaApi(datos, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}