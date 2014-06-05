package dao;

import java.util.LinkedList;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.User;

public interface UserDao {

	//Returns a linkedList of all the user registred.
	LinkedList<User> obtenerTodos() throws NoDataFoundException, DaoException;

	//Finds a user in database searching by its email.
    User findByEMail(String email) throws NoDataFoundException, DaoException;

    User updatePassword(String usuario, String password) throws NoDataFoundException, DaoException;

    User registerUser(String email,String facebook,String name,String password);
}
