package DaoImpl;

import java.util.ResourceBundle;

import dao.OrderDao;
import entity.Order;
import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;


public class OrderDaoDB implements OrderDao{

	@Override
	public void submitOrder(String userEmail, int restaurant, int[][] plates) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order retrieveOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}



}
