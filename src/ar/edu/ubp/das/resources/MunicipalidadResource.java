package ar.edu.ubp.das.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.bean.ws.NoticiasRequestBean;
import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.db.WebDbManager;

@Path("/municipalidad")
public class MunicipalidadResource {

	@GET
	@Path("/noticias")
	public Response getNoticias(@Context HttpServletRequest context) {		
		//Obtenemos el path para el connection manager
		String path = context.getServletContext().getRealPath("/WEB-INF/conexiones.xml");
		ConnectionManager connectionManager = 
				new ConnectionManager(path,new WebDbManager("Token","ar.edu.ubp.das.db"));	
		
		NoticiasRequestBean noticiaRequest = new NoticiasRequestBean();
		noticiaRequest.setUsuario("u1");
		
		String result = connectionManager.callApi(2, noticiaRequest).toString();
		return Response.status(Response.Status.OK).entity(result!=null?result:"error").build();
	}

}
