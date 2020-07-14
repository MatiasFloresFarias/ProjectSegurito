package controlador.ControladorAsesoria;

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
import javax.servlet.http.HttpSession;

import dao.AsesoriaDao;
import dao.ClienteDao;
import dao.ProfesionalDao;
import modelo.Asesoria;
import modelo.Cliente;
import modelo.Profesional;
import modelo.Usuario;


/**
 * Servlet implementation class EditarAsesoria
 */
@WebServlet("/EditarAsesoria")
public class EditarAsesoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAsesoria() {
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
		
		
		int asesoriaid = Integer.parseInt(request.getParameter("id"));
		
		AsesoriaDao asesoriadao = new AsesoriaDao();
		Asesoria asesoria = new Asesoria();
		asesoria = asesoriadao.obtenerAsesoria(asesoriaid);
		
		//transformo las fechas pa q se vean en el mismo formato q acepta sql
		String fechayhora1 = asesoria.getFechayhora();
		LocalDateTime datetime = LocalDateTime.parse(fechayhora1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fechayhora = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		asesoria.setFechayhora(fechayhora);
		
		
		request.setAttribute("datosasesoria", asesoria);
		request.getRequestDispatcher("asesoriaEditar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String fechayhora = request.getParameter("txtfechayhora");
		String motivo  = request.getParameter("txtmotivo");
		String detalle = request.getParameter("txtdetalle");
		int id_profesional = Integer.parseInt(request.getParameter("txt_idprofesional"));
		int id_cliente = Integer.parseInt(request.getParameter("txt_idcliente"));
		int id = Integer.parseInt(request.getParameter("hdnidasesoria"));

		Asesoria asesoria = new Asesoria(id,fechayhora,motivo,detalle, id_profesional, id_cliente);
		
		AsesoriaDao asesoriadao = new AsesoriaDao();
		boolean editar = asesoriadao.actualizarAsesoria(asesoria);
		
		String mensaje = "";
		
		if (editar)
			mensaje = "La Asesoria se ha editado exitosamente";
		else
			mensaje = "Ocurrio un error al editar la asesoria";

		request.setAttribute("datosasesoria", asesoria);
		request.setAttribute("cumensaje", mensaje);
		
//		//obtiene rol
//		HttpSession misession= (HttpSession) request.getSession(); 
//		Usuario suser= (Usuario) misession.getAttribute("sesionuser");
//		String rol= suser.getRol();
////		System.out.println(rol);
//		if  (rol == "cliente") {
//			request.getRequestDispatcher("ListarAsesorias").forward(request, response);	
//		} else {
			response.sendRedirect("CrearAsesoria?emensaje="+ mensaje);
//		}
		
		
	}

}
