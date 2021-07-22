package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.DATOS_NO_VALIDOS;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.dto.ConsultaDto;
import pe.gob.muni.apimercado.model.dto.RequestDto;
import pe.gob.muni.apimercado.service.IConsultaService;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.ValidatorException;

@RestController
@RequestMapping("/consulta")
public class ConsultaApi {
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private IConsultaService service;
	
	@PostMapping(path="/datos")
	public ResponseEntity<?> deudaComerciante(@RequestBody RequestDto params) {
		logger.info("Se recibió parámetro para consultar datos de comerciante - {}",Util.objectToJson(params));
		try {
			ConsultaDto rpta = service.consultarDatosComerciante(params);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ValidatorException e) {
			logger.error("Errores de validacion al consultar datos de comerciante - {}",Util.objectToJson(params));
			return respuestaApi(e.getErrores(), e.getMessage(), DATOS_NO_VALIDOS, HttpStatus.BAD_REQUEST);
		} catch (ApiException e) {
			logger.error("Error de api al consultar datos de comerciante - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.UNAUTHORIZED);
		} 
		catch (Exception e) {
			logger.error("Error general de api al consultar datos de comerciante - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}