package pe.gob.muni.apimercado.utils;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
	
	private HttpStatus status;
	
	private static final long serialVersionUID = -5272256098423403364L;

	public ApiException(HttpStatus status,String message,Throwable ex) {
		super(message,ex);
		this.status = status;
	}
	
	public ApiException(String message,Throwable ex) {
		super(message,ex);
	}
	
	public ApiException(String message) {
		super(message);
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
