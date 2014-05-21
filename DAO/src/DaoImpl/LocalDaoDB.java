package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;
import dao.LocalDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Shop;
import entity.plate;

public class LocalDaoDB implements LocalDao {
	
	//Metodo para buscar un local por su nombre, devuelve el local si lo encuentra sino tira la exception de NoDataFound.
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
		shop.setId(rsShop.getString("id_local"));
		shop.setHorario(rsShop.getString("horario"));
		shop.setLogo(rsShop.getString("logo"));		
		shop.setLocation(rsShop.getString("location"));
		return shop;
	}

	//Metodo que devuelve todos los platos de un restaurante segun su nombre.
	public LinkedList<plate> getMenu(String name) throws NoDataFoundException,
			DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String getPlates = "SELECT * FROM plate WHERE plate.restaurant_name='"+name+"'";

		ResultSet rsPlates = jdbc.select(getPlates);
		LinkedList<plate> plates = new LinkedList<>();
		try{
			while (rsPlates.next()){
				plate platito=loadPlate(rsPlates,jdbc);
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
	private plate loadPlate(ResultSet rsPlates, AccesoJDBC jdbc) throws SQLException {
		plate plate = new plate();
		plate.setName(rsPlates.getString("name"));
		plate.setId(rsPlates.getString("plate_id"));
		plate.setPicture(rsPlates.getString("foto"));
		plate.setDescription(rsPlates.getString("description"));
		plate.setTime(rsPlates.getInt("cook_time"));
		plate.setPrice(rsPlates.getDouble("price"));
		return plate;
	}
	
	public static void main(String[] args){
		LocalDaoDB prueba =new LocalDaoDB();
		Shop shopPrueba = new Shop();
		LinkedList<plate> menu = new LinkedList();
		try {
			shopPrueba = prueba.getByName("Mc Donalds");
			menu=prueba.getMenu("Mc Donalds");
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(shopPrueba.getLocation());
		System.out.println(shopPrueba.getHorario());
		System.out.println(shopPrueba.getId());
		
		System.out.println("El largo deberia ser 2 y es"+menu.size());
		System.out.println(menu.getFirst().getDescription());
		System.out.println(menu.getLast().getDescription());
	}
}

