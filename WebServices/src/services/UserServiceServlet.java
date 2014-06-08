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

/**
 * Servlet implementation class UserServiceServlet
 */
@WebServlet("/UserServiceServlet")
public class UserServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html");
		UserServiceImpl userServiceImpl = new UserServiceImpl();
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
	}

}
