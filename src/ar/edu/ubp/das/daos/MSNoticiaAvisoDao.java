package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.bean.NoticiaAvisoBean;
import ar.edu.ubp.das.db.Dao;

public class MSNoticiaAvisoDao extends Dao<NoticiaAvisoBean, List<NoticiaAvisoBean>> {

	@Override
	public NoticiaAvisoBean delete(List<NoticiaAvisoBean> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoticiaAvisoBean insert(List<NoticiaAvisoBean> arg0) throws SQLException {
		try {
			this.connect();
			this.setProcedure("{CALL dbo.INS_NOTICIAS");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public NoticiaAvisoBean make(ResultSet resultSet) throws SQLException {
NoticiaAvisoBean bean = new NoticiaAvisoBean();
		
		bean.setFecha(resultSet.getTimestamp("fecha"));
		bean.setMensaje(resultSet.getString("mensaje"));
		bean.setTipo(resultSet.getString("tipo"));
		
		return bean;
	}

	@Override
	public List<NoticiaAvisoBean> select(List<NoticiaAvisoBean> arg0) throws SQLException {
		try {
			this.connect();
			this.setProcedure("dbo.GET_NOTICIAS");
			return this.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public NoticiaAvisoBean update(List<NoticiaAvisoBean> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(List<NoticiaAvisoBean> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


}
