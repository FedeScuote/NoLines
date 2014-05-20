package dao;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.Shop;

public interface LocalDao {
	Shop getByName(String name) throws NoDataFoundException, DaoException;
}
