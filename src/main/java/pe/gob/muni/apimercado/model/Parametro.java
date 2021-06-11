package pe.gob.muni.apimercado.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Parametro extends BasicEntity {
	
	@Min(value=1, message="Atributo codigo debe ser != 0")
	private int codigo;
	@Min(value=1, message="Atributo subcodigo debe ser != 0")
	private int subcodigo;
	@NotBlank(message="Atributo tabla no debe ser vacío.")
	private String tabla;
	private int valor_entero;
	private double valor_decimal;
	private String valor_cadena1;
	private String valor_cadena2;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getSubcodigo() {
		return subcodigo;
	}
	public void setSubcodigo(int subcodigo) {
		this.subcodigo = subcodigo;
	}
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public int getValor_entero() {
		return valor_entero;
	}
	public void setValor_entero(int valor_entero) {
		this.valor_entero = valor_entero;
	}
	public double getValor_decimal() {
		return valor_decimal;
	}
	public void setValor_decimal(double valor_decimal) {
		this.valor_decimal = valor_decimal;
	}
	public String getValor_cadena1() {
		return valor_cadena1;
	}
	public void setValor_cadena1(String valor_cadena1) {
		this.valor_cadena1 = valor_cadena1;
	}
	public String getValor_cadena2() {
		return valor_cadena2;
	}
	public void setValor_cadena2(String valor_cadena2) {
		this.valor_cadena2 = valor_cadena2;
	}

}
