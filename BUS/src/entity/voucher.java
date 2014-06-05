package entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dao.LocalDao;
import dao.VoucherDao;
import dao.exception.DaoException;
import dao.exception.NoDataFoundException;
import entity.exception.getMenuException;
import entity.exception.voucherErrorException;

public class voucher {
	int id;
	double discount;
	Date generatedTime;
	Date usedTime;
	Date expirationTime;
	int restaurantId;

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}

	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	public static final voucher createVoucherForUser(String email,int idLocal) throws voucherErrorException{
		try {
			voucher v = new voucher();
			VoucherDao voucherDao = (VoucherDao) Class.forName("DaoImpl.VoucherDaoDB").newInstance();
			v = voucherDao.createVoucherForUser(email, idLocal);
			return v;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new voucherErrorException();
		}
	}

}
