package ar.edu.ubp.das.conections;

import java.io.IOException;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class CustomStringResponseHandler implements HttpClientResponseHandler<String> {

	@Override
	public String handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
		int status = response.getCode();
	
		if(status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
			HttpEntity entity = response.getEntity();
			try {
				return entity!=null ? EntityUtils.toString(entity):null;
			}catch (ParseException ex) {
				throw new ClientProtocolException(ex);
			}						
		}else {
			throw new ClientProtocolException("Respuesta inesperada " + status);
		}
	}
}
