package idao;

import java.util.List;

import modelo.Factura;


public interface iFacturaDao {
	
	public boolean crearFactura(Factura fac);
	public List<Factura> leerFactura();
	public boolean actualizarFactura(Factura fac);
	public boolean eliminarFactura(Factura fac);
	Factura obtenerFactura(int id_factura);

}
