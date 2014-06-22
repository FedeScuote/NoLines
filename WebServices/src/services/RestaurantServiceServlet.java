package services;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Plate;
import entity.Restaurant;
import entity.Shop;
import entity.voucher;

/**
 * Servlet implementation class RestaurantServiceServlet
 */
@WebServlet("/RestaurantServiceServlet")
public class RestaurantServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @javax.inject.Inject
    private RestaurantServiceImpl restaurantService = new RestaurantServiceImpl();
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		final java.io.Writer writer = response.getWriter();
		if(request.getParameter("ws").equals("1")){ //Lista de restaurantes 
			String category=request.getParameter("category");
			LinkedList<Restaurant> lista;
			if(category.equals("0")){
				lista = (LinkedList<Restaurant>) restaurantService.getRestaurants();	
			}else if(category.equals("5")){
				lista=(LinkedList<Restaurant>) restaurantService.getLikedRestaurants(request.getParameter("user"));
			}else{
				lista = (LinkedList<Restaurant>) restaurantService.getRestaurantsByCategory(Integer.parseInt(category));
			}
			JSONArray wrapper = new JSONArray();
			for (int i = 0; i < lista.size(); i++) {
				JSONObject restaurant = loadRestaurantJson(lista.get(i));
				wrapper.add(restaurant)	;
			}
			writer.append(wrapper.toJSONString());
		}else if(request.getParameter("ws").equals("2")){
			String idRestaurant = request.getParameter("idRestaurant");
			LinkedList<Plate> menu = (LinkedList<Plate>) restaurantService.getMenu(Integer.parseInt(idRestaurant));
			JSONArray wrapper = new JSONArray();
			for (int i = 0; i < menu.size(); i++) {
				JSONObject plate = new JSONObject();
				plate.put("id", menu.get(i).getId());
				plate.put("name", menu.get(i).getName());
				plate.put("description", menu.get(i).getDescription());
				plate.put("picture", menu.get(i).getPicture());
				plate.put("price", menu.get(i).getPrice());
				plate.put("time", menu.get(i).getTime());
				wrapper.add(plate);
			}
			writer.append(wrapper.toJSONString());
		}
	}
	
	/**
	 * Metodo para crear un restaurante json
	 * @param r restaurant entity
	 * @return json conteniendo restaurant
	 */
	private JSONObject loadRestaurantJson(Restaurant r){
		JSONObject restaurant = new JSONObject();
		restaurant.put("id", r.getId());
		restaurant.put("name", r.getName());
		restaurant.put("logo", r.getLogo());
		restaurant.put("horario", r.getHorario());
		restaurant.put("location", r.getLocation());
		restaurant.put("description",r.getDescription());
		return restaurant;
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

