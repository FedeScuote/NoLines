package DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import jdbc.AccesoJDBC;
import jdbc.ConexionDB;
import jdbc.NoDatabaseConexionException;
import dao.VoucherDao;
import dao.exception.DaoException;
import entity.Restaurant;
import entity.voucher;

public class VoucherDaoDB implements VoucherDao{
	
	//Creates a unique voucher for only one user.
	public voucher createVoucherForUser(String email, int idLocal) throws DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String stat = "INSERT INTO `noLines`.`voucher` (`voucher_id`, `discount`, `create_time`, `used_time`, `expiration_date`, `id_local`, `email`) VALUES (NULL, '"+calculateDiscount()+"', NOW(), NULL, NOW() + INTERVAL 1 HOUR, '"+idLocal+"', '"+email+"');";
		try {
			jdbc.modify(stat);
		} catch (DaoException e) {
			throw new DaoException();
		}
		voucher vo= new voucher();
		String stat2="SELECT * from voucher WHERE email='"+email+"' ORDER BY(create_time) DESC LIMIT 1";
		ResultSet rsVouchers = jdbc.select(stat2);
		try {
			if(rsVouchers.next()){
				vo=loadVoucher(rsVouchers);
			}
		} catch (SQLException e) {
			throw new DaoException();
		}finally{
			conexion.disconnect();
		}
		return vo;
	}

	private double calculateDiscount() {
		//devuelve un numero random entre 10 y 20.
		double random=(Math.random()*10)+10;
		int numero = (int) Math.round(random);
		return (numero);
	}

	//Creates a voucher for each user.
	public void createVoucher(int idLocal) throws DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		LinkedList<String> emails = getEmails(jdbc);
		double discount = calculateDiscount();
		for(int i=0;i<emails.size();i++){
			String stat = "INSERT INTO `noLines`.`voucher` (`voucher_id`, `discount`, `create_time`, `used_time`, `expiration_date`, `id_local`, `email`) VALUES (NULL, '"+discount+"', NOW(), NULL, NOW() + INTERVAL 1 HOUR, '"+idLocal+"', '"+emails.get(i)+"');";
			try {
				jdbc.modify(stat);
			} catch (DaoException e) {
				throw new DaoException();
			}
		}
	}
	
	//Obtiene de la base de datos una lista de los emails de los usuarios registrados.
	private LinkedList<String> getEmails(AccesoJDBC jdbc) throws DaoException {
		String stat = "SELECT email FROM `user`";
		LinkedList<String> emails= new LinkedList<String>();
		try {
			ResultSet rsEmails = jdbc.select(stat);
			try {
				while(rsEmails.next()){
					emails.add(rsEmails.getString("email"));
				}
			} catch (SQLException e) {
				throw new DaoException();
			}
		} catch (DaoException e) {
			throw new DaoException();
		}
		return emails;
	}

	//Validates if the user has that voucher active, if it is, it will be used and the use date will be set.
	public boolean validateVoucher(String email,int idVoucher) throws DaoException {
		boolean resultado=false;
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		String stat = "SELECT * FROM `voucher` WHERE voucher.voucher_id = '"+idVoucher+"'";
		try {
			ResultSet rsVoucher = jdbc.select(stat);
			try {
				if(rsVoucher.next()){
					Date date=new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					try{
						if(rsVoucher.getString("used_time").equals(null)){
							//Hago tirar la nullpointer exception en caso de que used time sea null en la base de datos.
						}
					}catch(NullPointerException e){
						if(rsVoucher.getString("email").equals(email)){
							String modify="UPDATE  `noLines`.`voucher` SET  `used_time` = NOW( ) WHERE  `voucher`.`voucher_id` ='"+idVoucher+"'";
							jdbc.modify(modify);
							resultado=true; //valido pues no fue utilizado y aun no vencio.
						}
					}
				}
			} catch (SQLException e) {
				throw new DaoException();
			}
		} catch (DaoException e) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
		return resultado;
	}


	public LinkedList<voucher> getVouchers(String email) throws DaoException {
		ConexionDB conexion = new ConexionDB();
		conexion.connect();
		AccesoJDBC jdbc = null;
		try {
			jdbc = conexion.getAccesoJDBC();
		} catch (NoDatabaseConexionException ex) {
			throw new DaoException();
		}
		Date date= new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		int hora = date.getHours();
		int minutos = date.getMinutes();
		int segundos = date.getSeconds();
		String getVouchers = "SELECT * FROM voucher WHERE voucher.email ='"+email+"' AND voucher.used_time IS NULL AND voucher.expiration_date > '"+sqlDate+" "+hora+":"+minutos+":"+segundos+"'";
		ResultSet rsVouchers = jdbc.select(getVouchers);
		LinkedList<voucher> vouchers = new LinkedList<voucher>();
		try{
			while (rsVouchers.next()){
				vouchers.add(loadVoucher(rsVouchers));		
			}
			return vouchers;
		}catch (SQLException ex) {
			throw new DaoException();
		} finally {
			conexion.disconnect();
		}
	}

	private voucher loadVoucher(ResultSet rsVouchers) throws SQLException {
		voucher vo = new voucher();
		vo.setDiscount(rsVouchers.getDouble("discount"));
		vo.setExpirationTime(rsVouchers.getDate("expiration_date"));
		vo.setGeneratedTime(rsVouchers.getDate("create_time"));
		vo.setId(rsVouchers.getInt("voucher_id"));
		vo.setRestaurantId(rsVouchers.getInt("id_local"));
		return vo;
	}

}
