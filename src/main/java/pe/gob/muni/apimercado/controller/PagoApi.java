package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.github.pagehelper.PageInfo;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;

import pe.gob.muni.apimercado.model.Pago;
import pe.gob.muni.apimercado.model.Ticket;
import pe.gob.muni.apimercado.model.dto.PagoDto;
import pe.gob.muni.apimercado.service.IPagoService;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;

@RestController
@RequestMapping("/pago")
public class PagoApi extends BasicController<Pago, IPagoService> {
	
	@Autowired
    ServletContext servletContext;
	@Autowired
	private final TemplateEngine templateEngine = null;
		
	@PostMapping("/tickets")
	public ResponseEntity<?> pagoTickets(@RequestBody List<Ticket> entitys) {
		logger.info("Se recibió perfil - {}",Util.objectToJson(entitys));
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
	
	@GetMapping(path="/pagPago")
	public ResponseEntity<?> paginacionTickets(@RequestParam Map<String, String> params) {
		logger.info("Se recibió parámetro para paginar pagos - {}",Util.objectToJson(params));
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
	
	@GetMapping(path="/report")
	public ResponseEntity<?> reportePago(HttpServletRequest request, HttpServletResponse response) {
		try {

	        Pago pago = service.getEntity(17);

	        /* Create HTML using Thymeleaf template Engine */

	        WebContext context = new WebContext(request, response, servletContext);
	        context.setVariable("pago", pago);
	        String orderHtml = templateEngine.process("pago", context);

	        /* Setup Source and target I/O streams */

	        ByteArrayOutputStream target = new ByteArrayOutputStream();
	        ConverterProperties converterProperties = new ConverterProperties();
	        converterProperties.setBaseUri("http://localhost.mercado");
	      
	        /* Call convert method */
	        HtmlConverter.convertToPdf(orderHtml, target,converterProperties);
	       

	        /* extract output as bytes */
	        byte[] bytes = target.toByteArray();


	        /* Send the response as downloadable PDF */

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(bytes);
			//return respuestaApi(null, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		}
		catch (Exception e) {
			logger.error("Error interno de api al procesar guardar - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}