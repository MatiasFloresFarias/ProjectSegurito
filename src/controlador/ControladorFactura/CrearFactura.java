package controlador.ControladorFactura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CapacitacionDao;
import dao.ClienteDao;
import dao.FacturaDao;
import modelo.Capacitacion;
import modelo.Cliente;
import modelo.Factura;

/**
 * Servlet implementation class CrearFactura
 */
@WebServlet("/CrearFactura")
public class CrearFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearFactura() {
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
		
		//mostrarlista
		List<Factura> lfacturas = new ArrayList<Factura>();
		FacturaDao facturadao = new FacturaDao();
		lfacturas = facturadao.leerFactura();
			
		request.setAttribute("listadofacturas",lfacturas);
		
		request.getRequestDispatcher("facturaAdministrador.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
            String fechadecobro = request.getParameter("txtfechadecobro");
            String fechaVencimiento = request.getParameter("txtfechaVencimiento");
            int extras = ((request.getParameter("txtextras").equals("")) ? 0 : Integer.parseInt(request.getParameter("txtextras"))) ;
            int impuestos =0; //Integer.parseInt(request.getParameter("txtimpuestos"));
            int subtotal =0; //Integer.parseInt(request.getParameter("txtsubtotal"));
            int total =0; //Integer.parseInt(request.getParameter("txttotal"));
            int id_cliente = Integer.parseInt(request.getParameter("txtid_cliente"));
            
            Factura factura = new Factura(fechadecobro, fechaVencimiento, extras, impuestos, subtotal,total,id_cliente);
            
            FacturaDao facturadao = new FacturaDao();
            boolean agregar = facturadao.crearFactura(factura);
            
            String mensaje = "";
            
            if (agregar)
                mensaje = "La factura se ha creado exitosamente";
            else
                mensaje = "Ocurrio un error al crear la factura";
                
            request.setAttribute("cumensaje", mensaje);
            
          //mostrarlista
            List<Factura> lfactura = new ArrayList<Factura>();
            
            lfactura = facturadao.leerFactura();
                    
            request.setAttribute("listadofacturas",lfactura);
            
		doGet(request, response);
	}

}
