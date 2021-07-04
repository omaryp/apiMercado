package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;
import static pe.gob.muni.apimercado.utils.Util.objectToJson;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.dto.PagoDto;
import pe.gob.muni.apimercado.service.IPagoService;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.dto.EnvioDto;

@RestController
@RequestMapping("/pago")
public class PagoApi extends BasicController<Pago, IPagoService> {
		
	@PostMapping("/tickets")
	public ResponseEntity<?> pagoTickets(@RequestBody List<Ticket> entitys) {
		logger.info("Se recibió perfil - {}",objectToJson(entitys));
		try {
			service.pagoTickets(entitys);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ApiException e) {
			logger.error("Error de api al procesar peticion guardar - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping(path="/{codigo}")
	public ResponseEntity<?> getEntityPagoDto(@PathVariable int codigo) {
		logger.info("Se recibió parametro para obtener datos de pago - {}",codigo);
		try {
			PagoDto rpta = service.getEntityPagoDto(codigo);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping(path="/ticket/{codigo}")
	public ResponseEntity<?> getEntityPagoDtoTicket(@PathVariable int codigo) {
		logger.info("Se recibió parametro para obtener datos de pago - {}",codigo);
		try {
			PagoDto rpta = service.getEntityPagoDtoTicket(codigo);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping(path="/enviar")
	public ResponseEntity<?> enviarMensaje(@RequestBody EnvioDto entity) {
		logger.info("Se recibió parametro para enviar mensaje de pago - {}",objectToJson(entity));
		try {
			service.enviarCorreo(entity);
			return respuestaApi("Mensaje se envió correctamente", "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping(path="/pagPago")
	public ResponseEntity<?> paginacionTickets(@RequestParam Map<String, String> params) {
		logger.info("Se recibió parámetro para paginar pagos - {}",objectToJson(params));
		try {
			PageInfo<PagoDto> rpta = service.pagingTickets(params);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping(path="/report/ticket")
	public ResponseEntity<?> reporteTicketPago(@RequestParam Map<String,String> params) {
		logger.info("Se recibió parámetro para obtener reporte pago - {}",objectToJson(params));
		try {
			byte [] rpta = service.reporteTicketPago(params);
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
	
	@GetMapping(path="/report")
	public ResponseEntity<?> reportePagos(@RequestParam Map<String,String> params) {
		logger.info("Se recibió parámetro para obtener reporte pagos - {}",params);
		try {
			byte [] rpta = service.reportePagos(params);
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