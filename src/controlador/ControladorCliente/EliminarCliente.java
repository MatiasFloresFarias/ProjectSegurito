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
 * Servlet implementation class EliminarCliente
 */
@WebServlet("/EliminarCliente")
public class EliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int clienteid = Integer.parseInt(request.getParameter("id"));
		Cliente cliente = new Cliente();
		cliente.setId_cliente(clienteid);

		ClienteDao clientedao = new ClienteDao();
		boolean elimino = clientedao.eliminarCliente(cliente);

		List<Cliente> listadoeliminar = new ArrayList<Cliente>();
		listadoeliminar = clientedao.leerCliente();

		String mensaje = "";
		if (elimino) {
			mensaje = "El cliente ha sido eliminado exitosamente";
		} else {
			if (ClienteDao.integridad) {
				mensaje = "Error de integridad, esta tratando de eliminar un registro con campos secundarios asociados";
				ClienteDao.integridad = false;
			} else {
				mensaje = "Ocurrio un problema  al eliminar el cliente";
			}
		}
		request.setAttribute("cumensaje", mensaje);
		request.setAttribute("listadoclientes", listadoeliminar);
		request.getRequestDispatcher("ListarCliente").forward(request, response);

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
