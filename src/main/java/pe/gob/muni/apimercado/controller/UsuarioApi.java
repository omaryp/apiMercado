package pe.gob.muni.apimercado.controller;

import static pe.gob.muni.apimercado.utils.Constants.ERROR_AL_PROCESAR_PETICION;
import static pe.gob.muni.apimercado.utils.Constants.ERROR_INTERNO;
import static pe.gob.muni.apimercado.utils.Constants.TRANSACCION_OK;
import static pe.gob.muni.apimercado.utils.Util.respuestaApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.muni.apimercado.model.Usuario;
import pe.gob.muni.apimercado.model.dto.UsuarioSessionDto;
import pe.gob.muni.apimercado.service.IUsuarioService;
import pe.gob.muni.apimercado.utils.ApiException;



@RestController
@RequestMapping("/usuario")
public class UsuarioApi extends BasicController<Usuario, IUsuarioService> {	

	@GetMapping("/session")
	public ResponseEntity<?> getUsuarioSession() {
		logger.info("Se inicia carga datos de session");
		try {
			UsuarioSessionDto dto = service.getDatosSession();
			return respuestaApi(dto, "Transacción OK.", TRANSACCION_OK, HttpStatus.OK);
		} catch (ApiException e) {
			logger.error("Error de api al procesar obtención de usuario de session- {} - {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_AL_PROCESAR_PETICION, HttpStatus.ACCEPTED);
		} 
		catch (Exception e) {
			logger.error("Error interno de api al obtención de usuario de session - {}- {}",e.getMessage(),e);
			return respuestaApi(null, e.getMessage(), ERROR_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}