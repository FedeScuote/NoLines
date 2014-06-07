package dao;

import java.util.List;

import dao.exception.DaoException;
import entity.voucher;

public interface VoucherDao {

	//Crear un nuevo voucher for a single user.
	public voucher createVoucherForUser(String email,int idLocal) throws DaoException;
	
	//Crea un nuevo voucher para todos los usuarios.
	public void createVoucher(int idLocal) throws DaoException;
	
	//Valida un voucher y lo marca como utilizado.
	public boolean validateVoucher(String email,int idVoucher) throws DaoException;
	
	//Obtiene una lista de los vouchers activos de un usuario. Devuelve la lista vacia en caso de no tener vouchers.
	public List<voucher> getVouchers(String email) throws DaoException;
}
