package services;

import java.util.List;

import entity.Shop;

public class RestaurantServiceImpl implements RestaurantService {

	public String Hola(){
		
		return "Hola";
	}
	public List getMenu(String idRestaurant) {
		// TODO Auto-generated method stub
		return null;
	}

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

}
