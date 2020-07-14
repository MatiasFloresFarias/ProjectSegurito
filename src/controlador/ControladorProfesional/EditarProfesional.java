package controlador.ControladorProfesional;

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

import dao.ProfesionalDao;
import modelo.Profesional;

/**
 * Servlet implementation class EditarProfesional
 */
@WebServlet("/EditarProfesional")
public class EditarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarProfesional() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int profesionalid = Integer.parseInt(request.getParameter("id"));
		
		ProfesionalDao profesionaldao = new ProfesionalDao();
		Profesional profesional = new Profesional();
		profesional = profesionaldao.obtenerProfesional(profesionalid);
		
		request.setAttribute("datosprofesional", profesional);
		request.getRequestDispatcher("profesionalEditar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String nombre = request.getParameter("txtnombre");
		String apellido  = request.getParameter("txtapellido");
		String correo = request.getParameter("txtcorreo");
		String telefono = request.getParameter("txttelefono");
		String cargo = request.getParameter("txtcargo");
		int id = Integer.parseInt(request.getParameter("hdnidprofesional"));

		Profesional profesional = new Profesional(id,nombre,apellido,correo, telefono, cargo);
		
		ProfesionalDao profesionaldao = new ProfesionalDao();
		boolean editar = profesionaldao.actualizarProfesional(profesional);
		
		String mensaje = "";
		
		if (editar)
			mensaje = "El profesional se ha editado exitosamente";
		else
			mensaje = "Ocurrio un error al editar el profesional";

		request.setAttribute("datosprofesional", profesional);
		request.setAttribute("cumensaje", mensaje);
		
//		//obtiene rol
//		HttpSession misession= (HttpSession) request.getSession(); 
//		Usuario suser= (Usuario) misession.getAttribute("sesionuser");
//		String rol= suser.getRol();
////		System.out.println(rol);
//		if  (rol == "cliente") {
//			request.getRequestDispatcher("ListarAsesorias").forward(request, response);	
//		} else {
			response.sendRedirect("CrearProfesional?emensaje="+ mensaje);
//		}
		
		
	}

}

