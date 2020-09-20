package ar.edu.ubp.das.conections;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ar.edu.ubp.das.bean.TokenBean;
import ar.edu.ubp.das.bean.ws.TokenRequestBean;
import ar.edu.ubp.das.conections.util.Conexion;
import ar.edu.ubp.das.conections.util.Conexiones;
import ar.edu.ubp.das.token.TokenManager;

public class ConnectionManager {
	
	private Conexiones conexiones = null;
	
	public String callApi(int idConexion, Object parameters) {
		Conexion conexion = this.getConnectionById(idConexion);
		
		if(conexion == null || conexion.getTipo() == "") return null;
		
		IConnections tipoConexion = this.getConnectionByType(conexion.getTipo());
		
		if(tipoConexion == null) return null;
		
		if(conexion.isReqToken()) {
			//TODO BUSCAR TOKEN
		}
		
		String resultado = tipoConexion.CallApi(conexion.getUrl(), conexion.getAccion(), parameters);
		
		return resultado;
	}
	
	public String callApiToken(String servicio) {
		TokenManager tokenManager = TokenManager.getInstance();
		
		TokenBean token = tokenManager.buscarToken(servicio);
		
		if(token == null) {
			Optional<Conexion> conexion = 
					this.conexiones.getListaConexiones().stream().filter(x -> x.getEntidad() == servicio && x.isNegociaToken()).findFirst();
			
			if(!conexion.isPresent() || conexion.get().getTipo() == "") {
				//TODO ERROR
			} 
			else {
				IConnections tipoConexion = this.getConnectionByType(conexion.get().getTipo());
				
				if(tipoConexion == null) { 
					//TODO ERROR;
					}
				
				TokenRequestBean tokenRequest = new TokenRequestBean();
				
				tokenRequest.setUsuario(conexion.get().getUsuario());
				tokenRequest.setPassword(conexion.get().getPassword());
				
				String resultado = tipoConexion.CallApi(conexion.get().getUrl(), conexion.get().getAccion(), tokenRequest);
			}
		}
		return null;
	}
	

	
	
	public Conexion getConnectionById(int idConexion) {
		this.loadConnections();
		
		Optional<Conexion> conexion =  this.conexiones.getListaConexiones().stream().filter(c -> c.getNroConexion() == idConexion).findFirst();

		return conexion.isPresent() ? conexion.get() : null;
	}

	public IConnections getConnectionByType(String tipo) {
		if(tipo == null || tipo.trim().isEmpty()) return null;
		
		Class<?> clase;
		try {
			clase = Class.forName("ar.edu.ubp.das.conections." + tipo + "Connection");

			Constructor<?> constructor = clase.getConstructor();
			
			Object instancia = constructor.newInstance();	
			
			return (IConnections) instancia;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException 
				| InstantiationException | IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	
	public void loadConnections() {
		//TODO: poner path en web.xml
		File file = new File("C:\\Java\\Final DAS\\SistemaAsistencias\\WebContent\\WEB-INF\\conexiones.xml");
		JAXBContext jaxbContext;
		
		try {
			jaxbContext = JAXBContext.newInstance(Conexiones.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			this.conexiones = (Conexiones) jaxbUnmarshaller.unmarshal(file);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
