package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConexionDB {
  private Connection con;
  //private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	// resource bundle del properties definido arriba
	//private static ResourceBundle rb = ResourceBundle
	//		.getBundle(RESOURCE_NAME_DAO_PROPERTIES);

 
  //private static final String DRIVER = rb.getString("DRIVER");
  //private static final String URL = rb.getString("URL");
  //private static final String USER = rb.getString("USER");
  //private static final String PASSW = rb.getString("PASSW");
  
  private static final String DRIVER="com.mysql.jdbc.Driver";
  private static final String URL="jdbc:mysql://localhost/noLines";
  private static final String USER="root";
  private static final String PASSW="";
  
  static {
    try {
      // Load the jdbc-odbc bridge driver
      Class.forName(DRIVER);
    }
    catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      System.exit(-1);

    }

  }

  public ConexionDB() {

  }

  public AccesoJDBC getAccesoJDBC() throws NoDatabaseConexionException {
    if (con != null) {
      return new AccesoJDBC(con);
    }
    else {
      throw new NoDatabaseConexionException();
    }

  }

  public void connect() {
    try {

      // Attempt to connect to a driver.  Each one
      // of the registered drivers will be loaded until
      // one is found that can process this URL
      //Connection con = DriverManager.getConnection(url);
      con = DriverManager.getConnection(URL, USER, PASSW);

      // If we were unable to connect, an exception
      // would have been thrown.  So, if we get here,
      // we are successfully connected to the URL

      // Check for, and display and warnings generated
      // by the connect.



      // Get the DatabaseMetaData object and display
      // some information about the connection
      //  dma = con.getMetaData();

    }
    catch (SQLException ex) {

      // A SQLException was generated.  Catch it and
      // display the error information.  Note that there
      // could be multiple error objects chained
      // together

      ex.printStackTrace();
    }

  }

  public void disconnect() {
    if (con != null) {
      try {
        con.close();
        con = null;
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

}

