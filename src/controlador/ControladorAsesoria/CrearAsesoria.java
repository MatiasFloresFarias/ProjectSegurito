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
import dao.ClienteDao;
import dao.ProfesionalDao;
import modelo.Asesoria;
import modelo.Cliente;
import modelo.Profesional;

/**
 * Servlet implementation class CrearAsesoria
 */
@WebServlet("/CrearAsesoria")
public class CrearAsesoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearAsesoria() {
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
		List<Asesoria> lasesorias = new ArrayList<Asesoria>();
		AsesoriaDao asesoriadao = new AsesoriaDao();
		lasesorias = asesoriadao.leerAsesoria();
			
		request.setAttribute("listadoasesorias",lasesorias);
		
		
		request.getRequestDispatcher("asesoriaProfesional.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fechayhora = request.getParameter("txtfechayhora");
		String motivo = request.getParameter("txtmotivo");
		String detalle = request.getParameter("txtdetalle");
		int id_profesional = Integer.parseInt(request.getParameter("txtid_profesional"));
		int id_cliente = Integer.parseInt(request.getParameter("txtid_cliente"));

		Asesoria asesoria = new Asesoria(fechayhora, motivo, detalle, id_profesional, id_cliente);
		
		AsesoriaDao asesoriadao = new AsesoriaDao();
		boolean agregar = asesoriadao.crearAsesoria(asesoria);
		
		String mensaje = "";
		
		if (agregar)
			mensaje = "La asesoria se ha creado exitosamente";
		else
			mensaje = "Ocurrio un error al crear la asesoria";
			
		request.setAttribute("cumensaje", mensaje);
		
		//mostrarlista
		List<Asesoria> lasesorias = new ArrayList<Asesoria>();
		
		lasesorias = asesoriadao.leerAsesoria();
			
		request.setAttribute("listadoasesorias",lasesorias);
		
		doGet(request,response);
//		request.getRequestDispatcher("CrearAsesoria").forward(request, response);
		
	}

}
