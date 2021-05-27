package pe.gob.muni.apimercado.model.dto;

import java.util.List;

import pe.gob.muni.apimercado.model.Modulo;
import pe.gob.muni.apimercado.model.Rol;

public class ModuloDto {

	private Modulo modulo;
	private List<Rol> authorities;
	
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public List<Rol> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Rol> authorities) {
		this.authorities = authorities;
	}

	
}
