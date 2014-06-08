package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;
import dao.LocalDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Plate;
import entity.Restaurant;
import entity.Shop;

public class LocalDaoDB implements LocalDao {
	
	//Metodo para obtener todos los restaurantes de la base de datos.
	public LinkedList<Restaurant> getRestaurants() throws NoDataFoundException, DaoException{
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		
		String getRestaurants = "SELECT * FROM local WHERE local.id_local IN (SELECT id_restaurant from restaurant)";
		ResultSet rsRestaurants = jdbc.select(getRestaurants);
		LinkedList<Restaurant> restaurantes = new LinkedList<Restaurant>();
		try{
			while (rsRestaurants.next()){
				restaurantes.add(loadRestaurant(rsRestaurants.getInt("id_local"),rsRestaurants,jdbc));		
			}
			return restaurantes;
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
	}
	
	private Restaurant loadRestaurant(int id, ResultSet rsRestaurants,
			AccesoJDBC jdbc) throws SQLException {
		Restaurant shop = new Restaurant();
		shop.setId(id);
		shop.setName(rsRestaurants.getString("name"));
		shop.setHorario(rsRestaurants.getString("horario"));
		shop.setLogo(rsRestaurants.getString("logo"));		
		shop.setLocation(rsRestaurants.getString("location"));
		shop.setDescription(rsRestaurants.getString("description"));
		return shop;
	}

	//Metodo para buscar un local por su id, devuelve el local si lo encuentra sino tira la exception de NoDataFound.
	public Shop getById(int id) throws NoDataFoundException, DaoException{
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		
		String getShop = "SELECT * FROM local WHERE local.id_local='"+id+"'";
		ResultSet rsShop = jdbc.select(getShop);
		try{
			if (rsShop.next()){
				return loadShop(id,rsShop,jdbc);
			}
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
		return null;
	}
	
	//submetodo para setear todos los atributos a un Shop.
	private Shop loadShop(int id,ResultSet rsShop,AccesoJDBC jdbc) throws SQLException{
		Shop shop = new Shop();
		shop.setId(id);
		shop.setName(rsShop.getString("name"));
		shop.setHorario(rsShop.getString("horario"));
		shop.setLogo(rsShop.getString("logo"));		
		shop.setLocation(rsShop.getString("location"));
		return shop;
	}

	//Metodo que devuelve todos los platos de un restaurante segun su nombre.
	public LinkedList<Plate> getMenu(int id) throws NoDataFoundException,
			DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String getPlates = "SELECT * FROM plate WHERE plate.id_restaurant='"+id+"'";

		ResultSet rsPlates = jdbc.select(getPlates);
		LinkedList<Plate> plates = new LinkedList<Plate>();
		try{
			while (rsPlates.next()){
				Plate platito=loadPlate(rsPlates,jdbc);
				plates.add(platito);
			}
			return plates;
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
	}

	//submetodo para setear todos los atributos a un Plate
	private Plate loadPlate(ResultSet rsPlates, AccesoJDBC jdbc) throws SQLException {
		Plate plate = new Plate();
		plate.setName(rsPlates.getString("name"));
		plate.setId(rsPlates.getString("plate_id"));
		plate.setPicture(rsPlates.getString("foto"));
		plate.setDescription(rsPlates.getString("description"));
		plate.setTime(rsPlates.getInt("cook_time"));
		plate.setPrice(rsPlates.getDouble("price"));
		return plate;
	}

	public List getLocals() throws NoDataFoundException, DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String getLocales = "SELECT * FROM local";
		ResultSet rsLocales = jdbc.select(getLocales);
		LinkedList<Shop> locales = new LinkedList<Shop>();
		try{
			while (rsLocales.next()){
				locales.add(loadShop(rsLocales.getInt("id_local"),rsLocales,jdbc));		
			}
			return locales;
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
	}

}

