package pe.gob.muni.apimercado.model;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Usuario {
	
	private int idUsuario;
	
	@NotBlank(message="Atributo usuario no debe ser vacío.")
	@NotNull(message="Atributo usuario no debe ser vacío.")
	private String usuario;
	
	@NotBlank(message="Atributo password no debe ser vacío.")
	@NotNull(message="Atributo password no debe ser nulo.")
	private String password;
	
	@NotBlank(message="Atributo nombres no debe ser vacío.")
	@NotNull(message="Atributo nombres no debe ser vacío.")
	private String nombres;
	
	@NotBlank(message="Atributo apellidos no debe ser vacío.")
	@NotNull(message="Atributo apellidos no debe ser nulo.")
	private String apellidos;
	
	@Min(value=1, message="Atributo tipo documento debe ser != 0")
	@Digits(fraction = 0, integer = 11, message="Atributo tipo documento debe contener solo números")
	private int tipoDoc;
	
	@NotBlank(message="Atributo número de documento no debe ser vacío.")
	@NotNull(message="Atributo número de documento no debe ser nulo.")
	@Digits(fraction = 0, integer = 11, message="Atributo número de documento debe contener solo números")
	@Size(min = 8, max = 11,message="Atributo número de documento no es válido")
	private String nroDoc;
	
	@Digits(fraction = 0, integer = 11, message="Atributo teléfono debe contener solo números")
	@Min(value=1, message="Atributo telefono debe ser != 0")
	private int telefono;
	
	@NotBlank(message="Atributo direccion no debe ser vacío.")
	@NotNull(message="Atributo direccion no debe ser nulo.")
	private String direccion;
	
	@NotBlank(message="Atributo usuario no debe ser vacío.")
	@NotNull(message="Atributo usuario no debe ser nulo.")
	@Email(message="Atributo correo debe ser válido")
	private String correo;
	
	private Date fechaNac;
	private Date fechaUltAcc;
	
	@Min(value=1, message="Atributo código de perfil debe ser != 0")
	@Digits(fraction = 0, integer = 11, message="Atributo código de perfil debe contener solo números")
	private int codigoPerfil;
	
	private boolean activo;
	
	@NotNull(message="Atributo usuario no debe ser vacío.")
	private Date fechaReg;
	
	private Date fechaMod;
	
	@NotBlank(message="Atributo usuario de registro no debe ser vacío.")
	@NotNull(message="Atributo usuario de registro no debe ser nulo.")
	private String usuarioReg;
	
	private String usuarioMod;
	
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
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(int tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Date getFechaUltAcc() {
		return fechaUltAcc;
	}
	public void setFechaUltAcc(Date fechaUltAcc) {
		this.fechaUltAcc = fechaUltAcc;
	}
	public Date getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}
	public Date getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}
	public String getUsuarioReg() {
		return usuarioReg;
	}
	public void setUsuarioReg(String usuarioReg) {
		this.usuarioReg = usuarioReg;
	}
	public String getUsuarioMod() {
		return usuarioMod;
	}
	public void setUsuarioMod(String usuarioMod) {
		this.usuarioMod = usuarioMod;
	}
	
	public int getCodigoPerfil() {
		return codigoPerfil;
	}
	public void setCodigoPerfil(int codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
