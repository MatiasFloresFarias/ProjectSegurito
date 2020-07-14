package controlador.ControladorProfesional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProfesionalDao;
import modelo.Profesional;

/**
 * Servlet implementation class ListarProfesional
 */
@WebServlet("/ListarProfesional")
public class ListarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarProfesional() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfesionalDao profesionaldao = new ProfesionalDao();
		List<Profesional> lprofesionales = new ArrayList<Profesional>();
		
		lprofesionales = profesionaldao.leerProfesional();
			
		request.setAttribute("listadoprofesionales",lprofesionales);
		request.getRequestDispatcher("profesionalListado.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
