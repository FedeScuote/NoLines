package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import DaoImpl.LocalDaoDB;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Plate;
import entity.Restaurant;
import entity.Shop;

public class TestLocalDaoDBB {
	LocalDaoDB localDaoDB=new LocalDaoDB();
	@Test
	public void getRestaurants(){
		LinkedList<Restaurant> restaurantes;
		try {
			restaurantes = localDaoDB.getRestaurants();
			assertEquals(1, restaurantes.getFirst().getId());
			assertEquals("Mc Donalds", restaurantes.getFirst().getName());
		} catch (NoDataFoundException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getById(){
		try {
			Shop shop = localDaoDB.getById(1);
			assertEquals("8:00 a 00:00", shop.getHorario());
		} catch (NoDataFoundException | DaoException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getMenu(){
		try {
			LinkedList<Plate> menu = localDaoDB.getMenu(1);
			assertEquals("Combo Big Mc",menu.getFirst().getName());
			assertEquals("http://localhost/image/plate/mcdonalds5.png", menu.getLast().getPicture());
		} catch (NoDataFoundException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
