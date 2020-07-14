package controlador.ControladorDetalleFactura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DetalleFacturaDao;
import modelo.DetalleFactura;

/**
 * Servlet implementation class ListarDetalle
 */
@WebServlet("/ListarDetalleFactura")
public class ListarDetalleFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarDetalleFactura() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DetalleFacturaDao detallefacturadao = new DetalleFacturaDao();
		List<DetalleFactura> ldetallefactura = new ArrayList<DetalleFactura>();
		int facturaid = Integer.parseInt(request.getParameter("id"));
		
		ldetallefactura = detallefacturadao.leerDetalleFactura(facturaid);

		request.setAttribute("listadodetallefactura", ldetallefactura);
		request.getRequestDispatcher("detalleFacturaCliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
