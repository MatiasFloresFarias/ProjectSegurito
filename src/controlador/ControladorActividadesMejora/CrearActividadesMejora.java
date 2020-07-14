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
import dao.ClienteDao;
import dao.ProfesionalDao;
import modelo.ActividadesMejora;
import modelo.Cliente;
import modelo.Profesional;

/**
 * Servlet implementation class CrearActividadesMejora
 */
@WebServlet("/CrearActividadesMejora")
public class CrearActividadesMejora extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearActividadesMejora() {
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
		List<ActividadesMejora> lactmejora = new ArrayList<ActividadesMejora>();
		ActividadesMejoraDao actmejoradao = new ActividadesMejoraDao();
		lactmejora = actmejoradao.leerActividad();
			
		request.setAttribute("listadoactmejora",lactmejora);
		
		
		request.getRequestDispatcher("actividadesMejoraProfesional.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombre = request.getParameter("txtnombre");
		String fechainicio = request.getParameter("txtfechainicio");
		String fechatermino = request.getParameter("txtfechatermino");
		String estado = request.getParameter("txtestado");
		String detalle = request.getParameter("txtdetalle");
		int id_profesional = Integer.parseInt(request.getParameter("txtid_profesional"));
		int id_cliente = Integer.parseInt(request.getParameter("txtid_cliente"));

		ActividadesMejora actmejora = new ActividadesMejora(nombre,fechainicio, fechatermino, estado, detalle, id_profesional, id_cliente);
		
		ActividadesMejoraDao actmejoradao = new ActividadesMejoraDao();
		boolean agregar = actmejoradao.crearActividad(actmejora);
		
		String mensaje = "";
		
		if (agregar)
			mensaje = "La actividad de mejora se ha creado exitosamente";
		else
			mensaje = "Ocurrio un error al crear la actividad de mejora";
			
		request.setAttribute("cumensaje", mensaje);
		
		//mostrarlista
		List<ActividadesMejora> lactmejora = new ArrayList<ActividadesMejora>();
		
		lactmejora = actmejoradao.leerActividad();
			
		request.setAttribute("listadoactmejora",lactmejora);
		
		doGet(request,response);		
	}

}
