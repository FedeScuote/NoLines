package services;

import java.util.List;

public interface RestaurantService {
	public List getMenu(String idRestaurant);
	public int getRanking(String idRestaurant);
	public void addRestaurant(String name, String logo, String location, String horario);
	public void removeRestaurant(String idRestaurant);
	public List getRestaurants();

}
