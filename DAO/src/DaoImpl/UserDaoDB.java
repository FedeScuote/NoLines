package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import dao.UserDao;
import entity.User;

public class UserDaoDB implements UserDao {
	private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	// resource bundle del properties definido arriba
	private static ResourceBundle rb = ResourceBundle
			.getBundle(RESOURCE_NAME_DAO_PROPERTIES);

	public LinkedList<User> obtenerTodos() throws NoDataFoundException,
			DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByEMail(String mail) throws NoDataFoundException,
			DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public User updatePassword(String usuario, String password)
			throws NoDataFoundException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public void desactivarUsuario(String name) throws NoDataFoundException,
			DaoException {
		// TODO Auto-generated method stub

	}

}
