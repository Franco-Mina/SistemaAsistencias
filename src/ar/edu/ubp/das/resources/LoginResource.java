package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import ar.edu.ubp.das.bean.LoginBean;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;

@Path("/municipalidad")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ingresarUsuario(@Context HttpServletRequest context, LoginBean loginReq) {
		String path = context.getServletContext().getRealPath("/Logger/SistemaAsistencias/");
		List<LoginBean> loginList;
		
		try {
			Dao<LoginBean, LoginBean> dao = DaoFactory.getDao("Login", "ar.edu.ubp.das");
		
			loginList = dao.select(loginReq);
			
			Gson gson = new Gson();
			
			String result = gson.toJson(loginList); 
			
			return Response.status(Response.Status.OK).entity(result!=null?result:"Error").build();
			
		} catch (SQLException e) {
			ar.edu.ubp.das.logger.Logger.getLogger(path).escribirLog(e);
		}		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
	}
	
	@GET
	public Response getPing() {
		return Response.status(Status.OK).entity("pong").build();
	}

}
