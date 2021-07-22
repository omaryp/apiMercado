package pe.gob.muni.apimercado.service;

import pe.gob.muni.apimercado.model.dto.CaptchaDto;
import pe.gob.muni.apimercado.model.dto.RequestDto;
import pe.gob.muni.apimercado.utils.ApiException;

public interface ICaptchaService{

	CaptchaDto obtenerCaptcha() throws ApiException, Exception ;

	boolean validarCaptcha(RequestDto solictud) throws ApiException, Exception ;
}
