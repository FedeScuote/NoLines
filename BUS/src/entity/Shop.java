package entity;

import java.util.LinkedList;
import java.util.List;

import dao.LocalDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;


public class Shop {
	int id;
	String name;
	String logo;
	String horario;
	String location;
	String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String buscarTaco(){
		 try {
				LocalDao local = (LocalDao) Class.forName("DaoImpl.LocalDaoDB").newInstance();
				Shop shop = local.getById(2);
				return(shop.getLocation());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "fallo";
	}
	
	public static final List getAllRestaurants(){ //tirar excepxiones y en lo otro decir que no se encontraron
		List restaurants = new LinkedList<Restaurant>();
		try {
			LocalDao local = (LocalDao) Class.forName("DaoImpl.LocalDaoDB").newInstance();
			restaurants = local.getRestaurants();
			return restaurants;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoDataFoundException | DaoException e) {
			return restaurants;
		}
	}

	public static final List getAllShops(){ //tirar excepxiones y en lo otro decir que no se encontraron
		List shops = new LinkedList<>();
		try {
			LocalDao local = (LocalDao) Class.forName("DaoImpl.LocalDaoDB").newInstance();
			shops = local.getLocals();
			return shops;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoDataFoundException | DaoException e) {
			return shops;
		}
	}
	
	public static final List getAllRestaurantsCategory(int category){ 
		List shops = new LinkedList<>();
		try {
			LocalDao local = (LocalDao) Class.forName("DaoImpl.LocalDaoDB").newInstance();
			shops = local.getAllRestaurantsCategory(category);
			return shops;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoDataFoundException | DaoException e) {
			return shops;
		}
	}
	
	public static final Shop getShop(int id){ //tirar excepxiones y en lo otro decir que no se encontraron
		Shop shop;
		try {
			LocalDao local = (LocalDao) Class.forName("DaoImpl.LocalDaoDB").newInstance();
			shop = local.getById(id);
			return shop;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoDataFoundException | DaoException e) {
			return new Shop();
		}
	}
 public static void main(String[] args){
	 LinkedList<Restaurant> lista =  (LinkedList<Restaurant>) Shop.getAllRestaurantsCategory(1);
	 System.out.print(lista.getFirst().getName());
 }
}