package pe.demo.apirest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ParametrosApiRest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//PARAMETROS CARGADOS DESDE PROPERTIES
	@Value("${config.aplicativos.config.log4j2}")
    private String rutaConfigLog4j2;
	@Value("${config.aplicativos.rest.authBasic}")
	private Boolean flagAutenticacionBasica;
	@Value("${config.aplicativos.rest.usuario}")
	private String usuarioAutenticacionBasica;
	@Value("${config.aplicativos.rest.password}")
	private String claveAutenticacionBasica;
	@Value("${config.aplicativos.rest.protocolo}")
	private String protocoloTransferencia;
	@Value("${config.aplicativos.rest.ip}")
	private String ipAutorizador;
	@Value("${config.aplicativos.rest.puerto}")
	private Integer puertoAutorizador;
	@Value("${config.aplicativos.rest.id}")
	private String idAutorizador;
	@Value("${config.aplicativos.rest.mediaType}")
	private String mediaType;
	@Value("${config.aplicativos.rest.setCaracteres}")
	private String setCaracteres;
	@Value("${config.aplicativos.rest.zonaHoraria}")
	private String zonaHoraria;
	@Value("${config.aplicativos.rest.formatoFecha}")
	private String formatoFecha;
	@Value("${config.aplicativos.rest.timeOutCon}")
	private Long timeoutConexion;
	@Value("${config.aplicativos.rest.timeOutLec}")
	private Long timeoutLectura; 
	
	public String getRutaConfigLog4j2() {
		logger.info("Se retorna parámetro {}",rutaConfigLog4j2);
		return rutaConfigLog4j2;
	}

	public Boolean getFlagAutenticacionBasica() {
		return flagAutenticacionBasica;
	}

	public void setFlagAutenticacionBasica(Boolean flagAutenticacionBasica) {
		this.flagAutenticacionBasica = flagAutenticacionBasica;
	}

	public String getUsuarioAutenticacionBasica() {
		return usuarioAutenticacionBasica;
	}

	public void setUsuarioAutenticacionBasica(String usuarioAutenticacionBasica) {
		this.usuarioAutenticacionBasica = usuarioAutenticacionBasica;
	}

	public String getClaveAutenticacionBasica() {
		return claveAutenticacionBasica;
	}

	public void setClaveAutenticacionBasica(String claveAutenticacionBasica) {
		this.claveAutenticacionBasica = claveAutenticacionBasica;
	}

	public String getProtocoloTransferencia() {
		return protocoloTransferencia;
	}

	public void setProtocoloTransferencia(String protocoloTransferencia) {
		this.protocoloTransferencia = protocoloTransferencia;
	}

	public String getIpAutorizador() {
		return ipAutorizador;
	}

	public void setIpAutorizador(String ipAutorizador) {
		this.ipAutorizador = ipAutorizador;
	}

	public Integer getPuertoAutorizador() {
		return puertoAutorizador;
	}

	public void setPuertoAutorizador(Integer puertoAutorizador) {
		this.puertoAutorizador = puertoAutorizador;
	}

	public String getIdAutorizador() {
		return idAutorizador;
	}

	public void setIdAutorizador(String idAutorizador) {
		this.idAutorizador = idAutorizador;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getSetCaracteres() {
		return setCaracteres;
	}

	public void setSetCaracteres(String setCaracteres) {
		this.setCaracteres = setCaracteres;
	}

	public String getZonaHoraria() {
		return zonaHoraria;
	}

	public void setZonaHoraria(String zonaHoraria) {
		this.zonaHoraria = zonaHoraria;
	}

	public String getFormatoFecha() {
		return formatoFecha;
	}

	public void setFormatoFecha(String formatoFecha) {
		this.formatoFecha = formatoFecha;
	}

	public Long getTimeoutConexion() {
		return timeoutConexion;
	}

	public void setTimeoutConexion(Long timeoutConexion) {
		this.timeoutConexion = timeoutConexion;
	}

	public Long getTimeoutLectura() {
		return timeoutLectura;
	}

	public void setTimeoutLectura(Long timeoutLectura) {
		this.timeoutLectura = timeoutLectura;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setRutaConfigLog4j2(String rutaConfigLog4j2) {
		this.rutaConfigLog4j2 = rutaConfigLog4j2;
	}
	
	
}
