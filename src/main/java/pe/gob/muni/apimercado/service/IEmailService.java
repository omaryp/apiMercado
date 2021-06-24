package pe.gob.muni.apimercado.service;

import javax.activation.DataSource;

import pe.gob.muni.apimercado.utils.ApiException;

public interface IEmailService {
	
	public void enviarMensaje(String destinatario, String asunto, String mensaje) throws ApiException,Exception;
	
	public void enviarMensaje(String destinatario, String asunto, String mensaje, String rutaArchivoAdjunto)throws ApiException,Exception;
	
	public void enviarMensaje(String destinatario, String asunto, String mensaje, DataSource... archivosAdjuntos)throws ApiException,Exception;

}
