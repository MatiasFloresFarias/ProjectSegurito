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
import dao.ProfesionalDao;
import modelo.Factura;

/**
 * Servlet implementation class EliminarFactura
 */
@WebServlet("/EliminarFactura")
public class EliminarFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarFactura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    
	    int facturaid= Integer.parseInt(request.getParameter("id"));
            Factura factura = new Factura();
            factura.setId_factura(facturaid);
            
            FacturaDao facturadao = new FacturaDao();
            boolean elimino = facturadao.eliminarFactura(factura);
            
            List<Factura> listadoeliminar = new ArrayList<Factura>();
            listadoeliminar = facturadao.leerFactura();
            
            String mensaje = "";
            
    		if (elimino) {
    			mensaje = "La factura ha sido eliminada exitosamente";
    		} else {
    			if (FacturaDao.integridad) {
    				mensaje = "Error de integridad, esta tratando de eliminar un registro con campos secundarios asociados";
    				FacturaDao.integridad = false;
    			} else {
    				mensaje = "Ocurrio un problema  al eliminar la factura";
    			}
    		}
            
            request.setAttribute("cumensaje", mensaje);
            request.setAttribute("listadofacturas", listadoeliminar);
            request.getRequestDispatcher("CrearFactura").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
