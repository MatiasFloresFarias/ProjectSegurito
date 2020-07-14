
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
 * Servlet implementation class EliminarActividadesMejora
 */
@WebServlet("/EliminarActividadesMejora")
public class EliminarActividadesMejora extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarActividadesMejora() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int actmejoraid= Integer.parseInt(request.getParameter("id"));
		ActividadesMejora actmejora = new ActividadesMejora();
		actmejora.setIdActMejora(actmejoraid);
		
		ActividadesMejoraDao actmejoradao = new ActividadesMejoraDao();
		boolean elimino = actmejoradao.eliminarActividad(actmejora);
		
		List<ActividadesMejora> listadoeliminar = new ArrayList<ActividadesMejora>();
		listadoeliminar = actmejoradao.leerActividad();
		
		
		String mensaje = "";
		
		if (elimino)
			mensaje = "La actividad de mejora ha sido eliminado exitosamente";
		else
			mensaje = "Ocurrio un problema  al eliminar la actividad de mejora";
		request.setAttribute("cumensaje", mensaje);
		request.setAttribute("listadoactmejora", listadoeliminar);
		request.getRequestDispatcher("CrearActividadesMejora").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
