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
import entity.User;

public class UserDaoDB implements UserDao {
	private static final String RESOURCE_NAME_DAO_PROPERTIES = "conf.dao_properties";

	//sin implementar
	public LinkedList<User> obtenerTodos() throws NoDataFoundException,
			DaoException {
		// TODO Auto-generated method stub
		return null;
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
	//sin implementar
	public User registerUser(String email,String facebook,String name,String password){
		return null;
	}
public static void main(String[] args){
	UserDaoDB prueba = new UserDaoDB();
	try {
		User pruebito =prueba.findByEMail("jt.tejeria@gmail.com");
		System.out.println(pruebito.getUserName());
		System.out.println(pruebito.getEMail());
		System.out.println(pruebito.getAccount().size());
		System.out.println(pruebito.getAccount().get(0).getNumber());
	} catch (NoDataFoundException | DaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
}

}
