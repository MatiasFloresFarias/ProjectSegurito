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
 * Servlet implementation class EliminarCapacitacion
 */
@WebServlet("/EliminarCapacitacion")
public class EliminarCapacitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCapacitacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int capacitacionid= Integer.parseInt(request.getParameter("id"));
		Capacitacion capacitacion = new Capacitacion();
		capacitacion.setId_capacitacion(capacitacionid);
		
		CapacitacionDao capacitaciondao = new CapacitacionDao();
		boolean elimino = capacitaciondao.eliminarCapacitacion(capacitacion);
		
		List<Capacitacion> listadoeliminar = new ArrayList<Capacitacion>();
		listadoeliminar = capacitaciondao.leerCapacitacion();
		
		
		String mensaje = "";
		
		if (elimino)
			mensaje = "La capacitacion ha sido eliminado exitosamente";
		else
			mensaje = "Ocurrio un problema  al eliminar la capacitacion";
		request.setAttribute("cumensaje", mensaje);
		request.setAttribute("listadocapacitaciones", listadoeliminar);
		request.getRequestDispatcher("CrearCapacitacion").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
