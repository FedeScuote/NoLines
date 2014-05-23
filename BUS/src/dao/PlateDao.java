package dao;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Plate;


public interface PlateDao {

	//Obtener un Plate por su id
	Plate getById(int id) throws NoDataFoundException, DaoException;
}
