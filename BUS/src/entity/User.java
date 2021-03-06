package entity;

import java.util.List;

import dao.UserDao;
import dao.VoucherDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.exception.loginException;
import entity.exception.voucherErrorException;

public class User {
	String name;
	String email;
	String password;
	String facebook;
	List<Account> account;

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	public String getEMail() {
		return email;
	}

	public void setEMail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
	public static boolean register(User user){
			UserDao userDao;
			try {
				userDao = (UserDao) Class.forName("DaoImpl.UserDaoDB").newInstance();
				userDao.registerUser(user.getEMail(), user.getFacebook(), user.getName(), user.getPassword());
				return true;
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | DaoException e) {
				return false;
			}
			
		
	}
	
	public static User login(String user, String password) throws NoDataFoundException, loginException{
		UserDao userDao;
		try {
			userDao = (UserDao) Class.forName("DaoImpl.UserDaoDB").newInstance();
			return(userDao.validateLogin(user, password));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | DaoException e) {
			e.printStackTrace();
			throw new loginException();
		}
	}
	
	public static void like(String user, int shop){
		UserDao userDao;
		try {
			userDao = (UserDao) Class.forName("DaoImpl.UserDaoDB").newInstance();
			userDao.addLike(user, shop);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void disLike(String user, int shop){
		UserDao userDao;
		try {
			userDao = (UserDao) Class.forName("DaoImpl.UserDaoDB").newInstance();
			userDao.removeLike(user, shop);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 

}
