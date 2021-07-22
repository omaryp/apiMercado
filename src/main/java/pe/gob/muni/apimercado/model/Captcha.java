package pe.gob.muni.apimercado.model;

public class Captcha {
	
	private String request_code;
	private String captcha;
	
	public String getRequest_code() {
		return request_code;
	}
	public void setRequest_code(String request_code) {
		this.request_code = request_code;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
		
}
