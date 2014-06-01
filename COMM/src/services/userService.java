package services;

import java.util.List;

public interface userService {

	public void login(String username, String password);
	public void logout(String username);
	public void register(String username, String password, String name, String fbAccount);
	public void locate(String username); //aca seguramente no sea void, devuelva algun tipo de coordeneadas, investigar
	public void saveParking(String place);
	public String showParking();
	public List showVouchers();
	public void changePassword(String username, String oldPassword, String newPassword);
	public void rankPlate(String idPlate, int ranking);
	public void rankRestauran(String idRestaurant, int ranking);
	public void addLike();
	public void removeLike();
	public List getVoucher(); //quizas devuelve un voucherVO??
	void order(String idRestaurant, List plates, List amounts);


}
