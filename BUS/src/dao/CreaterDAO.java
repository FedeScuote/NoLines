package dao;

import java.util.ResourceBundle;

import dao.DaoException;

public class CreaterDAO {

	//properties donde tengo todas las variables definidas
	private static final String RESOURCE_NAME_PROPERTIES =  "conf.bus_properties";
	//resource bundle del properties definido arriba
	private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME_PROPERTIES);

	// provienen de las variables del properties
	private static String DAOEXCEPTION_K = "DAO_EXCEPTION";

	// BUS no puede crear directamente un ObjectDaoDB, sino debera depender de DAO
	// Sera dependencia cclica y el compilador no tendra por donde
	// empezar. Se usa esto.

	public static Object getDao(String dao) throws DaoException {
		try {
			// Tiene que tener a Dao en el ClassPath al ejecutar esto.
			return Class.forName(dao).newInstance();
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DaoException(rb.getString(DAOEXCEPTION_K));
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DaoException(rb.getString(DAOEXCEPTION_K));
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new DaoException(rb.getString(DAOEXCEPTION_K));
		}
	}

}
