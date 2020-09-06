package ar.edu.ub.das.token.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ar.edu.ub.das.token.bean.TokenBean;
import ar.edu.ubp.das.db.Dao;

public class MSTokenDao extends Dao<TokenBean, TokenBean>{

	@Override
	public TokenBean delete(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenBean insert(TokenBean token) throws SQLException {
		try {
			this.connect();
			
			this.setProcedure("dbo.INSERTAR_TOKEN(?,?,?,?)");
			this.setParameter(1, token.getIdEntidad());
			this.setParameter(2, token.getToken());
			this.setParameter(3, token.getFechaCreacion());
			this.setParameter(4, token.getFechaExpiracion());
			
			this.executeUpdate();
			
			return token;
			
		} finally {
			this.close();
		}
	}

	@Override
	public TokenBean make(ResultSet resultSet) throws SQLException {
		TokenBean token = new TokenBean();
		
		token.setFechaCreacion(resultSet.getTimestamp("Fecha_Creacion"));
		token.setFechaExpiracion(resultSet.getTimestamp("Fecha_Expiracion"));
		token.setId(resultSet.getInt("Id_Token"));
		token.setToken(resultSet.getString("Token"));
		token.setIdEntidad(resultSet.getInt("Id_Entidad"));

		return token;
	}

	@Override
	public List<TokenBean> select(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenBean update(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean valid(String token, String usuario) {

		boolean any = false;
		Connection conn;
		CallableStatement stmt;
		try {
			Context environment = Context.class.cast((new InitialContext()).lookup("java:comp/env"));	
			Class.forName((String)environment.lookup("ProviderName"));
			conn=DriverManager.getConnection((String)environment.lookup("ConnectionString"));
			conn.setAutoCommit(false);
			try {
				
				stmt = conn.prepareCall("{CALL dbo.VALIDAR_TOKEN(?,?)}");
				stmt.setString(1, token);
				stmt.setString(2, usuario);
				
				ResultSet resultSet = stmt.executeQuery();
				any = resultSet.next();
				
				stmt.close();
				}catch (SQLException e) {
					// TODO: handle exception		
					e.printStackTrace();
				}finally {
					conn.close();
				}
		} catch (ClassNotFoundException | NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return any;
	}
	
}
