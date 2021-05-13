package pe.gob.muni.apimercado;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import pe.gob.muni.apimercado.config.ParametrosApiRest;
import pe.gob.muni.apimercado.utils.Environment;

@SpringBootApplication
@ComponentScan({"pe.gob.muni.apimercado"})
@MapperScan("pe.gob.muni.apimercado.mapper")
@PropertySource("file:${user.home}/aplicativos/apirest/config/application.properties")
public class ApirestApplication {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ParametrosApiRest configAplicativo;

	@PostConstruct
	public void initLogger() {
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		File file = null;
		file = new File(Environment.getUserHome() + configAplicativo.getRutaConfigLog4j2());
		context.setConfigLocation(file.toURI());
		logger.info("Log4j2 cargado desde el archivo : " + file.getAbsolutePath());
	}

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}
