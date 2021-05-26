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
	
	private Date fechaUltAcc;
	
	@Min(value=1, message="Atributo código de perfil debe ser != 0")
	private int perfiles_codigo;
	
	private int persona_id;
	
	private boolean activo;
	

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

	public int getCodigoPerfil() {
		return perfiles_codigo;
	}

	public int getPersona_id() {
		return persona_id;
	}

	public void setPersona_id(int persona_id) {
		this.persona_id = persona_id;
	}

	public void setCodigoPerfil(int codigoPerfil) {
		this.perfiles_codigo = codigoPerfil;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
