package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import dao.UserDao;
import entity.Account;
import entity.Restaurant;
import entity.User;

public class UserDaoDB implements UserDao {
	private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	//implementado
	public LinkedList<User> obtenerTodos() throws NoDataFoundException,
			DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		
		String getUsers = "SELECT * FROM user";
		ResultSet rsUsers = jdbc.select(getUsers);
		LinkedList<User> users = new LinkedList<User>();
		try{
			while (rsUsers.next()){
				users.add(loadUser(rsUsers, jdbc));		
			}
			return users;
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
	}
	
	//implementado
	public User findByEMail(String mail) throws NoDataFoundException,
			DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		User user = new User();
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String getUser = "SELECT * FROM user WHERE user.email='"+mail+"'";
		ResultSet rsUser = jdbc.select(getUser);
		try{
			if (rsUser.next()){
				user= loadUser(rsUser,jdbc);
			}else{
				throw new NoDataFoundException();
			}
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
		return user;
	}
	//implementado
	private User loadUser(ResultSet rsUser,AccesoJDBC jdbc) throws SQLException, DaoException {
		User user = new User();
		user.setEMail(rsUser.getString("email"));
		//user.setPassword(rsUser.getString("password"));
		user.setUserName(rsUser.getString("name"));
		user.setAccount(getAccounts(user.getEMail(),jdbc));
		//user.setFacebook(rsUser.getString("facebook"));
		return user;
	}
	//implementado
	private List<Account> getAccounts(String eMail,AccesoJDBC jdbc) throws DaoException, SQLException {
		LinkedList<Account> cuentas = new LinkedList<Account>();
		String stat="SELECT number from paying_account WHERE email = '"+eMail+"'";
		try {
			ResultSet rsCuentas=jdbc.select(stat);
			Account e=new Account();
			while(rsCuentas.next()){
				e.setNumber(rsCuentas.getInt("number"));
				cuentas.add(e);
			}
		} catch (DaoException e) {
			throw new DaoException();
		}
		return cuentas;
	}
	//sin implementar
	public User updatePassword(String usuario, String password)
			throws NoDataFoundException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	//implementado
	public User registerUser(String email,String facebook,String name,String password) throws DaoException{
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String stat = "INSERT INTO `noLines`.`user` (`name`, `email`, `password`, `create_time`) VALUES ('"+name+"', '"+email+"', PASSWORD('"+password+"'), NOW());";
		try {
			jdbc.modify(stat);
			User user= new User();
			user.setEMail(email);
			user.setFacebook(facebook);
			user.setPassword(password);
			user.setUserName(name);
			return user;
		} catch (DaoException e) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
	}
	//sin implementar
	public void setAccountNumber(String user,String password,int account){
		
	}
	

	public User validateLogin(String email, String password) throws DaoException, NoDataFoundException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		User user = new User();
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String getUser = "SELECT * FROM user WHERE user.email='"+email+"' AND user.password=PASSWORD('"+password+"')";
		ResultSet rsUser = jdbc.select(getUser);
		try{
			if (rsUser.next()){
				user= loadUser(rsUser,jdbc);
			}else{
				throw new NoDataFoundException();
			}
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
		return user;
	}

	public static void main(String[] args){
	UserDaoDB prueba = new UserDaoDB();
	try {
		User pruebito =prueba.findByEMail("jt.tejeria@gmail.com");
		System.out.println(pruebito.getUserName());
		System.out.println(pruebito.getEMail());
		System.out.println(pruebito.getAccount().size());
		System.out.println(pruebito.getAccount().get(0).getNumber());
		
		User ingreso = prueba.registerUser("pipin@gmail.com", "pipin@gmail.com", "pipe", "minombre");
		System.out.println(ingreso.getUserName());
		System.out.println(ingreso.getEMail());
		
		User valido = prueba.validateLogin("pipin@gmail.com","minombre");
		System.out.println("valido correcto si piping@gmail.com = "+valido.getEMail());
	} catch (NoDataFoundException | DaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
}


}
