package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;

import org.apache.log4j.Logger;

import util.enums.TipoJugador;
import General.ConsultasGenerales;
import Robots.BatallaNavalRobotIDAODB;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import dao.usuarios.UsuarioDao;
import entity.usuarios.Administrador;
import entity.usuarios.Director;
import entity.usuarios.Jugador;
import entity.usuarios.Usuario;

public class UserDaoDB implements UserDao {
	private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	// resource bundle del properties definido arriba
	private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME_DAO_PROPERTIES);

	private static Logger logger = Logger.getLogger("UserDaoDB.class");

	

}
