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
 * Servlet implementation class EliminarDetalleFactura
 */
@WebServlet("/EliminarDetalleFactura")
public class EliminarDetalleFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarDetalleFactura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int detallefacturaid= Integer.parseInt(request.getParameter("id"));
	    int facturaid = Integer.parseInt(request.getParameter("idfactura"));
        DetalleFactura detallefactura = new DetalleFactura();
        detallefactura.setId_detallefactura(detallefacturaid);
        
        DetalleFacturaDao detallefacturadao = new DetalleFacturaDao();
        boolean elimino = detallefacturadao.eliminarDetalleFactura(detallefactura);
        
        List<DetalleFactura> listadoeliminar = new ArrayList<DetalleFactura>();
        listadoeliminar = detallefacturadao.leerDetalleFactura(facturaid);
        
        String mensaje = "";
        
        if (elimino)
                mensaje = "El detalle de factura ha sido eliminado exitosamente";
        else
                mensaje = "Ocurrio un problema  al eliminar el detalle factura";
        
        //actualizar valores en factura
        Factura factura = new Factura();
        FacturaDao facturadao = new FacturaDao();
        factura = facturadao.obtenerFactura(facturaid);
        factura.setItems(listadoeliminar);
        factura.setSubtotal((int)factura.calcularSubtotal());
        factura.setImpuestos((int)factura.calcularIVA());
        factura.setTotal((int)factura.calcularTotal());
        
        facturadao.actualizarValores(factura);
        
        
        request.setAttribute("cumensaje", mensaje);
        request.setAttribute("listadodetallefacturas", listadoeliminar);
        request.getRequestDispatcher("CrearDetalleFactura?id="+ facturaid).forward(request, response);
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}

}
