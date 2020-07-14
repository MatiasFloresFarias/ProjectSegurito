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
import dao.FacturaDao;
import modelo.DetalleFactura;
import modelo.Factura;

/**
 * Servlet implementation class CrearDetalleFactura
 */
@WebServlet("/CrearDetalleFactura")
public class CrearDetalleFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearDetalleFactura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FacturaDao facturadao = new FacturaDao();
		List<Factura> lfacturas = new ArrayList<Factura>();
		
		lfacturas = facturadao.leerFactura();
		
		request.setAttribute("listadofacturas",lfacturas);
		
		
		//mostrarlista
		int facturaid = Integer.parseInt(request.getParameter("id"));
		List<DetalleFactura> ldetallefacturas = new ArrayList<DetalleFactura>();
		DetalleFacturaDao detallefacturadao = new DetalleFacturaDao();
		ldetallefacturas = detallefacturadao.leerDetalleFactura(facturaid);
			
		request.setAttribute("listadodetallefacturas",ldetallefacturas);
		request.setAttribute("facturaid",facturaid);
		request.getRequestDispatcher("detalleFacturaAdministrador.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
            String nombre = request.getParameter("txtnombre");
            int precio = Integer.parseInt(request.getParameter("txtprecio"));
            int cantidad = Integer.parseInt(request.getParameter("txtcantidad"));
            int id_factura = Integer.parseInt(request.getParameter("txtid_factura"));
    
            DetalleFactura detallefactura = new DetalleFactura(nombre, precio, cantidad, id_factura);        
            DetalleFacturaDao detallefacturadao = new DetalleFacturaDao();
            boolean agregar = detallefacturadao.crearDetalleFactura(detallefactura);
            
            String mensaje = "";
            
            if (agregar)
                mensaje = "El detalle de factura se ha creado exitosamente";
            else
                mensaje = "Ocurrio un error al crear el detalle de factura";
                
            request.setAttribute("cumensaje", mensaje);
            
          //mostrarlista
            List<DetalleFactura> ldetallefactura = new ArrayList<DetalleFactura>();
            
            ldetallefactura = detallefacturadao.leerDetalleFactura(id_factura);

          //actualizar valores en factura
            Factura factura = new Factura();
            FacturaDao facturadao = new FacturaDao();
            factura = facturadao.obtenerFactura(id_factura);
            factura.setItems(ldetallefactura);
            factura.setSubtotal((int)factura.calcularSubtotal());
            factura.setImpuestos((int)factura.calcularIVA());
            factura.setTotal((int)factura.calcularTotal());
            
            facturadao.actualizarValores(factura);
            
            request.setAttribute("listadodetallefacturas",ldetallefactura);

            response.sendRedirect("CrearDetalleFactura?id="+ id_factura);
	}

}
