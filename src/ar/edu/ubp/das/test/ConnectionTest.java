package ar.edu.ubp.das.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.conections.IConnections;
import ar.edu.ubp.das.conections.RestConnection;
import ar.edu.ubp.das.conections.SoapConnection;
import ar.edu.ubp.das.conections.util.Conexion;

class ConnectionTest {

	private final ConnectionManager connectionManager = new ConnectionManager();
	
	@Test
	void testRest() {
		IConnections rest = connectionManager.getConnectionByType("Rest");
		
		assert(rest instanceof RestConnection);
	}

	@Test 
	void testSoap() {
		IConnections soapConnection = connectionManager.getConnectionByType("Soap");
		
		assert(soapConnection instanceof SoapConnection);
	}
	
	@Test
	void testNoExiste() {
		IConnections nullConnection = connectionManager.getConnectionByType("null");
		
		assertNull(nullConnection);
	}
	
	@Test
	void testEmpty() {
		IConnections emptyConnection = connectionManager.getConnectionByType("");
		
		assertNull(emptyConnection);
	}
		

	@Test
	void getConnection() {
		Conexion conexion = connectionManager.getConnectionById(1);
		
		assertNotNull(conexion);
		
		assertEquals("Rest",conexion.getTipo());
		assertEquals("Post", conexion.getAccion());
		assertEquals("http://localhost:8080/BomberosRest/api/asistencias",conexion.getUrl());
		
	}
	
	@Test
	void getReqTokenTrue() {
		Conexion conexion = connectionManager.getConnectionById(1);
		
		assertNotNull(conexion);
		assertTrue(conexion.isReqToken());			
	}
	
	@Test
	void getReqTokenFalse() {
		Conexion conexion = connectionManager.getConnectionById(2);
		
		assertNotNull(conexion);
		
		assertFalse(conexion.isReqToken());	;				
	}
	
	@Test
	void getNegTokenFalse() {
		Conexion conexion = connectionManager.getConnectionById(2);
		
		assertNotNull(conexion);
		
		assertFalse(conexion.isNegociaToken());				
	}
	
	@Test
	void getNegTokenTrue() {
		Conexion conexion = connectionManager.getConnectionById(3);
		
		assertNotNull(conexion);
		
		assertTrue(conexion.isNegociaToken());				
	}
	
}
