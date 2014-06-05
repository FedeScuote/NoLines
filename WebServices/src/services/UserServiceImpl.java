package services;

import java.util.LinkedList;
import java.util.List;

import entity.Order;
import entity.Restaurant;
import entity.Shop;
import entity.voucher;
import entity.exception.PersistException;
import entity.exception.voucherErrorException;

public class UserServiceImpl implements userService {

	@Override
	public void login(String username, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(String username, String password, String name,
			String fbAccount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void locate(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveParking(String place) {
		// TODO Auto-generated method stub

	}

	@Override
	public String showParking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List showVouchers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String username, String oldPassword,
			String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rankPlate(String idPlate, int ranking) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rankRestauran(String idRestaurant, int ranking) {
		// TODO Auto-generated method stub

	}

	@Override
	public void order(String idRestaurant, List plates, List amounts) {
			Order order = new Order();
			order.setIdReastaurant(idRestaurant);
			order.setItems(plates);
			order.setAmounts(amounts);
			try {
				order.persistOrder();
			} catch (PersistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void addLike() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeLike() {
		// TODO Auto-generated method stub

	}

	@Override
	public voucher getVoucher(String user) {
		 Shop shopRandom = getRandomShop();
		 try {
			voucher voucherNew = voucher.createVoucherForUser(user, shopRandom.getId());
			return voucherNew;
		} catch (voucherErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	private Shop getRandomShop(){
		int indiceRandom;
		List shops = Shop.getAllShops();
		indiceRandom = (int)(Math.random()*(shops.size()));
		return (Shop) shops.get(indiceRandom);
	}
	 public static void main(String[] args){
		 String plato1 = "1";
		 String plato2 = "2";
		 String am1= "3";
		 String am2= "6";
		 String rest="1";
		 List platos = new LinkedList<>();
		 List cants = new LinkedList<>();
		 UserServiceImpl userServiceImpl = new UserServiceImpl();
		 userServiceImpl.order(rest, platos, cants);
		 
	 }

}
