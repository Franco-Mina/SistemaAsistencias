package ar.edu.ubp.das.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.conections.IConnections;
import ar.edu.ubp.das.conections.RestConnection;
import ar.edu.ubp.das.conections.SoapConnection;
import ar.edu.ubp.das.util.Conexion;
import ar.edu.ubp.das.util.Conexiones;

class ConnectionTest {

	private final ConnectionManager connectionManager = new ConnectionManager();
	
	@Test
	void testRest() {
		IConnections rest = connectionManager.getConnection("Rest");
		
		assert(rest instanceof RestConnection);
	}

	@Test 
	void testSoap() {
		IConnections soapConnection = connectionManager.getConnection("Soap");
		
		assert(soapConnection instanceof SoapConnection);
	}
	
	@Test
	void testNoExiste() {
		IConnections nullConnection = connectionManager.getConnection("null");
		
		assertNull(nullConnection);
	}
	
	@Test
	void testEmpty() {
		IConnections emptyConnection = connectionManager.getConnection("");
		
		assertNull(emptyConnection);
	}
		
	@Test
	void parseXml() {
				
		Conexiones conexiones = connectionManager.getConnections();
		
		assertNotNull(conexiones);
		
		assertNotNull(conexiones.getListaConexiones());
				
		assertNotEquals(0, conexiones.getListaConexiones().size());
		assertEquals("Rest", conexiones.getListaConexiones().get(0).getTipo());
		assertEquals("Post", conexiones.getListaConexiones().get(0).getAccion());
		assertEquals("http://localhost:8080/BomberosRest/api/asistencias", conexiones.getListaConexiones().get(0).getUrl());
	}
	
	@Test
	void getConnection() {
		Conexion conexion = connectionManager.getConnectionById(1);
		
		assertNotNull(conexion);
		
		assertEquals("Rest",conexion.getTipo());
		assertEquals("Post", conexion.getAccion());
		assertEquals("http://localhost:8080/BomberosRest/api/asistencias",conexion.getUrl());
		
	}
}
