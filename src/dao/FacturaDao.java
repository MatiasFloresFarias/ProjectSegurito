package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import idao.iFacturaDao;
import modelo.DetalleFactura;
import modelo.Factura;

import conectar.ConexionSingleton;

public class FacturaDao implements iFacturaDao {
	public static boolean integridad =false;
	@Override
	public boolean crearFactura(Factura fac) {
		// TODO Auto-generated method stub

		boolean registrar = false;
		
		PreparedStatement stm = null;
		Connection con = null;
		
		String sql = "INSERT INTO factura(fechacobro, fechavencimiento, extras, impuestos, subtotal, total, cliente_id_cliente) VALUES (TO_DATE(?,'dd/mm/yyyy'), TO_DATE(?,'dd/mm/yyyy'), ?, ?, ?, ?, ?)";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1,fac.getFechadecobro());
			stm.setString(2, fac.getFechaVencimiento());
			stm.setInt(3, fac.getExtras());
			stm.setInt(4, fac.getImpuestos());
			stm.setInt(5, fac.getSubtotal());
			stm.setInt(6, fac.getTotal());
			stm.setInt(7, fac.getId_cliente());
			stm.execute();
			registrar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase FacturaDao, metodo crearFactura");
			e.printStackTrace();
		}
		
		return registrar;	
	
	}

	@Override
	public List<Factura> leerFactura() {
		// TODO Auto-generated method stub

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_factura, fechacobro, fechavencimiento, extras, impuestos, subtotal, total, cliente_id_cliente, nombreempresa as cliente FROM factura INNER JOIN cliente ON cliente_id_cliente=id_cliente";
		
		List<Factura> listaFactura = new ArrayList<Factura>();
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Factura c = new Factura();
				c.setId_factura(rs.getInt("id_factura"));
				c.setFechadecobro(rs.getString("fechacobro"));
				c.setFechaVencimiento(rs.getString("fechavencimiento"));
				c.setExtras(rs.getInt("extras"));
				c.setImpuestos(rs.getInt("impuestos"));
				c.setSubtotal(rs.getInt("subtotal"));
				c.setTotal(rs.getInt("total"));
				c.setId_cliente(rs.getInt("cliente_id_cliente"));
				c.setCliente(rs.getString("cliente"));
				listaFactura.add(c);
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase FacturaDao, metodo leerFactura ");
			e.printStackTrace();
		}
		
		return listaFactura;
	}

	@Override
	public boolean actualizarFactura(Factura fac) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement stm = null;
		
		boolean actualizar = false;
		
		String sql = "UPDATE factura SET fechacobro = TO_DATE(?,'dd/mm/yyyy'), fechavencimiento = TO_DATE(?,'dd/mm/yyyy'), extras = ?, impuestos = ?, subtotal = ?, total = ?, cliente_id_cliente = ? WHERE id_factura = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, fac.getFechadecobro());
			stm.setString(2, fac.getFechaVencimiento());
			stm.setInt(3, fac.getExtras());
			stm.setInt(4, fac.getImpuestos());
			stm.setInt(5, fac.getSubtotal());
			stm.setInt(6, fac.getTotal());
			stm.setInt(7, fac.getId_cliente());
			stm.setInt(8, fac.getId_factura());
			stm.executeUpdate();
			actualizar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase FacturaDao, metodo actualizar");
			e.printStackTrace();
		}
		
		return actualizar;

	}

	@Override
	public boolean eliminarFactura(Factura fac) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement stm = null;
		
		boolean eliminar = false;
		
		String sql = "DELETE FROM factura WHERE id_factura = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, fac.getId_factura());
			stm.execute();
			eliminar = true;
			stm.close();
			con.close();
		}catch(SQLIntegrityConstraintViolationException ex) {
    		System.out.println("Error de integridad: Debe eliminar primero los datos asociados a este campo en el resto de las tablas");
    		integridad = true;
    		ex.printStackTrace();
      }catch(SQLException e) {
			System.out.println("Error: Clase FacturaDao, metodo eliminarFactura");
			e.printStackTrace();
		}
		
		return eliminar;
	}

	@Override
	public Factura obtenerFactura(int id_factura) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from factura where id_factura = ?";
		
		Factura u = new Factura();
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, id_factura);
			rs = stm.executeQuery();
			while (rs.next()) {
				u.setId_factura(rs.getInt("id_factura"));
				u.setFechadecobro(rs.getString("fechacobro"));
				u.setFechaVencimiento(rs.getString("fechavencimiento"));
				u.setExtras(rs.getInt("extras"));
				u.setImpuestos(rs.getInt("impuestos"));
				u.setSubtotal(rs.getInt("subtotal"));
				u.setTotal(rs.getInt("total"));
				u.setId_cliente(rs.getInt("cliente_id_cliente"));
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase FacturaDao, metodo obtenerFactura");
			e.printStackTrace();
		}
		
		return u;
	}

//	public List<DetalleFactura> calcularFactura(int id_factura) {
//		Connection con = null;
//		Statement stm = null;
//		ResultSet rs = null;
//		
//		String sql = "select * from detallefactura where factura_id_factura = " + id_factura;
//		List <DetalleFactura> listaFactura = new ArrayList<DetalleFactura>();
//		
//		try {
//			con = ConexionSingleton.getConnection();
//			stm = con.createStatement();
//			rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				DetalleFactura c = new DetalleFactura();
//				c.setId_detallefactura(rs.getInt("id_detallefactura"));
//				c.setNombre(rs.getString("nombre"));
//				c.setPrecio(rs.getInt("precio"));
//				c.setCantidad(rs.getInt("cantidad"));
//				listaFactura.add(c);
//			}
//			stm.close();
//			rs.close();
//			con.close(); 
//		} catch(SQLException e) {
//			System.out.println("Error: Clase FacturaDao, metodo calcularFactura ");
//			e.printStackTrace();
//		}
//		return listaFactura;
//	}
	
	public boolean actualizarValores(Factura fact) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement stm = null;
		
		boolean actualizar = false;
		
		String sql = "UPDATE factura SET extras = ?, impuestos = ?, subtotal = ?, total = ? WHERE id_factura = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, fact.getExtras());
			stm.setInt(2, fact.getImpuestos());
			stm.setInt(3, fact.getSubtotal());
			stm.setInt(4, fact.getTotal());
			stm.setInt(5, fact.getId_factura());
			stm.executeUpdate();
			actualizar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase FacturaDao, metodo actualizar valores");
			e.printStackTrace();
		}
		
		return actualizar;

	}
	
	
}