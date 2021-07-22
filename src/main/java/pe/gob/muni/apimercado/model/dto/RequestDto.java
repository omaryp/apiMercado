package pe.gob.muni.apimercado.model.dto;

import javax.validation.constraints.NotBlank;

public class RequestDto {
	
	@NotBlank(message="Dni no debe ser vacío.")
	private String dni;
	@NotBlank(message="key no debe ser vacío.")
	private String key;
	@NotBlank(message="Captcha no debe ser vacío.")
	private String captcha;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
