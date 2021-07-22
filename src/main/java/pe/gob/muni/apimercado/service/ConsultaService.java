package pe.gob.muni.apimercado.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.muni.apimercado.model.Comerciante;
import pe.gob.muni.apimercado.model.dto.ConsultaDto;
import pe.gob.muni.apimercado.model.dto.PagoDto;
import pe.gob.muni.apimercado.model.dto.RequestDto;
import pe.gob.muni.apimercado.model.dto.TicketDto;
import pe.gob.muni.apimercado.repository.ComercianteRepository;
import pe.gob.muni.apimercado.repository.PagoRepository;
import pe.gob.muni.apimercado.repository.TicketRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.ValidatorException;
import pe.gob.muni.apimercado.utils.dto.PageTablePago;
import pe.gob.muni.apimercado.utils.dto.PageTableTicket;

import static pe.gob.muni.apimercado.utils.Util.objectToJson;

@Service
public class ConsultaService implements IConsultaService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private PagoRepository pagRepository;
	@Autowired
	private TicketRepository ticRepository;
	@Autowired
	private ComercianteRepository comRepository;
	@Autowired
	private ICaptchaService captcha;
	
	@Override
	public ConsultaDto consultarDatosComerciante(RequestDto params) throws ApiException,ValidatorException, Exception {
		try {
			logger.info("Se inicia carga de datos de comerciante", objectToJson(params));
			
			captcha.validarCaptcha(params);
			
			String dni = params.getDni();
			if(dni.equals("")) throw new ValidatorException("Dni no debe ser vacío.");
			
			logger.info("Obteniendo pagos de comerciante con ", objectToJson(params));
			PageTablePago parPago = new PageTablePago();
			parPago.setDni(dni);
			List<PagoDto> pagos = pagRepository.pagingPagos(parPago);
			
			logger.info("Obteniendo deudas de comerciante con ", objectToJson(params));
			PageTableTicket parTick = new PageTableTicket();
			parTick.setDni(dni);;
			List<TicketDto> deudas = ticRepository.pagingTickets(parTick);
			
			logger.info("Obteniendo datos del comerciante con ", objectToJson(params));
			Comerciante comerciante = comRepository.getComercianteDni(dni);
			
			ConsultaDto datos = new ConsultaDto();
			datos.setDeudas(deudas);
			datos.setPagos(pagos);
			datos.setApellidos(comerciante.getApellidos());
			datos.setNombres(comerciante.getNombres());
			
			logger.info("Se terminó de cargar datos del comerciante con ", objectToJson(params));
			return datos;
		} catch (ValidatorException e) {
			logger.error("Error de validación obteniendo datos de comerciante {} - {}", e.getMessage(), e);
			throw e;
		}catch (ApiException e) {
			logger.error("Error api obteniendo datos de comerciante {} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo  datos de comerciante {} - {}", e.getMessage(), e);
			throw e;
		}
		
	}
	
}
