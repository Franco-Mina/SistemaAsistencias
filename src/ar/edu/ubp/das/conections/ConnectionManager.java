package ar.edu.ubp.das.conections;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;

import ar.edu.ubp.das.bean.TokenBean;
import ar.edu.ubp.das.bean.ws.TokenRequestBean;
import ar.edu.ubp.das.conections.util.Conexion;
import ar.edu.ubp.das.conections.util.Conexiones;
import ar.edu.ubp.das.token.TokenManager;
import ar.edu.ubp.das.token.bean.TokenResponseBean;

public class ConnectionManager {
	
	private Conexiones conexiones = null;
	
	public String callApi(int idConexion, Object parameters) {
		Conexion conexion = this.getConnectionById(idConexion);
		
		String token = "";
		
		if(conexion == null || conexion.getTipo() == "") return null;
		
		IConnections tipoConexion = this.getConnectionByType(conexion.getTipo());
		
		if(tipoConexion == null) return null;
		
		if(conexion.isReqToken()) {
			try {
				token = this.callApiToken(conexion.getEntidad());
				
				Method metodo = parameters.getClass().getMethod("setToken", String.class);
				
				if(metodo != null) {
					metodo.invoke(parameters, token);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String resultado = tipoConexion.CallApi(conexion.getUrl(), conexion.getAccion(), parameters);
		
		return resultado;
	}
	
	public String callApiToken(String servicio) throws Exception {
		TokenManager tokenManager = TokenManager.getInstance();
		
		TokenBean token = tokenManager.buscarToken(servicio);
		
		if(token != null) {
			return token.getToken();
		}else {
			Optional<Conexion> conexion = 
					this.conexiones.getListaConexiones().stream().filter(x -> x.getEntidad().compareTo(servicio) == 0 && x.isNegociaToken()).findFirst();

			if(!conexion.isPresent() || conexion.get().getTipo() == "") {
				//TODO ERROR
				return null;
			} 
			else {
				IConnections tipoConexion = this.getConnectionByType(conexion.get().getTipo());
				
				if(tipoConexion == null) { 
					//TODO ERROR;
					}
								
				TokenRequestBean tokenRequest = new TokenRequestBean();
				
				tokenRequest.setPassword(conexion.get().getPassword());
				tokenRequest.setUsuario(conexion.get().getUsuario());
				
				String resultado = tipoConexion.CallApi(conexion.get().getUrl(), 
						conexion.get().getAccion(), tokenRequest);
				
				if(resultado != null) {
					
					Gson gson = new Gson();
										
					TokenResponseBean tokenResponse = gson.fromJson(resultado, TokenResponseBean.class);
					
					if(tokenResponse.getRespuesta() == 1) {
						TokenManager.getInstance().nuevoToken(tokenResponse, servicio);
						return tokenResponse.getToken();
					}else {
						throw new Exception("Error al solicitar el token del servicio "+ servicio + " error: " + tokenResponse.getMensaje());
					}					
				}else {
					//TODO ERROR

					return null;
				}
			}
		}
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
