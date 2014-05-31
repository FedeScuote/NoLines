package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.OrderDao;
import dao.exception.DaoException;
import entity.Order;
import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;


public class OrderDaoDB implements OrderDao{
	
	//Submits an orden in the DataBase, 
	//requires the user that is submiting the order, the id of the restaurant, and the id's and quantities of each plate.
	//plates[plate_id][amount]
	public void submitOrder(String userEmail,int payingNumber, int restaurant, int[][] plates) throws DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String stat = "INSERT INTO `noLines`.`order` (`order_id`, `user_email`, `paying_account_number`, `restaurant_name`, `date`) VALUES (NULL, '"+userEmail+"', '"+payingNumber+"', '"+restaurant+"', NOW());";
		try {
			jdbc.modify(stat);
			int orderId=getLastOrderId(jdbc);
			for (int i = 0; i < plates.length; i++) {
				String insert="INSERT INTO `noLines`.`order_has_plate` (`order_id`, `plate_id`, `quantity`) VALUES ('"+orderId+"', '"+plates[i][0]+"', '"+plates[i][1]+"');";
				jdbc.modify(insert);
			}
		} catch (DaoException e) {
			throw new DaoException();
		}
	}

	private int getLastOrderId(AccesoJDBC jdbc) throws DaoException {
		String stat = "SELECT order_id FROM `order` ORDER BY order_id DESC LIMIT 1";
		try {
			ResultSet rsOrderId = jdbc.select(stat);
			try {
				if(rsOrderId.next()){
					return rsOrderId.getInt("order_id");
				}
				else{
					throw new DaoException();
				}
			} catch (SQLException e) {
				throw new DaoException();
			}
		} catch (DaoException e) {
			throw new DaoException();
		}
	}

	@Override
	public Order retrieveOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}



}
