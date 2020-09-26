package ar.edu.ubp.das.conections.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "conexion")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Conexion implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8164033553469793441L;
	
	@XmlElement(name = "nro_conexion")
	private int NroConexion;
	private String Entidad;
	private String Tipo;
	private String Url;
	private String Accion;
	@XmlElement(name="req_token")
	private boolean RequiereToken;
	@XmlElement(name="neg_token")
	private boolean NegociaToken;
	private String Usuario;
	private String Password;
	
	public String getEntidad() {
		return Entidad;
	}
	public void setEntidad(String entidad) {
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
	public int getNroConexion() {
		return NroConexion;
	}
	public void setNroConexion(int nroConexion) {
		NroConexion = nroConexion;
	}
	public boolean isReqToken() {
		return RequiereToken;
	}
	public void setReqToken(boolean reqToken) {
		RequiereToken = reqToken;
	}
	public boolean isNegociaToken() {
		return NegociaToken;
	}
	public void setNegociaToken(boolean negociaToken) {
		NegociaToken = negociaToken;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}	
}
