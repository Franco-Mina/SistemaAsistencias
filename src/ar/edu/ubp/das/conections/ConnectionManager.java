package ar.edu.ubp.das.conections;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ar.edu.ubp.das.util.Conexion;
import ar.edu.ubp.das.util.Conexiones;

public class ConnectionManager {
	
	public String callApi(int idConexion, Object parameters) {
		Conexion conexion = this.getConnectionById(idConexion);
		
		if(conexion == null || conexion.getTipo() == "") return null;
		
		IConnections tipoConexion = this.getConnection(conexion.getTipo());
		
		if(tipoConexion == null) return null;
		
		String resultado = tipoConexion.CallApi(conexion.getUrl(), conexion.getAccion(), parameters);
		
		return resultado;
	}
	
	public Conexion getConnectionById(int idConexion) {
		Conexiones conexiones = this.getConnections();
		
		Optional<Conexion> conexion =  conexiones.getListaConexiones().stream().filter(c -> c.getEntidad() == idConexion).findFirst();

		return conexion.isPresent() ? conexion.get() : null;
	}

	public IConnections getConnection(String tipo) {
		if(tipo == null || tipo.trim().isEmpty()) return null;
		
		Class<?> clase;
		try {
			clase = Class.forName("ar.edu.ubp.das.conections." + tipo+"Connection");

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
	
	public Conexiones getConnections() {
		//TODO: poner path en web.xml
		File file = new File("C:\\Java\\Final DAS\\SistemaAsistencias\\WebContent\\WEB-INF\\conexiones.xml");
		JAXBContext jaxbContext;
		
		try {
			jaxbContext = JAXBContext.newInstance(Conexiones.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			Conexiones conexiones = (Conexiones) jaxbUnmarshaller.unmarshal(file);
			
			return conexiones;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
