package controlador.ControladorActividadesMejora;

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

import dao.ActividadesMejoraDao;
import dao.ClienteDao;
import dao.ProfesionalDao;
import modelo.ActividadesMejora;
import modelo.Cliente;
import modelo.Profesional;

/**
 * Servlet implementation class EditarActividadesMejora
 */
@WebServlet("/EditarActividadesMejora")
public class EditarActividadesMejora extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarActividadesMejora() {
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
		
		
		int actmejoraid = Integer.parseInt(request.getParameter("id"));
		
		ActividadesMejoraDao actmejoradao = new ActividadesMejoraDao();
		ActividadesMejora actmejora = new ActividadesMejora();
		actmejora = actmejoradao.obtenerActividad(actmejoraid);
		
		//transformo las fechas pa q se vean en el mismo formato q acepta sql
		String fecha1 = actmejora.getFechaInicio();
		LocalDateTime datetime = LocalDateTime.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fechainicio = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		actmejora.setFechaInicio(fechainicio);
		
		String fecha2 = actmejora.getFechaTermino();
		LocalDateTime datetime1 = LocalDateTime.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fechatermino = datetime1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		actmejora.setFechaTermino(fechatermino);
		
		
		
		request.setAttribute("datosactmejora", actmejora);
		request.getRequestDispatcher("actividadesMejoraEditar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("txtnombre");
		String fechainicio  = request.getParameter("txtfechainicio");
		String fechatermino  = request.getParameter("txtfechatermino");
		String estado = request.getParameter("txtestado");
		String detalle = request.getParameter("txtdetalle");
		int id_profesional = Integer.parseInt(request.getParameter("txt_idprofesional"));
		int id_cliente = Integer.parseInt(request.getParameter("txt_idcliente"));
		int id = Integer.parseInt(request.getParameter("hdnidactmejora"));

		ActividadesMejora actmejora = new ActividadesMejora(id,nombre,fechainicio, fechatermino, estado, detalle, id_profesional, id_cliente);
		
		ActividadesMejoraDao actmejoradao = new ActividadesMejoraDao();
		boolean editar = actmejoradao.actualizarActividad(actmejora);
		
		String mensaje = "";
		
		if (editar)
			mensaje = "La Actividad de mejora se ha editado exitosamente";
		else
			mensaje = "Ocurrio un error al editar la Actividad de mejora";

		request.setAttribute("datosactmejora", actmejora);
//		request.setAttribute("cumensaje", mensaje);
		
			response.sendRedirect("CrearActividadesMejora?emensaje="+ mensaje);	
	}

}
