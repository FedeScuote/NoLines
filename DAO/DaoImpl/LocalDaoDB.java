package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;

import org.apache.log4j.Logger;

public class LocalDaoDB implements LocalDao {
	private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	// resource bundle del properties definido arriba
	private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME_DAO_PROPERTIES);

	private static Logger logger = Logger.getLogger("LocalDaoDB.class");
}

