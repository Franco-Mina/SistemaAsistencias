package ar.edu.ubp.das.test;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.conections.IConnections;
import ar.edu.ubp.das.conections.RestConnection;
import ar.edu.ubp.das.conections.SoapConnection;

class ConnectionTest {

	private final ConnectionManager connectionManager = new ConnectionManager();
	
	@Test
	void testRest() {
		IConnections rest = connectionManager.GetConection("Rest");
		
		assert(rest instanceof RestConnection);
	}

	@Test 
	void testSoap() {
		IConnections soapConnection = connectionManager.GetConection("Soap");
		
		assert(soapConnection instanceof SoapConnection);
	}
	
	@Test
	void testNoExiste() {
		IConnections nullConnection = connectionManager.GetConection("null");
		
		assertNull(nullConnection);
	}
}
