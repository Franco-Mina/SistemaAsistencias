package ar.edu.ub.das.token;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import ar.edu.ub.das.token.bean.TokenBean;
import ar.edu.ub.das.token.bean.TokenRequestBean;
import ar.edu.ub.das.token.bean.TokenResponseBean;
import ar.edu.ubp.das.bean.EntidadBean;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;

public class TokenManager {
	// Cantidad de milisegundos en una hora
	static final Long DURATION = ((60l * 60l) * 1000l);
	
	
	public TokenResponseBean getToken( TokenRequestBean request) {
		TokenResponseBean response = new TokenResponseBean();
		response.setRespuesta(0);
		response.setMensaje("");
	
		EntidadBean entidad = this.validarEntidad(request);
		
		if(entidad == null) {
			response.setMensaje("No se ha encontrado una entidad activa que corresponda con las credenciales ingresadas");
			return response;
		}
		
		TokenBean token = new TokenBean();
		
		Timestamp fecha = new Timestamp(Calendar.getInstance().getTime().getTime());
		
		token.setIdEntidad(entidad.getId());
		token.setFechaCreacion(fecha);
		token.setToken(UUID.randomUUID().toString());
		
		// Agregamos 8 horas, se puede cambiar el valor para cambiar la duracion
		token.setFechaExpiracion(new Timestamp(fecha.getTime() + (DURATION * 8)));
		
		try {
			Dao<TokenBean, TokenBean> dao = DaoFactory.getDao("Token", "ar.edu.ubp.das.token");
			
			token = dao.insert(token);
					
			response.setToken(token.getToken());
			response.setFechaExpiracion(token.getFechaExpiracion());
			response.setRespuesta(1);
			
			return response;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		}		
	}
	
	private EntidadBean validarEntidad(TokenRequestBean request) {
		try {
			Dao<EntidadBean,EntidadBean> daoEntidad = DaoFactory.getDao("Entidad", "ar.edu.ubp.das");
			
	
			EntidadBean entidad = new EntidadBean();
			entidad.setUsuario(request.getUsuario());
			entidad.setPassword(request.getPassword());
			
			List<EntidadBean> entidades = daoEntidad.select(entidad);
			
			return (entidades.isEmpty()) ? null : entidades.get(0);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
}
