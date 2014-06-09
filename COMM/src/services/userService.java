package services;

import java.util.List;

import dao.exception.NoDataFoundException;
import entity.User;
import entity.voucher;
import entity.exception.loginException;

public interface userService {

	public User login(String username, String password) throws NoDataFoundException, loginException;
	public void logout(String username);
	public boolean register(String username, String password, String name, String fbAccount);
	public void locate(String username); //aca seguramente no sea void, devuelva algun tipo de coordeneadas, investigar
	public void saveParking(String place);
	public String showParking();
	public void changePassword(String username, String oldPassword, String newPassword);
	public void rankPlate(String idPlate, int ranking);
	public void rankRestauran(String idRestaurant, int ranking);
	public void addLike();
	public void removeLike();
	public voucher getVoucher(String user);
	List showVouchers(String user);
	void order(String idRestaurant, List plates, List amounts, String idUser);


}
