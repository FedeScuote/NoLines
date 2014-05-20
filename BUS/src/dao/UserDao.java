package dao;

import java.util.LinkedList;

import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.User;

public interface UserDao {

	// Devuelve todos los usuarios del mismo tipo de la clase al que se lo pide.
	LinkedList<User> obtenerTodos() throws NoDataFoundException, DaoException;

	// SOLO USUARIOS ACTIVOS
    User findByName(String name) throws NoDataFoundException, DaoException;

    User updatePassword(String usuario, String password) throws NoDataFoundException, DaoException;

    void desactivarUsuario(String name) throws NoDataFoundException, DaoException;
}
