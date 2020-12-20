package ar.edu.ubp.das.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.db.WebDbManager;

@Path("/asistencia")
public class AsistenciaResource {

	private ConnectionManager connectionManager = new ConnectionManager("/WEB-INF/conexiones.xml",new WebDbManager("Token","ar.edu.ubp.das.db"));	
	
	@GET
	public Response getAsistencias() {
		String result = this.connectionManager.callApi(2, null).toString();
		return Response.status(Response.Status.OK).entity(result!=null?result:"error").build();
	}
}
