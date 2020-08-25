package ar.edu.ubp.das.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/ping")
public class PingResource {

	@GET
	public Response getPing() {
		return Response.status(Status.OK).entity("pong").build();
	}
	
	@POST
	public Response postPing() {
		return Response.status(Status.OK).entity("pong").build();
	}
}
