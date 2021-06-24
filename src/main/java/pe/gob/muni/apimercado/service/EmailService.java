package pe.gob.muni.apimercado.service;

import java.io.File;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pe.gob.muni.apimercado.utils.ApiException;

@Service
public class EmailService implements IEmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public JavaMailSender emailSender;

	@Value("${config.aplicativos.mail.from}")
	private String configAplicativosMailFrom;

	@Override
	public void enviarMensaje(String destinatario, String asunto, String mensaje) throws ApiException, Exception {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(destinatario);
			message.setSubject(asunto);
			message.setText(mensaje);
			emailSender.send(message);
		} catch (Exception e) {
			logger.error("No se puedo enviar correo electrónico {} {} ", e.getMessage(), e);
			throw new ApiException("No se puedo enviar correo electrónico", null);
		}
	}

	@Override
	public void enviarMensaje(String destinatario, String asunto, String mensaje, String rutaArchivoAdjunto,String nombreArchivo)
			throws ApiException, Exception {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(destinatario);
			helper.setSubject(asunto);
			helper.setText(mensaje);

			FileSystemResource file = new FileSystemResource(new File(rutaArchivoAdjunto));
			helper.addAttachment(nombreArchivo, file);

			emailSender.send(message);
		} catch (MessagingException e) {
			logger.error("No se puedo enviar correo electrónico {} {} ", e.getMessage(), e);
			throw new ApiException("No se puedo enviar correo electrónico", null);
		} catch (Exception e) {
			logger.error("Error general al enviar correo electrónico {} {} ", e.getMessage(), e);
			throw new ApiException("Error general al enviar correo electrónico", null);
		}
	}

	@Override
	public void enviarMensaje(String destinatario, String asunto, String mensaje, DataSource... archivosAdjuntos)
			throws ApiException, Exception {

		// chequeo de parámetros
		Assert.hasLength(destinatario, "email 'to' needed");
		Assert.hasLength(asunto, "email 'subject' needed");
		Assert.hasLength(mensaje, "email 'text' needed");

		// plantilla para el envío de email
		final MimeMessage message = emailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message, true);

			// settings de los parámetros del envío
			helper.setTo(destinatario);
			helper.setSubject(asunto);
			helper.setFrom(configAplicativosMailFrom);
			helper.setText(mensaje, true);

			// adjuntando los ficheros
			if (archivosAdjuntos != null) {
				for (int i = 0; i < archivosAdjuntos.length; i++) {
					helper.addAttachment(archivosAdjuntos[i].getName(), archivosAdjuntos[i]);
				}
			}

			this.emailSender.send(message);

		} catch (MessagingException e) {
			logger.error("No se puedo enviar correo electrónico {} {} ", e.getMessage(), e);
			throw new ApiException("No se puedo enviar correo electrónico", null);
		} catch (Exception e) {
			logger.error("Error general al enviar correo electrónico {} {} ", e.getMessage(), e);
			throw new ApiException("Error general al enviar correo electrónico", null);
		}

	}

}
