package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.bean.LoginBean;
import ar.edu.ubp.das.db.Dao;

public class MSLoginDao extends Dao<LoginBean, LoginBean> {

	@Override
	public LoginBean delete(LoginBean login) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginBean insert(LoginBean entidad) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginBean make(ResultSet resultSet) throws SQLException {
		LoginBean loginBean = new LoginBean();
		
		loginBean.setApellido(resultSet.getString("apellido"));
		loginBean.setCodigo(resultSet.getString("codigo"));
		loginBean.setCuil(resultSet.getInt("cuil"));
		loginBean.setDireccion(resultSet.getString("direccion"));
		loginBean.setDni(resultSet.getInt("dni"));
		loginBean.setEmail(resultSet.getString("email"));
		loginBean.setNombre(resultSet.getString("nombre"));
		loginBean.setPais(resultSet.getString("pais"));
		loginBean.setPassword(resultSet.getString("contraseña"));
		loginBean.setTelefono(resultSet.getString("telefono"));

		return loginBean;
	}

	@Override
	public List<LoginBean> select(LoginBean login) throws SQLException {
		try {
			this.connect();
			
			this.setProcedure("dbo.OBTENER_USUARIO(?)");
			
			this.setParameter(1, login.getEmail());
			
			return this.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LoginBean update(LoginBean entidad) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(LoginBean entidad) throws SQLException {
		return false;		
	}

}
