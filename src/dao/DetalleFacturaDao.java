package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import idao.iDetalleFacturaDao;
import modelo.DetalleFactura;

import conectar.ConexionSingleton;

public class DetalleFacturaDao implements iDetalleFacturaDao {

	@Override
	public boolean crearDetalleFactura(DetalleFactura detallefac) {
		// TODO Auto-generated method stub

		boolean registrar = false;
		
		PreparedStatement stm = null;
		Connection con = null;

		String sql = "INSERT INTO detallefactura(nombre, precio, cantidad, factura_id_factura) VALUES (?, ?, ?, ?)";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1,detallefac.getNombre());
			stm.setInt(2, detallefac.getPrecio());
			stm.setInt(3, detallefac.getCantidad());
			stm.setInt(4, detallefac.getId_factura());
			stm.execute();
			registrar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase DetalleFacturaDao, metodo crearDetalleFactura");
			e.printStackTrace();
		}
		
		return registrar;	
	
	}

	@Override
	public List<DetalleFactura> leerDetalleFactura(int id_factura) {
		// TODO Auto-generated method stub

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from detallefactura where factura_id_factura =" + id_factura;
		
		List<DetalleFactura> listaDetalleFactura = new ArrayList<DetalleFactura>();
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				DetalleFactura c = new DetalleFactura();
				c.setId_detallefactura(rs.getInt("id_detallefactura"));
				c.setNombre(rs.getString("nombre"));
				c.setPrecio(rs.getInt("precio"));
				c.setCantidad(rs.getInt("cantidad"));
				c.setId_factura(rs.getInt("factura_id_factura"));
				listaDetalleFactura.add(c);
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase DetalleFacturaDao, metodo leerDetalleFactura ");
			e.printStackTrace();
		}
		
		return listaDetalleFactura;
	}

	@Override
	public boolean actualizarDetalleFactura(DetalleFactura detallefac) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement stm = null;
		
		boolean actualizar = false;
		
		String sql = "UPDATE detallefactura SET nombre = ?, precio = ?, cantidad = ? WHERE id_detallefactura = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, detallefac.getNombre());
			stm.setInt(2, detallefac.getPrecio());
			stm.setInt(3, detallefac.getCantidad());
			stm.setInt(4, detallefac.getId_detallefactura());
			stm.executeUpdate();
			actualizar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase DetalleFacturaDao, metodo actualizar");
			e.printStackTrace();
		}
		
		return actualizar;

	}

	@Override
	public boolean eliminarDetalleFactura(DetalleFactura detallefac) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement stm = null;
		
		boolean eliminar = false;
		
		String sql = "DELETE FROM detallefactura WHERE id_detallefactura = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, detallefac.getId_detallefactura());
			stm.execute();
			eliminar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase DetalleFacturaDao, metodo eliminarDetalleFactura");
			e.printStackTrace();
		}
		
		return eliminar;
	}

	@Override
	public DetalleFactura obtenerDetalleFactura(int id_detallefactura) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from detallefactura where id_detallefactura = ?";
		
		DetalleFactura u = new DetalleFactura();
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, id_detallefactura);
			rs = stm.executeQuery();
			while (rs.next()) {
				u.setId_detallefactura(rs.getInt("id_detallefactura"));
				u.setNombre(rs.getString("nombre"));
				u.setPrecio(rs.getInt("precio"));
				u.setCantidad(rs.getInt("cantidad"));
				u.setId_factura(rs.getInt("factura_id_factura"));
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase DetalleFacturaDao, metodo obtenerDetalleFactura");
			e.printStackTrace();
		}
		
		return u;
	}

	
	
}