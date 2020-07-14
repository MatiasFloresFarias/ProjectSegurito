package controlador.ControladorCliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ClienteDao;

import modelo.Cliente;

/**
 * Servlet implementation class CrearCliente
 */
@WebServlet("/CrearCliente")
public class CrearCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Cliente> lclientes = new ArrayList<Cliente>();
		ClienteDao clientedao = new ClienteDao();
		lclientes = clientedao.leerCliente();
			
		request.setAttribute("listadoclientes",lclientes);
		
		
		request.getRequestDispatcher("cliente.jsp").forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombreEmpresa = request.getParameter("txtNombreEmpresa");
		String rut = request.getParameter("txtRutEmpresa");
		String fechaRegistro = request.getParameter("txtFechaRegistro");

		Cliente cliente = new Cliente(nombreEmpresa, rut, fechaRegistro);
		
		ClienteDao clientedao = new ClienteDao();
		boolean agregar = clientedao.crearCliente(cliente);
		
		String mensaje = "";
		
		if (agregar)
			mensaje = "El cliente se ha creado exitosamente";
		else
			mensaje = "Ocurrio un error al creal al cliente";
			
		request.setAttribute("cumensaje", mensaje);
		
	
		List<Cliente> lclientes = new ArrayList<Cliente>();
		
		lclientes = clientedao.leerCliente();
			
		request.setAttribute("listadoasesorias",lclientes);
		
		
		doGet(request, response);
	}

}
