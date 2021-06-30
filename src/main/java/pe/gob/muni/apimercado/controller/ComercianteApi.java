package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.service.IComercianteService;
import pe.gob.muni.apimercado.utils.ApiException;

@RestController
@RequestMapping("/comerciante")
public class ComercianteApi extends BasicController<Comerciante, IComercianteService> {
	
	
	@GetMapping(path="/report/asistencia")
	public ResponseEntity<?> reporteAsistencia(@RequestParam Map<String, String> params) {
		logger.info("Se recibió parámetro para obtener reporte de asistencia de comerciante- {}",params);
		try {
			byte [] rpta = service.reporteAsistencia(params);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(rpta);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping(path="/report/deuda")
	public ResponseEntity<?> reporteDeuda(@RequestParam Map<String, String> params) {
		logger.info("Se recibió parámetro para obtener reporte de asistencia de comerciante- {}",params);
		try {
			byte [] rpta = service.reporteDeuda(params);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(rpta);
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