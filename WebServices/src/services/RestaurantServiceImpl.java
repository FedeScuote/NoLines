package services;

import java.util.LinkedList;
import java.util.List;

import entity.Restaurant;
import entity.Shop;
import entity.exception.getMenuException;

public class RestaurantServiceImpl implements RestaurantService {


	public int getRanking(String idRestaurant) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addRestaurant(String name, String logo, String location,
			String horario) {
		// TODO Auto-generated method stub
		
	}

	public void removeRestaurant(String idRestaurant) {
		// TODO Auto-generated method stub
		
	}

	public List getRestaurants() {
		return Shop.getAllRestaurants();
	}
	
	public List getRestaurantsByCategory(int category) {
		return Shop.getAllRestaurantsCategory(category);
	}
	public List getLikedRestaurants(String user){
		return Shop.getLikedRestaurants(user);
	}
	public List getMenu(int idRestaurant) {
		try {
			return Restaurant.getMenu(idRestaurant);
		} catch (getMenuException e) {
			e.printStackTrace();
			return new LinkedList();
		}
	}
	
	
}
