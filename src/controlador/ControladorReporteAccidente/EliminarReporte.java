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
 * Servlet implementation class EliminarReporte
 */
@WebServlet("/EliminarReporte")
public class EliminarReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		int reporteaccidenteid= Integer.parseInt(request.getParameter("id"));
		ReporteAccidente reporteaccidente = new ReporteAccidente();
		reporteaccidente.setIdReporteAccidente(reporteaccidenteid);
		
		ReporteAccidenteDao reporteaccidentedao = new ReporteAccidenteDao();
		boolean elimino = reporteaccidentedao.eliminarReporteAccidente(reporteaccidente);
		
		List<ReporteAccidente> listadoeliminar = new ArrayList<ReporteAccidente>();
		listadoeliminar = reporteaccidentedao.leerReporteAccidente();
		
		
		String mensaje = "";
		
		if (elimino)
			mensaje = "El reporte ha sido eliminado exitosamente";
		else
			mensaje = "Ocurrio un problema  al eliminar el reporte";
		request.setAttribute("cumensaje", mensaje);
		request.setAttribute("listadoreporteaccidentes", listadoeliminar);
		request.getRequestDispatcher("CrearReporte").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
