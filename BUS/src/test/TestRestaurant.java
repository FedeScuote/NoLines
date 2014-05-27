package test;

import entity.Plate;
import entity.Restaurant;
import entity.exception.getMenuException;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestRestaurant {
	
	Restaurant restaurant = new Restaurant();
	@Test
	public void getMenu(){
		Plate plato1 = new Plate("1","Combo Big Mc","Big Mc con papas y bebida.","http://localhost/image/plate/mcdonalds1.png",195.0,4);
		Plate plato2 = new Plate("2","Combo Cuarto de Libra","Cuarto de libra con papas y bebida.","http://localhost/image/plate/mcdonalds2.png",205.0,5);
		List lista = new LinkedList<Plate>();
		lista.add(plato1);
		lista.add(plato2);
		try {
			List menu = Restaurant.getMenu(1);
			assertEquals(lista.size(), menu.size());
		} catch (getMenuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
