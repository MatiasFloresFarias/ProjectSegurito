package controlador.ControladorDetalleFactura;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.DetalleFacturaDao;
import dao.FacturaDao;
import modelo.DetalleFactura;
import modelo.Factura;


/**
 * Servlet implementation class EditarDetalleFactura
 */
@WebServlet("/EditarDetalleFactura")
public class EditarDetalleFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarDetalleFactura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ClienteDao clientedao = new ClienteDao();
//		List<Cliente> lclientes = new ArrayList<Cliente>();
//		
//		lclientes = clientedao.leerCliente();
//		
//		request.setAttribute("listadoclientes",lclientes);
//		
//		
		int detallefacturaid = Integer.parseInt(request.getParameter("id"));
		int facturaid = Integer.parseInt(request.getParameter("idfactura"));
		
		DetalleFacturaDao detallefacturadao = new DetalleFacturaDao();
		DetalleFactura detallefactura = new DetalleFactura();
		detallefactura = detallefacturadao.obtenerDetalleFactura(detallefacturaid);
		
		request.setAttribute("datosdetallefactura", detallefactura);
		request.setAttribute("facturaid", facturaid);
		request.getRequestDispatcher("detalleFacturaEditar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String nombre = request.getParameter("txtnombre");
            int precio = Integer.parseInt(request.getParameter("txtprecio"));
            int cantidad = Integer.parseInt(request.getParameter("txtcantidad"));
            int id_factura = Integer.parseInt(request.getParameter("txtid_factura"));
            int id = Integer.parseInt(request.getParameter("hdniddetallefactura"));
            
            DetalleFactura detallefactura = new DetalleFactura(id, nombre, precio, cantidad, id_factura);
            
            DetalleFacturaDao detallefacturadao = new DetalleFacturaDao();
            boolean editar = detallefacturadao.actualizarDetalleFactura(detallefactura);
            
            String mensaje = "";
            
            if (editar)
                mensaje = "El detalle de Factura se ha editado exitosamente";
            else
                mensaje = "Ocurrio un error al editar el detalle de factura";
            
            //actualizar valores en factura
            Factura factura = new Factura();
            FacturaDao facturadao = new FacturaDao();
            factura = facturadao.obtenerFactura(id_factura);
            factura.setItems(detallefacturadao.leerDetalleFactura(id_factura));
            factura.setSubtotal((int)factura.calcularSubtotal());
            factura.setImpuestos((int)factura.calcularIVA());
            factura.setTotal((int)factura.calcularTotal());
            
            facturadao.actualizarValores(factura);
            

        request.setAttribute("datosdetallefactura", detallefactura);
        request.setAttribute("cumensaje", mensaje);
		
        response.sendRedirect("CrearDetalleFactura?emensaje="+ mensaje + "&id="+id_factura);
      //doGet(request, response);
        
	}

}

