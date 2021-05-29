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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.BasicEntity;
import pe.gob.muni.apimercado.service.IBasicService;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.ValidatorException;

public class BasicController<T extends BasicEntity,E extends IBasicService<T>>{
	
	protected final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	protected E service;
	
	@PostMapping
	public ResponseEntity<?> saveEntity(@RequestBody T entity) {
		logger.info("Se recibió entidad - {}",Util.objectToJson(entity));
		try {
			service.saveEntity(entity);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ValidatorException e) {
			logger.error("Errores de validacion en la solicitud de  guardar  - {}",Util.objectToJson(entity));
			return respuestaApi(e.getErrores(), e.getMessage(), DATOS_NO_VALIDOS, HttpStatus.BAD_REQUEST);
		} catch (ApiException e) {
			logger.error("Error de api al procesar peticion guardar - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping
	public ResponseEntity<?> searchEntity(@RequestParam Map<String, String> params) {
		logger.info("Obteniendo entidades por filtro");
		try {
			Object rpta = service.searchEntity(params);
			if (null == rpta) return respuestaApi(rpta, "Recurso no encontrado", ERROR_AL_PROCESAR_PETICION, HttpStatus.NOT_FOUND);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error al procesar peticion get - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de la aplicación get - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/pag")
	public ResponseEntity<?> pagingEntitys(@RequestParam Map<String, String> params) {
		logger.info("Obteniendo filtro parmas - {}",params);
		try {
			PageInfo<T> rpta = service.pagingEntitys(params);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error al procesar peticion paginacion - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de la aplicación paginacion - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateEntity(@RequestBody T entity) {
		logger.info("Se recibió entidad - {}",Util.objectToJson(entity));
		try {
			service.updateEntity(entity);
			logger.info("Se procesó correctamente solicitud actualizar");
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ValidatorException e) {
			logger.error("Errores de validacion en la solicitud al actualizar  - {}",Util.objectToJson(entity));
			return respuestaApi(e.getErrores(), e.getMessage(), DATOS_NO_VALIDOS, HttpStatus.BAD_REQUEST);
		} catch (ApiException e) {
			logger.error("Error api peticion actualizar - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de la aplicación actualizar  - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/{codigo}")
	public ResponseEntity<?> deleteEntity(@PathVariable int codigo) {
		logger.info("Se recibio código  - {}",codigo);
		try {
			service.deleteEntity(codigo);
			logger.info("Se procesó correctamente solicitud deshabilitar  - {}",codigo);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ApiException e) {
			logger.error("Error api al procesar solicitud deshabilitar  - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de la aplicación solicitud deshabilitar  - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
