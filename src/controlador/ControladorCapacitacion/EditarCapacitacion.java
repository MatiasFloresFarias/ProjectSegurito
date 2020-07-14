package controlador.ControladorCapacitacion;

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

import dao.CapacitacionDao;
import dao.ClienteDao;
import dao.ProfesionalDao;
import modelo.Capacitacion;
import modelo.Cliente;
import modelo.Profesional;

/**
 * Servlet implementation class EditarCapacitacion
 */
@WebServlet("/EditarCapacitacion")
public class EditarCapacitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarCapacitacion() {
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
		
		ClienteDao clientedao = new ClienteDao();
		List<Cliente> lclientes = new ArrayList<Cliente>();
		
		lclientes = clientedao.leerCliente();
		
		request.setAttribute("listadoclientes",lclientes);
		
		
		int capacitacionid = Integer.parseInt(request.getParameter("id"));
		
		CapacitacionDao capacitaciondao = new CapacitacionDao();
		Capacitacion capacitacion = new Capacitacion();
		capacitacion = capacitaciondao.obtenerCapacitacion(capacitacionid);
		
		//transformo las fechas pa q se vean en el mismo formato q acepta sql
		String fechayhora1 = capacitacion.getFechayhora();
		LocalDateTime datetime = LocalDateTime.parse(fechayhora1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fechayhora = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		capacitacion.setFechayhora(fechayhora);
		
		
		request.setAttribute("datoscapacitacion", capacitacion);
		request.getRequestDispatcher("capacitacionEditar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fechayhora = request.getParameter("txtfechayhora");
		String tema  = request.getParameter("txttema");
		String contenido = request.getParameter("txtcontenido");
		int id_profesional = Integer.parseInt(request.getParameter("txt_idprofesional"));
		int id_cliente = Integer.parseInt(request.getParameter("txt_idcliente"));
		int id = Integer.parseInt(request.getParameter("hdnidcapacitacion"));

		Capacitacion capacitacion = new Capacitacion(id,fechayhora,tema, contenido, id_profesional, id_cliente);
		
		CapacitacionDao capacitaciondao = new CapacitacionDao();
		boolean editar = capacitaciondao.actualizarCapacitacion(capacitacion);
		
		String mensaje = "";
		
		if (editar)
			mensaje = "La Capacitacion se ha editado exitosamente";
		else
			mensaje = "Ocurrio un error al editar la capacitacion";

		request.setAttribute("datoscapacitacion", capacitacion);
		request.setAttribute("cumensaje", mensaje);
		
			response.sendRedirect("CrearCapacitacion?emensaje="+ mensaje);	
	}

}
