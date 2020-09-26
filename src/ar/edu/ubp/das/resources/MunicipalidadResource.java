package ar.edu.ubp.das.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.bean.ws.NoticiasRequestBean;
import ar.edu.ubp.das.conections.ConnectionManager;

@Path("/municipalidad")
public class MunicipalidadResource {

	private ConnectionManager connectionManager = new ConnectionManager();
	
	@GET
	@Path("/noticias")
	public Response getNoticias() {
		NoticiasRequestBean noticiaRequest = new NoticiasRequestBean();
		noticiaRequest.setUsuario("u1");
		
		String result = this.connectionManager.callApi(4, noticiaRequest).toString();
		return Response.status(Response.Status.OK).entity(result!=null?result:"error").build();
	}

}
