package controlador.ControladorFactura;

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

import dao.ClienteDao;
import dao.DetalleFacturaDao;
import dao.FacturaDao;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;

/**
 * Servlet implementation class EditarFactura
 */
@WebServlet("/EditarFactura")
public class EditarFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarFactura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteDao clientedao = new ClienteDao();
		List<Cliente> lclientes = new ArrayList<Cliente>();
		
		lclientes = clientedao.leerCliente();
		
		request.setAttribute("listadoclientes",lclientes);
		
		
		int facturaid = Integer.parseInt(request.getParameter("id"));
		
		FacturaDao facturadao = new FacturaDao();
		Factura factura = new Factura();
		factura = facturadao.obtenerFactura(facturaid);
		
		//transformo las fechas pa q se vean en el mismo formato q acepta sql
		String fecha1 = factura.getFechadecobro();
		LocalDateTime datetime = LocalDateTime.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fechacobro = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		factura.setFechadecobro(fechacobro);
		
		String fecha2 = factura.getFechaVencimiento();
		LocalDateTime datetime2 = LocalDateTime.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		String fechaVencimiento = datetime2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		factura.setFechaVencimiento(fechaVencimiento);
		
		
		request.setAttribute("datosfactura", factura);
		request.getRequestDispatcher("facturaEditar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    

            String fechadecobro = request.getParameter("txtfechadecobro");
            String fechaVencimiento = request.getParameter("txtfechaVencimiento");
            int extras = Integer.parseInt(request.getParameter("txtextras"));
            int impuestos = Integer.parseInt(request.getParameter("txtimpuestos"));
            int subtotal = Integer.parseInt(request.getParameter("txtsubtotal"));
            int total = Integer.parseInt(request.getParameter("txttotal"));
            int id_cliente = Integer.parseInt(request.getParameter("txtid_cliente"));
            int id = Integer.parseInt(request.getParameter("hdnidfactura"));
            
            Factura factura = new Factura(id, fechadecobro, fechaVencimiento, extras, impuestos, subtotal,total,id_cliente);
            
            FacturaDao facturadao = new FacturaDao();      
            boolean editar = facturadao.actualizarFactura(factura);
            
            String mensaje = "";
            
            if (editar)
                mensaje = "La Factura se ha editado exitosamente";
            else
                mensaje = "Ocurrio un error al editar la factura";
            
            
            //actualizar items +extra
            List<DetalleFactura> listadoitems = new ArrayList<DetalleFactura>();
            DetalleFacturaDao detallefacturadao = new DetalleFacturaDao();
            listadoitems = detallefacturadao.leerDetalleFactura(id);
            factura.setItems(listadoitems);
            factura.setSubtotal((int)factura.calcularSubtotal());
            factura.setImpuestos((int)factura.calcularIVA());
            factura.setTotal((int)factura.calcularTotal());
            facturadao.actualizarValores(factura);
            
            
        request.setAttribute("datosfactura", factura);
        request.setAttribute("cumensaje", mensaje);
		
        response.sendRedirect("CrearFactura?emensaje="+ mensaje);
      //doGet(request, response);
        
	}

}
