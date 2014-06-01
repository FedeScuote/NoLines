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
	public void createVoucherForUser(String email, int idLocal) throws DaoException {
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
	}

	private double calculateDiscount() {
		//devuelve un numero random entre 10 y 20.
		double random=(Math.random()*10)+10;
		return random;
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
			} finally {
				conexion.disconnect();
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
					if(rsVoucher.getDate("used_time").equals(null) && rsVoucher.getDate("expiration_date").after(sqlDate)){
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
		String getVouchers = "SELECT * FROM voucher WHERE voucher.email ='"+email+"' AND voucher.used_time =NULL AND voucher.expiration_date < '"+sqlDate+"'";
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
