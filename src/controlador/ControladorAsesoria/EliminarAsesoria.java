package controlador.ControladorAsesoria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AsesoriaDao;
import modelo.Asesoria;

/**
 * Servlet implementation class EliminarAsesoria
 */
@WebServlet("/EliminarAsesoria")
public class EliminarAsesoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarAsesoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int asesoriaid= Integer.parseInt(request.getParameter("id"));
		Asesoria asesoria = new Asesoria();
		asesoria.setId_asesoria(asesoriaid);
		
		AsesoriaDao asesoriadao = new AsesoriaDao();
		boolean elimino = asesoriadao.eliminarAsesoria(asesoria);
		
		List<Asesoria> listadoeliminar = new ArrayList<Asesoria>();
		listadoeliminar = asesoriadao.leerAsesoria();
		
		
		String mensaje = "";
		
		if (elimino)
			mensaje = "La asesoria ha sido eliminado exitosamente";
		else
			mensaje = "Ocurrio un problema  al eliminar la asesoria";
		request.setAttribute("cumensaje", mensaje);
		request.setAttribute("listadoasesorias", listadoeliminar);
//		request.getRequestDispatcher("ListarAsesorias").forward(request, response);
		request.getRequestDispatcher("CrearAsesoria").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
