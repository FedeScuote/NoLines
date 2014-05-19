package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;

import org.apache.log4j.Logger;

import dao.DaoException;
import dao.NoDataFoundException;
import dao.UserDao;
import entity.User;

public class UserDaoDB implements UserDao {
	private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	// resource bundle del properties definido arriba
	private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME_DAO_PROPERTIES);

	private static Logger logger = Logger.getLogger("UserDaoDB.class");

	
	// Devuelve todos los usuarios del mismo tipo de la clase al que se lo pide.
	LinkedList<User> obtenerTodos() throws NoDataFoundException, DaoException{
		
	}

	// SOLO USUARIOS ACTIVOS
    User findByName(String name) throws NoDataFoundException, DaoException{
    	
    }

    User updatePassword(String usuario, String password) throws NoDataFoundException, DaoException{
    	
    }

    void desactivarUsuario(String name) throws NoDataFoundException, DaoException{
    	
    }
	

}
