package ar.edu.ubp.das.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "conexion")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Conexion implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8164033553469793441L;
	
	
	private int Entidad;
	private String Tipo;
	private String Url;
	private String Accion;
	
	public int getEntidad() {
		return Entidad;
	}
	public void setEntidad(int entidad) {
		Entidad = entidad;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getAccion() {
		return Accion;
	}
	public void setAccion(String accion) {
		Accion = accion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}	
}
