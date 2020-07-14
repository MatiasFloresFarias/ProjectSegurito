package controlador.ControladorReporteAccidente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReporteAccidenteDao;
import modelo.ReporteAccidente;

/**
 * Servlet implementation class ListarReporte
 */
@WebServlet("/ListarReporte")
public class ListarReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ReporteAccidenteDao reporteaccidentedao = new ReporteAccidenteDao();
		List<ReporteAccidente> lreporteaccidentes = new ArrayList<ReporteAccidente>();
		
		lreporteaccidentes = reporteaccidentedao.leerReporteAccidente();
			
		request.setAttribute("listadoreporteaccidentes",lreporteaccidentes);
		request.getRequestDispatcher("accidenteProfesional.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
