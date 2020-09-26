package ar.edu.ubp.das.conections;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.google.gson.Gson;


public class SoapConnection implements IConnections {

	@Override
	public String CallApi(String url, String accion, Object parameters) {
		Object[] response = null;
		
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		
		Client cliente = factory.createClient(url);		
		
		Gson gson = new Gson();
		
		String jsonParam = gson.toJson(parameters);
		
		try {
			
			response = cliente.invoke(accion,jsonParam);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
				
		return response[0].toString();
	}

}
