package pe.gob.muni.apimercado.utils.dto;


public class RespuestaApi<T> {
	
	private int codigo;
	private String mensaje;
	private T contenido;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public T getContenido() {
		return contenido;
	}
	public void setContenido(T contenido) {
		this.contenido = contenido;
	}
	
}
