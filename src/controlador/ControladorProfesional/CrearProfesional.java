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
 * Servlet implementation class CrearProfesional
 */
@WebServlet("/CrearProfesional")
public class CrearProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearProfesional() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//mostrarlista
		List<Profesional> lprofesionales = new ArrayList<Profesional>();
		ProfesionalDao profesionaldao = new ProfesionalDao();
		lprofesionales = profesionaldao.leerProfesional();
			
		request.setAttribute("listadoprofesionales",lprofesionales);
		
		request.getRequestDispatcher("profesional.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombre = request.getParameter("txtNombreProfesional");
		String apellido = request.getParameter("txtApellido");
		String correo = request.getParameter("txtCorreo");
		String telefono = request.getParameter("txtTelefono");
		String cargo = request.getParameter("txtCargo");

		Profesional profesional = new Profesional(nombre, apellido, correo, telefono, cargo);
		
		ProfesionalDao profesionaldao = new ProfesionalDao();
		boolean agregar = profesionaldao.crearProfesional(profesional);
		
		String mensaje = "";
		
		if (agregar)
			mensaje = "El profesional se ha creado exitosamente";
		else
			mensaje = "Ocurrio un error al crear al profesional";
			
		request.setAttribute("cumensaje", mensaje);
		
		//mostrarlista
		List<Profesional> lprofesionales = new ArrayList<Profesional>();
		
		lprofesionales = profesionaldao.leerProfesional();
			
		request.setAttribute("listadoprofesionales",lprofesionales);
		
		doGet(request,response);
//		request.getRequestDispatcher("CrearAsesoria").forward(request, response);
		
	}

}

