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
import dao.ClienteDao;
import modelo.ReporteAccidente;
import modelo.Cliente;


/**
 * Servlet implementation class CrearReporte
 */
@WebServlet("/CrearReporte")
public class CrearReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ClienteDao clientedao = new ClienteDao();
		List<Cliente> lclientes = new ArrayList<Cliente>();
		
		lclientes = clientedao.leerCliente();
		
		request.setAttribute("listadoclientes",lclientes);
		
		//mostrarlista
		List<ReporteAccidente> lreporteaccidentes = new ArrayList<ReporteAccidente>();
		ReporteAccidenteDao reporteaccidentedao = new ReporteAccidenteDao();
		lreporteaccidentes = reporteaccidentedao.leerReporteAccidente();
			
		request.setAttribute("listadoreporteaccidentes",lreporteaccidentes);
		
		
		request.getRequestDispatcher("accidenteCliente.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fecha = request.getParameter("txtfecha");
		String direccion = request.getParameter("txtdireccion");
		String labor = request.getParameter("txtlabor");
		String descripcion = request.getParameter("txtdescripcion");
		int id_cliente = Integer.parseInt(request.getParameter("txtid_cliente"));

		ReporteAccidente reporteaccidente = new ReporteAccidente(fecha, direccion, labor, descripcion, id_cliente);
		
		ReporteAccidenteDao reporteaccidentedao = new ReporteAccidenteDao();
		boolean agregar = reporteaccidentedao.crearReporteAccidente(reporteaccidente);
		
		String mensaje = "";
		
		if (agregar)
			mensaje = "El reporte se ha creado exitosamente";
		else
			mensaje = "Ocurrio un error al crear el reporte";
			
		request.setAttribute("cumensaje", mensaje);
		
		//mostrarlista
		List<ReporteAccidente> lreporteaccidentes = new ArrayList<ReporteAccidente>();
		
		lreporteaccidentes = reporteaccidentedao.leerReporteAccidente();
			
		request.setAttribute("listadoreporteaccidentes",lreporteaccidentes);
		
		doGet(request,response);
		
	}

}
