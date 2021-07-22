package pe.gob.muni.apimercado.mapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.ibatis.annotations.Mapper;

import pe.gob.muni.apimercado.model.Captcha;

@Mapper
public interface ICaptchaMapper {
	
	void saveCaptcha(Captcha datos) throws SQLException,SQLIntegrityConstraintViolationException;
	
	String getCaptcha(String code) throws SQLException,SQLIntegrityConstraintViolationException;

}
