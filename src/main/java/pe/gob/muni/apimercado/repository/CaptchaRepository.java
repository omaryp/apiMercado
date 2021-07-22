package pe.gob.muni.apimercado.repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.gob.muni.apimercado.mapper.ICaptchaMapper;
import pe.gob.muni.apimercado.model.Captcha;
import pe.gob.muni.apimercado.utils.ApiException;

@Repository
public class CaptchaRepository {
	
	@Autowired
	ICaptchaMapper mapper;
	
	public void saveCaptcha(Captcha datos) throws ApiException {
		try {
			mapper.saveCaptcha(datos);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
		
	public String getCaptcha(String code) throws ApiException {
		try {
			return mapper.getCaptcha(code);
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new ApiException(e.getMessage(),e);
		}catch (SQLException e) {
			throw new ApiException(e.getMessage(),e);
		} 
	}
	
}