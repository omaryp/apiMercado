package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.dto.CaptchaDto;
import pe.gob.muni.apimercado.service.ICaptchaService;
import pe.gob.muni.apimercado.utils.ApiException;

@RestController
@RequestMapping("/captcha")
public class CaptchaApi {
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ICaptchaService service;
	
	@GetMapping
	public ResponseEntity<?> captcha() {
		logger.info("Se recibió solictud de captcha ");
		try {
			CaptchaDto rpta = service.obtenerCaptcha();
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ApiException e) {
			logger.error("Error de api petición no válida - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error general de api al obetener captcha- {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}