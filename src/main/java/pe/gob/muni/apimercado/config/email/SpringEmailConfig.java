package pe.gob.muni.apimercado.config.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class SpringEmailConfig {
	
	@Value("${config.aplicativos.mail.password}")
	private String configAplicativosMailPassword;	
	
	@Value("${config.aplicativos.mail.username}")
	private String configAplicativosMailUsername;	
	
	@Value("${config.aplicativos.mail.host}")
	private String configAplicativosMailHost;	
	
	@Value("${config.aplicativos.mail.port}")
	private Integer configAplicativosMailPort;	
	
	@Value("${config.aplicativos.mail.properties.mail.smtp.socketFactory.port}")
	private String configAplicativosMailPropertiesMailSmtpSocketFactoryPort;	
	
	@Value("${config.aplicativos.mail.properties.mail.smtp.socketFactory.class}")
	private String configAplicativosMailPropertiesMailSmtpSocketFactoryClass;	
	
	@Value("${config.aplicativos.mail.properties.mail.smtp.auth}")
	private String configAplicativosMailPropertiesMailSmtpAuth;	
	
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(configAplicativosMailHost);
	    mailSender.setPort(configAplicativosMailPort);	     
	    mailSender.setUsername(configAplicativosMailUsername);
	    mailSender.setPassword(configAplicativosMailPassword);
	     
	    Properties props = mailSender.getJavaMailProperties();	    
	    props.put("mail.smtp.socketFactory.port", configAplicativosMailPropertiesMailSmtpSocketFactoryPort);
        props.put("mail.smtp.socketFactory.class",configAplicativosMailPropertiesMailSmtpSocketFactoryClass);
        props.put("mail.smtp.auth", configAplicativosMailPropertiesMailSmtpAuth);
	     
	    return mailSender;
	}	

}
