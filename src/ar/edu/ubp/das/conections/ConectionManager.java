package ar.edu.ubp.das.conections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConectionManager {

	public IConnections GetConection(String tipo) {
		Class<?> clase;
		try {
			clase = Class.forName("ar/edu/ubp/das/conections/" + tipo+"Connection");

			Constructor<?> constructor = clase.getConstructor();
			
			Object instancia = constructor.newInstance();

			
			return (IConnections) instancia;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}
