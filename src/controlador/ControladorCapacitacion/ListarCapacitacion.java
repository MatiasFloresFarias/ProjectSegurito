package controlador.ControladorCapacitacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CapacitacionDao;
import modelo.Capacitacion;

/**
 * Servlet implementation class ListarCapacitacion
 */
@WebServlet("/ListarCapacitacion")
public class ListarCapacitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCapacitacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CapacitacionDao capacitaciondao = new CapacitacionDao();
		List<Capacitacion> lcapacitaciones = new ArrayList<Capacitacion>();
		
		lcapacitaciones = capacitaciondao.leerCapacitacion();
			
		request.setAttribute("listadocapacitaciones",lcapacitaciones);
		request.getRequestDispatcher("capacitacionCliente.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}