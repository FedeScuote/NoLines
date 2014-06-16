package services;

import java.util.LinkedList;
import java.util.List;

import dao.exception.NoDataFoundException;
import entity.Order;
import entity.Restaurant;
import entity.Shop;
import entity.User;
import entity.voucher;
import entity.exception.PersistException;
import entity.exception.loginException;
import entity.exception.voucherErrorException;

public class UserServiceImpl implements userService {

	@Override
	public User login(String username, String password) throws NoDataFoundException, loginException {
		return (User.login(username, password));
	}

	@Override
	public void logout(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean register(String username, String password, String name,
			String fbAccount) {
		User user = new User();
		user.setEMail(username);
		user.setPassword(password);
		user.setName(name);
		user.setFacebook(fbAccount);
		return(User.register(user));

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
	public List showVouchers(String user) {
		List listVouchers;
		try {
			listVouchers=voucher.showVouchers(user);
			return listVouchers;
		} catch (voucherErrorException e) {
			e.printStackTrace();
			return new LinkedList<>();
		}
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
	public void order(String idRestaurant, List plates, List amounts, String idUser) {
			Order order = new Order();
			order.setIdReastaurant(idRestaurant);
			order.setItems(plates);
			order.setAmounts(amounts);
			order.setIdUser(idUser);
			try {
				order.persistOrder();
			} catch (PersistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public List getLikedRestaurants(String user) {
		return Shop.getLikedRestaurants(user);
	}
	public List getUnLikedRestaurants(String user) {
		return Shop.getUnLikedRestaurants(user);
	}
	@Override
	public void addLike(String user,int idLocal) {
		User.like(user, idLocal);

	}

	@Override
	public void removeLike(String user,int idLocal) {
		User.disLike(user, idLocal);

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
		 UserServiceImpl userServiceImpl = new UserServiceImpl();
		 /*	 String plato1 = "1";
		 String plato2 = "2";
		 String am1= "3";
		 String am2= "6";
		 String rest="1";
		 List platos = new LinkedList<>();
		 List cants = new LinkedList<>();
		 userServiceImpl.order(rest, platos, cants);*/
		// userServiceImpl.register("pepegrillo@adinet.com.uy","pepepepito", "Ing. Pepe Grillo", "http://facebook.com/elGrilloDelCap");
		 try {
			User user = userServiceImpl.login("pepegrillo@adinet.com.uy", "pop");
			System.out.println(user.getEMail());
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (loginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

}
