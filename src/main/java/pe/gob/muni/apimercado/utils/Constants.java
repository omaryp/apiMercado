package pe.gob.muni.apimercado.utils;

public class Constants {

	// Spring Security

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT

	public static final String ISSUER_INFO = "Api REST para pagos de impuestos de los mercados de la municipalidad de 26 de octubre.";
	public static final String SUPER_SECRET_KEY = "@ITHg%JaxPw0Z#g&@7#$&KpkIcq48asYu#6XUjy#RH@^nPFyF3f%%0DcY";
	public static final long TOKEN_EXPIRATION_TIME = 86400000; //24 horas
	
	// Aplicacion
	
	public final static int TRANSACCION_OK = 0;
	public final static int DATOS_NO_VALIDOS = 1;
	public final static int ERROR_AL_PROCESAR_PETICION = 2;
	public final static int ERROR_INTERNO = 3;
	
	public final static int POR_USUARIO = 1;
	public final static int POR_CODIGO = 2;
	
	public final static int RESPONSE_LIST = 1; 
	public final static int RESPONSE_OBJECT = 2; 
	
	public final static int PUESTO_DISPONIBLE = 1; 
	public final static int PUESTO_OCUPADO = 2; 
	public final static int PUESTO_CERRADO = 3;  
	
	public final static int ACTIVO = 1; 
	public final static int DESACTIVADO = 0; 
}
