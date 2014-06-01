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
		if(request.getParameter("ws").equals("1")){
			LinkedList<Restaurant> lista = (LinkedList<Restaurant>) restaurantService.getRestaurants();
			JSONArray wrapper = new JSONArray();
			for (int i = 0; i < lista.size(); i++) {
				JSONObject restaurant = new JSONObject();
				restaurant.put("id", lista.get(i).getId());
				restaurant.put("name", lista.get(i).getName());
				restaurant.put("logo", lista.get(i).getLogo());
				restaurant.put("horario", lista.get(i).getHorario());
				restaurant.put("location", lista.get(i).getLocation());
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
