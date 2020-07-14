package controlador.ControladorReporteAccidente;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
 * Servlet implementation class EditarReporte
 */
@WebServlet("/EditarReporte")
public class EditarReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarReporte() {
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
		
		
		int reporteaccidenteid = Integer.parseInt(request.getParameter("id"));
		
		ReporteAccidenteDao reporteaccidentedao = new ReporteAccidenteDao();
		ReporteAccidente reporteaccidente = new ReporteAccidente();
		reporteaccidente = reporteaccidentedao.obtenerReporteAccidente(reporteaccidenteid);
		
		//transformo las fechas pa q se vean en el mismo formato q acepta sql
		String fecha1 = reporteaccidente.getFecha();
		LocalDateTime datetime = LocalDateTime.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fecha = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		reporteaccidente.setFecha(fecha);
		
		
		request.setAttribute("datosreporteaccidente", reporteaccidente);
		request.getRequestDispatcher("accidenteEditar.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fecha = request.getParameter("txtfecha");
		String direccion  = request.getParameter("txtdireccion");
		String labor = request.getParameter("txtlabor");
		String descripcion = request.getParameter("txtdescripcion");
		int id_cliente = Integer.parseInt(request.getParameter("txtid_cliente"));
		int id = Integer.parseInt(request.getParameter("hdnidreporteaccidente"));

		ReporteAccidente reporteaccidente = new ReporteAccidente(id,fecha,direccion,labor, descripcion, id_cliente);
		
		ReporteAccidenteDao reporteaccidentedao = new ReporteAccidenteDao();
		boolean editar = reporteaccidentedao.actualizarReporteAccidente(reporteaccidente);
		
		String mensaje = "";
		
		if (editar)
			mensaje = "El reporte se ha editado exitosamente";
		else
			mensaje = "Ocurrio un error al editar el reporte";

		request.setAttribute("datosreporteaccidente", reporteaccidente);
		request.setAttribute("cumensaje", mensaje);
		
		response.sendRedirect("CrearReporte?emensaje="+ mensaje);
		
	}

}
