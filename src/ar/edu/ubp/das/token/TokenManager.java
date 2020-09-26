package ar.edu.ubp.das.token;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import ar.edu.ubp.das.bean.TokenBean;
import ar.edu.ubp.das.daos.MSTokenDao;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.token.bean.TokenResponseBean;

public class TokenManager {

	private static TokenManager _tokenManager;
	private List<TokenBean> _listaTokens = null;
	private Object _lock = null;
	
	private TokenManager() {
		_listaTokens = new LinkedList<TokenBean>();
		_lock = new Object();
	}
	
	
	public static TokenManager getInstance() {
		if(_tokenManager == null) {
			_tokenManager = new TokenManager();
		}
		
		return _tokenManager;
	}
	
	
	public TokenBean buscarToken(String servicio) {
	
		Optional<TokenBean> token = null;
		
		if(!this._listaTokens.isEmpty()) {
			token = this._listaTokens.stream().filter(x -> x.getIdServicio().equals(servicio) 
					&& x.getFechaExpiracion().after(new Timestamp(System.currentTimeMillis()))).findFirst();
			
			if(token.isPresent()) return token.get();
		}
		
		try {
			Dao<TokenBean, TokenBean> dao = DaoFactory.getDao("Token", "ar.edu.ubp.das");
			
			
			token = Optional.ofNullable(((MSTokenDao) dao).buscarToken(servicio));
					
			if(token.isPresent()) {
				this._listaTokens.removeIf(x -> x.getIdServicio().equals(servicio));
				this._listaTokens.add(token.get());
				
				return token.get();
			}
			
			return null;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void nuevoToken(TokenResponseBean tokenResponse, String servicio) {
		
		try {
			Dao<TokenBean, TokenBean> dao = DaoFactory.getDao("Token", "ar.edu.ubp.das");
			
			TokenBean tokenNuevo = new TokenBean();
			
			tokenNuevo.setIdServicio(servicio);
			tokenNuevo.setFechaExpiracion(tokenResponse.getFechaExpiracion());
			tokenNuevo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
			tokenNuevo.setToken(tokenResponse.getToken());
			
			dao.insert(tokenNuevo);
			
			this.agregarToken(tokenNuevo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void agregarToken (TokenBean token) {
		synchronized (this._lock) {
			this._listaTokens.removeIf(x -> x.getIdServicio() == token.getIdServicio());
			this._listaTokens.add(token);
		}
	}
	
//	private void agregarToken (List<TokenBean> listaToken) {
//		List<String> listaServicios = listaToken.stream().map(TokenBean::getIdServicio).collect(Collectors.toList());
//		synchronized (this._lock) {
//			this._listaTokens.removeIf(x ->listaServicios.contains(x.getIdServicio()));
//			this._listaTokens.addAll(listaToken);
//		}
//	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		try {
	        throw new CloneNotSupportedException();
	    } catch (CloneNotSupportedException ex) {
	        System.out.println("No se puede clonar el objeto");
	    }
	    return null; 
	}
}
