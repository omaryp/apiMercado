package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.model.dto.TicketVisita;
import pe.gob.muni.apimercado.service.ITicketService;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.dto.ProcesoTicket;

@RestController
@RequestMapping("/ticket")
public class TicketApi extends BasicController<Ticket, ITicketService> {

	@PostMapping(path="/generate")
	public ResponseEntity<?> generarTickets(@RequestBody ProcesoTicket params) {
		logger.info("Se recibió parámetro para generar tickets - {}",Util.objectToJson(params));
		try {
			service.generarTickets(params);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping(path="/visita")
	public ResponseEntity<?> marcarTicketNoHabido(@RequestBody TicketVisita ticket) {
		logger.info("Se recibió parámetro para generar tickets - {}",Util.objectToJson(ticket));
		try {
			service.marcarEstadoVisitaTicket(ticket);
			return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar tickets - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping(path="/pagTicket")
	public ResponseEntity<?> paginacionTickets(@RequestParam Map<String, String> params) {
		logger.info("Se recibió parámetro para generar tickets - {}",Util.objectToJson(params));
		try {
			PageInfo<TicketDto> rpta = service.pagingTickets(params);
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
	
	@GetMapping(path="/deuda")
	public ResponseEntity<?> deudaComerciante(@RequestParam Map<String, String> params) {
		logger.info("Se recibió parámetro para generar reporte deuda comerciante - {}",Util.objectToJson(params));
		try {
			PageInfo<TicketDto> rpta = service.pagingTickets(params);
			return respuestaApi(rpta, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}catch (ApiException e) {
			logger.error("Error de api al generar reporte deuda comerciante - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error general de api al procesar reporte deuda comerciante - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping(path="/report/deuda/consolidado")
	public ResponseEntity<?> deudaConsolidado(@RequestParam Map<String, String> params) {
		logger.info("Se recibió parámetro para generar reporte deuda consolidado - {}",Util.objectToJson(params));
		try {
			byte [] rpta = service.deudaConsolidado(params);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=deudaConsolidado.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(rpta);
		}catch (ApiException e) {
			logger.error("Error de api al procesar reporte deuda consolidado - {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error general de api al procesar reporte deuda consolidado - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
		
}