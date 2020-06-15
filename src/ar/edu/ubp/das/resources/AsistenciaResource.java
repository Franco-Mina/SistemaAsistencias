package ar.edu.ubp.das.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.bean.AsistenciaBean;
import ar.edu.ubp.das.conections.RestConnection;

@Path("/asistencia")
public class AsistenciaResource {

	@GET
	public Response getAsistencias() {
		RestConnection connection = new RestConnection();
		String result = connection.CallApi("http://localhost:8080/BomberosRest/api/asistencias", "Get", null);
		return Response.status(Response.Status.OK).entity(result!=null?result:"error").build();
	}
	
	
	@POST
	public Response instAsistencia() {
		RestConnection connection = new RestConnection();
		
		AsistenciaBean asistencia = new AsistenciaBean();
		asistencia.setId(100);
		asistencia.setIdServicio("200");

		String result = connection.CallApi("http://localhost:8080/BomberosRest/api/asistencias", "Post", asistencia);
		return Response.status(Response.Status.OK).entity(result!=null?result:"error").build();
	}
}
