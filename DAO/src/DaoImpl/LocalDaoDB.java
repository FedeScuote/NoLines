package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;
import dao.LocalDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Shop;

public class LocalDaoDB implements LocalDao {
	//private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	// resource bundle del properties definido arriba
	//private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME_DAO_PROPERTIES);

	//private static Logger logger = Logger.getLogger("LocalDaoDB.class");
	
	public Shop getByName(String name) throws NoDataFoundException, DaoException{
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		
		String getShop = "SELECT * FROM local WHERE local.name='"+name+"'";
		ResultSet rsShop = jdbc.select(getShop);
		try{
			if (rsShop.next()){
				return loadShop(name,rsShop,jdbc);
			}
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
		return null;
	}
	//submetodo para setear todos los atributos a un Shop.
	private Shop loadShop(String name,ResultSet rsShop,AccesoJDBC jdbc) throws SQLException{
		Shop shop = new Shop();
		shop.setName(name);
		
		shop.setLocation(rsShop.getString("location"));
		return shop;
	}
	
	public static void main(String[] args){
		LocalDaoDB prueba =new LocalDaoDB();
		Shop shopPrueba = new Shop();
		try {
			shopPrueba = prueba.getByName("Mc Donalds");
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(shopPrueba.getLocation());
	}
	
}

