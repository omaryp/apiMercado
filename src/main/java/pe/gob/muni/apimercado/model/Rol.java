package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;

public class Rol extends BasicEntity implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message="Atributo nombre Rol no debe ser vacío.")
	@NotNull(message="Atributo nombre Rol no debe ser vacío.")
	private String nombre;
	private String descripcion;
	
	@Min(value=1, message="Atributo módulo debe ser != 0")
	@Digits(fraction = 0, integer = 11, message="Atributo módulo debe contener solo números")
	private int modulos_codigo;
	
	@NotBlank(message="Atributo icono no debe ser vacío.")
	@NotNull(message="Atributo icono no debe ser vacío.")
	private String icono;
	
	private boolean menu;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return nombre;
	}

	public int getModulos_codigo() {
		return modulos_codigo;
	}

	public void setModulos_codigo(int modulos_codigo) {
		this.modulos_codigo = modulos_codigo;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	
}
