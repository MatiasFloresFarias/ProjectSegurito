package controlador.ControladorActividadesMejora;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActividadesMejoraDao;
import modelo.ActividadesMejora;


/**
 * Servlet implementation class ListarActividadesMejora
 */
@WebServlet("/ListarActividadesMejora")
public class ListarActividadesMejora extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarActividadesMejora() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActividadesMejoraDao actmejoradao = new ActividadesMejoraDao();
		List<ActividadesMejora> lactmejora = new ArrayList<ActividadesMejora>();
		
		lactmejora = actmejoradao.leerActividad();
			
		request.setAttribute("listadoactmejora",lactmejora);
		request.getRequestDispatcher("actividadesMejoraCliente.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}