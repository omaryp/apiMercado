package pe.demo.apirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("Iniciando aplicación");
		return application.sources(ApirestApplication.class);
	}

}
