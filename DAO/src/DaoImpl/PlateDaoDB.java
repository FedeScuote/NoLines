package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.PlateDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Plate;
import entity.Shop;
import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;


public class PlateDaoDB implements PlateDao{

	public Plate getById(int id) throws NoDataFoundException, DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		
		String getPlate = "SELECT * FROM plate WHERE plate.plate_id='"+id+"'";
		ResultSet rsPlate = jdbc.select(getPlate);
		try{
			if (rsPlate.next()){
				return loadPlate(rsPlate,jdbc);
			}
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
		return null;
	}
	
	//submetodo para setear todos los atributos a un Plate
	private Plate loadPlate(ResultSet rsPlate, AccesoJDBC jdbc) throws SQLException {
		Plate plate = new Plate();
		plate.setName(rsPlate.getString("name"));
		plate.setId(rsPlate.getString("plate_id"));
		plate.setPicture(rsPlate.getString("foto"));
		plate.setDescription(rsPlate.getString("description"));
		plate.setTime(rsPlate.getInt("cook_time"));
		plate.setPrice(rsPlate.getDouble("price"));
		return plate;
	}

}
