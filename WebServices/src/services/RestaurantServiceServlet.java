package services;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
		LinkedList<Restaurant> lista = (LinkedList<Restaurant>) restaurantService.getRestaurants();
		JSONArray wrapper = new JSONArray();
		for (int i = 0; i < lista.size(); i++) {
			JSONObject restaurant = new JSONObject();
			restaurant.put("name", lista.get(i).getName());
			restaurant.put("logo", lista.get(i).getLogo());
			restaurant.put("horario", lista.get(i).getHorario());
			restaurant.put("location", lista.get(i).getLocation());
			wrapper.add(restaurant)	;
		}
		writer.append(wrapper.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
