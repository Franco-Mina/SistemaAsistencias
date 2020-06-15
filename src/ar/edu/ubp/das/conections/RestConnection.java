package ar.edu.ubp.das.conections;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.HttpEntities;

import com.google.gson.Gson;



public class RestConnection implements IConnections {

	@Override
	public String CallApi(String uri, String accion, Object parameters) {		
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			
			HttpClientResponseHandler<String> responseHandler = new CustomStringResponseHandler();
			HttpUriRequestBase httpRequest = getAccion(accion, uri);
					
			if(httpRequest == null) return "error en reflection";
			
			if(parameters != null) {
				Gson serializer = new Gson();
				
				String parametros = serializer.toJson(parameters);
				HttpEntity entity = HttpEntities.create(parametros,ContentType.APPLICATION_JSON);
				
				httpRequest.setEntity(entity);
			}
			
			String responseBody = client.execute(httpRequest,responseHandler);
			
			return responseBody;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public HttpUriRequestBase getAccion(String accion, String uri) {
		if(accion == null || accion.trim().isEmpty() || 
				uri == null || uri.trim().isEmpty()) return null;
		
		try {
			Class<?> clase = Class.forName("org.apache.hc.client5.http.classic.methods.Http" + accion);
			
			Class<?>[] parameterType = {String.class};
			
			Constructor<?> constructor = clase.getConstructor(parameterType);
			
			Object instancia = constructor.newInstance(uri);
			
			return (HttpUriRequestBase) instancia;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
