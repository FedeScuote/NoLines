package dao;

import java.util.LinkedList;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Shop;
import entity.Plate;

public interface LocalDao {
	
	//Obtener un Local por su nombre
	Shop getByName(String name) throws NoDataFoundException, DaoException;

	
	//Obtener el Menu de un restaurante por el nombre del restaurante.
	LinkedList<Plate> getMenu(String name) throws NoDataFoundException, DaoException;
	
}
