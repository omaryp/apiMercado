package pe.gob.muni.apimercado.utils;

public class ApiException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5272256098423403364L;

	public ApiException(String message,Throwable ex) {
		super(message,ex);
	}
	
}
