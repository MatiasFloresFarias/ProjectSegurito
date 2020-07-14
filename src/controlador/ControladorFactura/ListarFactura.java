package controlador.ControladorFactura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacturaDao;
import modelo.Factura;

/**
 * Servlet implementation class ListarFactura
 */
@WebServlet("/ListarFactura")
public class ListarFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarFactura() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		FacturaDao facturadao = new FacturaDao();
		List<Factura> lfactura = new ArrayList<Factura>();
		
//		for (int i = 0; i < lfactura.size(); i++) {
//			Factura factura = new Factura(i);
//			factura.getExtras();
//			factura.setItems(facturadao.calcularFactura(i));
//			factura.setImpuestos((int)factura.calcularIVA());
//			System.out.println(factura.calcularIVA());
//			factura.setSubtotal((int)factura.calcularSubtotal());
//			factura.setTotal((int)factura.calcularTotal());
//			
//		}
	
		lfactura = facturadao.leerFactura();

		request.setAttribute("listadofacturas", lfactura);
		request.getRequestDispatcher("facturaCliente.jsp").forward(request, response);
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
