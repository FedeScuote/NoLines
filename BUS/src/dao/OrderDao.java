package dao;

import dao.exception.DaoException;
import entity.Order;

public interface OrderDao {
	
	//Submits an orden in the DataBase, 
	//requires the user that is submiting the order, the id of the restaurant, and the id's and quantities of each plate.
	//plates[plate_id][amount]
	void submitOrder(String userEmail,int payingNumber, int restaurant,int[][] plates) throws DaoException;
	
	//Gets an order from the dataBase by its order number.
	Order retrieveOrder(int orderId);

}
