package entity;

import java.util.LinkedList;
import java.util.List;

import dao.LocalDao;
import dao.OrderDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.exception.PersistException;

public class Order {
	int id;
	List items;
	List amounts;
	String idUser;
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public List getAmounts() {
		return amounts;
	}

	public void setAmounts(List amounts) {
		this.amounts = amounts;
	}

	String idReastaurant;

	public String getIdReastaurant() {
		return idReastaurant;
	}

	public void setIdReastaurant(String idReastaurant) {
		this.idReastaurant = idReastaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public void addItem(String id) {
		items.add(id);
	}
	
	public void addIAmount(int a) {
		amounts.add(a);
	}

	public int calculatePrice() {
		return 0;
	}

	public int calculateTime() {
		return 0;
	}
	
	public void persistOrder() throws PersistException{
		try {
			int largo = items.size();
			int[][] plates = new int[largo][2];
			for (int i = 0; i < largo; i++) {
					plates[i][0] = Integer.parseInt(items.get(i).toString());
					plates[i][1] = Integer.parseInt((amounts.get(i).toString()));
			}
			OrderDao orderDao = (OrderDao) Class.forName("DaoImpl.OrderDaoDB").newInstance();
			orderDao.submitOrder(this.getIdUser(), 123456, Integer.parseInt(this.getIdReastaurant()), plates);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NumberFormatException | DaoException e) {
			e.printStackTrace();
			throw new PersistException();
		}
	}
	
	 public static void main(String[] args){
		 String plato1 = "1";
		 String plato2 = "2";
		 String am1= "3";
		 String am2= "6";
		 String rest="1";
		 List platos = new LinkedList<>();
		 List cants = new LinkedList<Integer>();
		 platos.add(plato1);
		 platos.add(plato2);
		 cants.add(Integer.parseInt(am1));
		 cants.add(Integer.parseInt(am2));
		 Order order = new Order();
		 order.setAmounts(cants);
		 order.setItems(platos);
		 order.setIdReastaurant(rest);
		 try {
			order.persistOrder();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

}
