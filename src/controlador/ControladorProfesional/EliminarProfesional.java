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
 * Servlet implementation class EliminarProfesional
 */
@WebServlet("/EliminarProfesional")
public class EliminarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProfesional() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int profesionalid= Integer.parseInt(request.getParameter("id"));
		Profesional profesional = new Profesional();
		profesional.setId_profesional(profesionalid);;
		
		ProfesionalDao profesionaldao = new ProfesionalDao();
		boolean elimino = profesionaldao.eliminarProfesional(profesional);
		
		List<Profesional> listadoeliminar = new ArrayList<Profesional>();
		listadoeliminar = profesionaldao.leerProfesional();
		
		
		String mensaje = "";
		
		if (elimino) {
			mensaje = "El profesional ha sido eliminado exitosamente";
		} else {
			if (ProfesionalDao.integridad) {
				mensaje = "Error de integridad, esta tratando de eliminar un registro con campos secundarios asociados";
				ProfesionalDao.integridad = false;
			} else {
				mensaje = "Ocurrio un problema  al eliminar el profesional";
			}
		}
		request.setAttribute("cumensaje", mensaje);
		request.setAttribute("listadoprofesionales", listadoeliminar);
		request.getRequestDispatcher("CrearProfesional").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
