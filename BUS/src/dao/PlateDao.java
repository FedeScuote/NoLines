package dao;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Plate;


public interface PlateDao {

	//Get a plate from dataBase by it's Id.
	Plate getById(int id) throws NoDataFoundException, DaoException;
}
