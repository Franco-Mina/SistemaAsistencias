package ar.edu.ubp.das.util;

import java.io.Serializable;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "conexiones")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Conexiones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3848377820301808614L;
	
	public Conexiones() {
		listaConexiones = new LinkedList<Conexion>();
	}
	
	
	private LinkedList<Conexion> listaConexiones;

	@XmlElement(name = "conexion")
	public LinkedList<Conexion> getListaConexiones() {
		return listaConexiones;
	}

	public void setListaConexiones(LinkedList<Conexion> listaConexiones) {
		this.listaConexiones = listaConexiones;
	}

}
