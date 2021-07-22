package pe.gob.muni.apimercado.service;

import static pe.gob.muni.apimercado.utils.Util.objectToJson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import pe.gob.muni.apimercado.model.Captcha;
import pe.gob.muni.apimercado.model.dto.CaptchaDto;
import pe.gob.muni.apimercado.model.dto.RequestDto;
import pe.gob.muni.apimercado.repository.CaptchaRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Validador;
import pe.gob.muni.apimercado.utils.ValidatorException;

@Service
public class CaptchaService implements ICaptchaService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CaptchaRepository repo;
	@Autowired
	private Validador<RequestDto> validador;
	
	@Override
	public CaptchaDto obtenerCaptcha() throws ApiException, Exception {
		logger.info("obteniendo captcha para validación");
		try {
			//dto a enviar como respuesta a la petición
			CaptchaDto dto = new CaptchaDto();
			//para generar captcha uso interno
			Captcha datos = new Captcha();
			
			ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(111, 66);
			String code = captcha.getCode();
			
			datos.setCaptcha(code);
			repo.saveCaptcha(datos);
			
			dto.setImage(captcha.getImageBase64());
			dto.setKey(datos.getKey());
			
			return dto;
		}catch (ApiException e) {
			logger.error("Error api obteniendo captcha{} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo captcha {} - {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public boolean validarCaptcha(RequestDto solicitud) throws ApiException, Exception {
		logger.info("Se entro a validar captcha {}",objectToJson(solicitud));
		try {
			validador.validarModelo(solicitud);
			if (validador.isHayErrores())
				throw new ValidatorException("Error de validación", validador.getErrores());
			
			String captcha = repo.getCaptcha(solicitud.getKey());
			
			if(!captcha.equals(solicitud.getCaptcha()))
				throw new ApiException("Petición no válida, ingrese correctamente el captcha");
			
			return true;
		}catch (ValidatorException e) {
			logger.error("Errores de validación {} - {} - {}",e.getErrores(), e.getMessage(), e);
			throw e;
		}catch (ApiException e) {
			logger.error("Error api obteniendo captcha{} - {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Error general obteniendo captcha {} - {}", e.getMessage(), e);
			throw e;
		}
	}
	
	
}
