package ar.edu.ubp.das.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/municipalidad")
public class MunicipalidadResource {

	@GET
	public Response getNoticias() {
		String result = this.connectionManager.callApi(2, null);
		return Response.status(Response.Status.OK).entity(result!=null?result:"error").build();
	}
}
