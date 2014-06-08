package services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Restaurant;
import entity.voucher;

/**
 * Servlet implementation class UserServiceServlet
 */
@WebServlet("/UserServiceServlet")
public class UserServiceServlet extends HttpServlet {
	@javax.inject.Inject
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		final java.io.Writer writer = response.getWriter();
		if(request.getParameter("ws").equals("4")){ //el web service 4
			voucher v= userServiceImpl.getVoucher("jt.tejeria@gmail.com"); //lo hago siempre con el mismo usuatio, cambiar, investigar stateless y statefull 
			JSONObject voucherJson = loadVoucherJson(v);
			writer.append(voucherJson.toJSONString());
		}else if(request.getParameter("ws").equals("5")){
			List listVouchers=userServiceImpl.showVouchers();
			JSONArray wrapper = new JSONArray();
			for (int i = 0; i < listVouchers.size(); i++) {
				JSONObject voucher = loadVoucherJson((voucher) listVouchers.get(i));
				wrapper.add(voucher);
			}
			writer.append(wrapper.toJSONString());
		}

	}
	
	private JSONObject loadVoucherJson(voucher v){
		JSONObject vjson = new JSONObject();
		vjson.put("id", v.getId());
		vjson.put("discount", v.getDiscount());
		vjson.put("generetedTime", v.getGeneratedTime());
		vjson.put("expirationTime", v.getExpirationTime());
		vjson.put("shopId", v.getShopId());
		vjson.put("usedTime", v.getUsedTime());
		return vjson;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html");
		if(request.getParameter("ws").equals("3")){
			String platito=request.getParameter("plato");
			String cantidades=request.getParameter("cantidad");
			String[] plato= platito.split(",");
			String[] cantidad= cantidades.split(",");
			String idRestaurant = request.getParameter("idRest");
			List plates = new LinkedList<>();
			List cants = new LinkedList<>();

			final java.io.Writer writer = response.getWriter();
			for (int i = 0; i < plato.length; i++) {
				if(plates.contains(plato[i])){
					int indice = plates.indexOf(plato[i]);
					cants.set(indice, new Integer(Integer.parseInt(cants.get(indice).toString())+1));
				}else{
					plates.add(plato[i]);
					cants.add(cantidad[i]);	
					}
			}
			userServiceImpl.order(idRestaurant, plates, cants);
		}else if(request.getParameter("ws").equals("6")){
			//registro
		}else if(request.getParameter("ws").equals("7")){
			//login
		}

	}

}
