package ar.edu.ubp.das.bean;

import java.sql.Timestamp;

public class TokenBean {
	
	private int 	id;
	private String  idServicio;
	private String 	idGobierno;
	private String 	token;
	private Timestamp 	fechaCreacion;
	private Timestamp 	fechaExpiracion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(Timestamp fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	public String getIdGobierno() {
		return idGobierno;
	}
	public void setIdGobierno(String idGobierno) {
		this.idGobierno = idGobierno;
	}
}