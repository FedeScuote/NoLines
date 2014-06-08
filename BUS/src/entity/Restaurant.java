package entity;

import java.util.LinkedList;
import java.util.List;

import dao.LocalDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.exception.getMenuException;

public class Restaurant extends Shop {

	public static final List getMenu(int id) throws getMenuException{ //tirar excepxiones y en lo otro decir que no se encontraron
		try {
			List menu = new LinkedList<Plate>();
			LocalDao local = (LocalDao) Class.forName("DaoImpl.LocalDaoDB").newInstance();
			menu = local.getMenu(id);
			return menu;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoDataFoundException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	


public static void main(String[] args){
	 LinkedList<Plate> lista;
	try {
		lista = (LinkedList<Plate>) Restaurant.getMenu(1);
		 System.out.print(lista.getFirst().getName());
		 
	} catch (getMenuException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}