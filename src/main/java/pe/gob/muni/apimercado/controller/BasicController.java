package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.DATOS_NO_VALIDOS;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import pe.gob.muni.apimercado.model.BasicEntity;
import pe.gob.muni.apimercado.model.RptaDataModel;
import pe.gob.muni.apimercado.service.IBasicService;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.ValidatorException;

public class BasicController<T extends BasicEntity,E extends IBasicService<T>>{
	
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	protected E service;
	
	@PostMapping
	public ResponseEntity<?> saveEntity(@RequestBody T entity) {
		logger.info("Se recibió perfil - {}",Util.objectToJson(entity));
		try {
			service.saveEntity(entity);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ValidatorException e) {
			logger.info("Errores de validacion en la solicitud de  guardar perfil - {}",Util.objectToJson(entity));
			return respuestaApi(e.getErrores(), e.getMessage(), DATOS_NO_VALIDOS, HttpStatus.BAD_REQUEST);
		} catch (ApiException e) {
			logger.info("Error al procesar peticion - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.info("Error interno de la aplicación - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping
	public ResponseEntity<?> searchEntity(@RequestParam Map<String, String> params) {
		logger.info("Obteniendo entidades por filtro");
		try {
			Object rpta = service.searchEntity(params);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.info("Error al procesar peticion - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.info("Error interno de la aplicación - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/pag")
	public ResponseEntity<?> pagingEntitys(@RequestParam Map<String, String> parmas) {
		logger.info("Obteniendo roles por filtro");
		try {
			int fin = Integer.parseInt(parmas.get("fin"));
			int inicio = Integer.parseInt(parmas.get("inicio"));
			int tipo = Integer.parseInt(parmas.get("tipo"));
			String valor = parmas.get("valor");
			RptaDataModel<T> rpta = service.pagingEntitys(valor, tipo, inicio, fin);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.info("Error al procesar peticion - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.info("Error interno de la aplicación - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateEntity(@RequestBody T entity) {
		logger.info("Se recibió perfil - {}",Util.objectToJson(entity));
		try {
			service.updateEntity(entity);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ValidatorException e) {
			logger.info("Errores de validacion en la solicitud al actualizar perfil - {}",Util.objectToJson(entity));
			return respuestaApi(e.getErrores(), e.getMessage(), DATOS_NO_VALIDOS, HttpStatus.BAD_REQUEST);
		} catch (ApiException e) {
			logger.info("Error api al actualizar perfil peticion - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.info("Error interno de la aplicación actualizar perfil - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteEntity(@RequestBody int codigo) {
		logger.info("Se recibio código de perfil - {}",codigo);
		try {
			service.deleteEntity(codigo);
			logger.info("Se procesó correctamente solicitud deshabilitar perfil - {}",codigo);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ApiException e) {
			logger.info("Error api al procesar solicitud deshabilitar perfil - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.info("Error interno de la aplicación solicitud deshabilitar perfil - {}",e.getMessage());
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
