package pe.gob.muni.apimercado.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParametrosApiRest {
		
	//PARAMETROS CARGADOS DESDE PROPERTIES
	@Value("${config.aplicativos.config.log4j2}")
    private String rutaConfigLog4j2;
	
	@Value("${config.api.reporte.resource.url}")
	private String resource_url;

	public String getRutaConfigLog4j2() {
		return rutaConfigLog4j2;
	}

	public void setRutaConfigLog4j2(String rutaConfigLog4j2) {
		this.rutaConfigLog4j2 = rutaConfigLog4j2;
	}

	public String getResource_url() {
		return resource_url;
	}
	
}
