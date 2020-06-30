package ar.edu.ubp.das.conections;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


public class SoapConnection implements IConnections {

	@Override
	public String CallApi(String url, String accion, Object parameters) {
		Object[] response = null;
		
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		
//		"http://localhost:8080/CXFTest/services?wsdl"
		Client cliente = factory.createClient(url);
		
		try {
			response = cliente.invoke(accion);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return (String) response[0];
	}

}
