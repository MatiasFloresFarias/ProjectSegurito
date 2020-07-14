package controlador.ControladorCliente;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ClienteDao;

import modelo.Cliente;



/**
 * Servlet implementation class EditarCliente
 */
@WebServlet("/EditarCliente")
public class EditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		int clienteid = Integer.parseInt(request.getParameter("id"));
		
		ClienteDao clientedao = new ClienteDao();
		Cliente cliente = new Cliente();
		cliente = clientedao.obtenerCliente(clienteid);
		
		
		//transformo las fechas pa q se vean en el mismo formato q acepta sql
		String fechayhora1 = cliente.getFechaRegistro();
		LocalDateTime datetime = LocalDateTime.parse(fechayhora1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fechayhora = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		cliente.setFechaRegistro(fechayhora);
		
		request.setAttribute("datoscliente", cliente);
		request.getRequestDispatcher("clienteEditar.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombreEmpresa = request.getParameter("txtNombreEmpresa");
		String rut = request.getParameter("txtRutEmpresa");
		String fechaRegistro = request.getParameter("txtFechaRegistro");
		int id = Integer.parseInt(request.getParameter("hdnidcliente"));

		Cliente cliente = new Cliente(id,nombreEmpresa,rut,fechaRegistro);
		
		ClienteDao clientedao = new ClienteDao();
		boolean editar = clientedao.actualizarCliente(cliente);
		
		String mensaje = "";
		
		if (editar)
			mensaje = "El cliente se ha editado exitosamente";
		else
			mensaje = "Ocurrio un error al editar el cliente";

		request.setAttribute("datoscliente", cliente);
//		request.setAttribute("cumensaje", mensaje);
		response.sendRedirect("CrearCliente?emensaje="+ mensaje);
		
//		doGet(request, response);
	}

}
