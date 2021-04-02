package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import ar.edu.ubp.das.bean.NoticiaAvisoBean;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;

@Path("/municipalidad")
public class MunicipalidadResource {

	@GET
	@Path("/noticias")
	public Response getNoticias(@Context HttpServletRequest context) {
		String path = context.getServletContext().getRealPath("/Logger/SistemaAsistencias/");
		List<NoticiaAvisoBean> listaNoticias;
		
		try {
			Dao<NoticiaAvisoBean, NoticiaAvisoBean> dao = DaoFactory.getDao("NoticiaAviso", "ar.edu.ubp.das");
		
			listaNoticias = dao.select(null);
			
			Gson gson = new Gson();
			
			String result = gson.toJson(listaNoticias); 
			
			return Response.status(Response.Status.OK).entity(result!=null?result:"Error").build();
			
		} catch (SQLException e) {
			ar.edu.ubp.das.logger.Logger.getLogger(path).escribirLog(e);
		}		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
	}

}
