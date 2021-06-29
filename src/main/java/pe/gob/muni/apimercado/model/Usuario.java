package pe.gob.muni.apimercado.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Usuario extends Persona{
	
	private int idUsuario;
	
	@NotBlank(message="Atributo usuario no debe ser vacío.")
	@NotNull(message="Atributo usuario no debe ser vacío.")
	private String usuario;
	
	@NotBlank(message="Atributo password no debe ser vacío.")
	@NotNull(message="Atributo password no debe ser nulo.")
	private String password;
	
	private String password2;
	
	private Date fechaUltAcc;
	
	@Min(value=1, message="Atributo código de perfil debe ser != 0")
	private int perfiles_codigo;
	
	private String nombre_perfil;
	
	private int personas_id;
	
	private boolean activo;
	
	private int mercados_id;
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaUltAcc() {
		return fechaUltAcc;
	}

	public void setFechaUltAcc(Date fechaUltAcc) {
		this.fechaUltAcc = fechaUltAcc;
	}

	public int getPersonas_id() {
		return personas_id;
	}

	public void setPersonas_id(int personas_id) {
		this.personas_id = personas_id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getPerfiles_codigo() {
		return perfiles_codigo;
	}

	public void setPerfiles_codigo(int perfiles_codigo) {
		this.perfiles_codigo = perfiles_codigo;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public int getMercados_id() {
		return mercados_id;
	}

	public void setMercados_id(int mercados_id) {
		this.mercados_id = mercados_id;
	}

	public String getNombre_perfil() {
		return nombre_perfil;
	}

	public void setNombre_perfil(String nombre_perfil) {
		this.nombre_perfil = nombre_perfil;
	}
	
}
