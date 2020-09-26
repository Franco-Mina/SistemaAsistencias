package ar.edu.ubp.das.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.conections.ConnectionManager;

@Path("/asistencia")
public class AsistenciaResource {

	private ConnectionManager connectionManager = new ConnectionManager();	
	
	@GET
	public Response getAsistencias() {
		String result = this.connectionManager.callApi(2, null).toString();
		return Response.status(Response.Status.OK).entity(result!=null?result:"error").build();
	}
}
