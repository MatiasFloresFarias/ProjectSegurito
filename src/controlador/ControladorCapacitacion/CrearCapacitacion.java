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
import dao.ClienteDao;
import dao.ProfesionalDao;
import modelo.Capacitacion;
import modelo.Cliente;
import modelo.Profesional;

/**
 * Servlet implementation class CrearCapacitacion
 */
@WebServlet("/CrearCapacitacion")
public class CrearCapacitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearCapacitacion() {
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
		
		//mostrarlista
		List<Capacitacion> lcapacitaciones = new ArrayList<Capacitacion>();
		CapacitacionDao capacitaciondao = new CapacitacionDao();
		lcapacitaciones = capacitaciondao.leerCapacitacion();
			
		request.setAttribute("listadocapacitaciones",lcapacitaciones);
		
		
		request.getRequestDispatcher("capacitacionProfesional.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fechayhora = request.getParameter("txtfechayhora");
		String tema = request.getParameter("txttema");
		String contenido = request.getParameter("txtcontenido");
		int id_profesional = Integer.parseInt(request.getParameter("txtid_profesional"));
		int id_cliente = Integer.parseInt(request.getParameter("txtid_cliente"));

		Capacitacion capacitacion = new Capacitacion(fechayhora, tema, contenido, id_profesional, id_cliente);
		
		CapacitacionDao capacitaciondao = new CapacitacionDao();
		boolean agregar = capacitaciondao.crearCapacitacion(capacitacion);
		
		String mensaje = "";
		
		if (agregar)
			mensaje = "La capacitacion se ha creado exitosamente";
		else
			mensaje = "Ocurrio un error al crear la capacitacion";
			
		request.setAttribute("cumensaje", mensaje);
		
		//mostrarlista
		List<Capacitacion> lcapacitaciones = new ArrayList<Capacitacion>();
		
		lcapacitaciones = capacitaciondao.leerCapacitacion();
			
		request.setAttribute("listadocapacitaciones",lcapacitaciones);
		
		doGet(request,response);		
	}

}
