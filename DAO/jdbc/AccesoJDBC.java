package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import dao.exception.DaoException;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AccesoJDBC {
	private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	// resource bundle del properties definido arriba
	private static ResourceBundle rb = ResourceBundle
			.getBundle(RESOURCE_NAME_DAO_PROPERTIES);

	private static Logger logger = Logger.getLogger("AccesoJDBC.class");
	private static String ERROR_MESSAGE_ACCESO_JDBC = rb.getString("ERROR_MESSAGE_ACCESO_JDBC");

  private Connection con;
  public AccesoJDBC(Connection con) {
    this.con = con;
  }

  public void modify(String stat) throws DaoException{
    if (con != null) {
      try {
        int n = con.createStatement().executeUpdate(stat);
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    else {
    	logger.error(ERROR_MESSAGE_ACCESO_JDBC);
      	throw new DaoException(ERROR_MESSAGE_ACCESO_JDBC);
    }
  }

  public ResultSet select(String stat) throws DaoException {
    if (con != null) {
      ResultSet rs = null;
      try {
        rs = con.createStatement().executeQuery(stat);
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
      return rs;
    }
    else {
    	logger.error(ERROR_MESSAGE_ACCESO_JDBC);
      	throw new DaoException(ERROR_MESSAGE_ACCESO_JDBC);
    }
  }

}
