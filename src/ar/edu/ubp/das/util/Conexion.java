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
	private String Metodo;
	private String Url;
	private String Accion;
	
	public int getEntidad() {
		return Entidad;
	}
	public void setEntidad(int entidad) {
		Entidad = entidad;
	}
	public String getMetodo() {
		return Metodo;
	}
	public void setMetodo(String metodo) {
		Metodo = metodo;
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
}
