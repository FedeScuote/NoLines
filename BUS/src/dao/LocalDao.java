package dao;

import java.util.List;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Shop;

public interface LocalDao {
	
	//Obtener una lista de todos los locales que son restaurantes.
	List getRestaurants() throws NoDataFoundException, DaoException;
	
	//Obtener un Local por su id
	Shop getById(int id) throws NoDataFoundException, DaoException;

	
	//Obtener el Menu de un restaurante por el nombre del restaurante.
	List getMenu(int id) throws NoDataFoundException, DaoException;
	
	//Obtener una lista de todos los locales
	List getLocals() throws NoDataFoundException, DaoException;
	
	List getAllRestaurantsCategory(int category) throws NoDataFoundException, DaoException;
	
	List getLikedRestaurants(String user) throws NoDataFoundException, DaoException;
	
	List getUnLikedRestaurants(String user) throws NoDataFoundException, DaoException;
}
