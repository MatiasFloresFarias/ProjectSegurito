package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import idao.iCapacitacionDao;

import modelo.Capacitacion;

import conectar.ConexionSingleton;

public class CapacitacionDao implements iCapacitacionDao {

	@Override
	public boolean crearCapacitacion(Capacitacion cap) {

		boolean registrar = false;
		
		PreparedStatement stm = null;
		Connection con = null;
		
		String sql = "INSERT INTO capacitaciones(fechayhora, tema, contenido, profesional_id_profesional, cliente_id_cliente) VALUES (TO_DATE(?,'dd/mm/yyyy hh24:mi'), ?, ?, ?, ?)";

		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1,cap.getFechayhora());
			stm.setString(2, cap.getTema());
			stm.setString(3, cap.getContenido());
			stm.setInt(4, cap.getId_profesional());
			stm.setInt(5, cap.getId_cliente());
			stm.execute();
			registrar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase UsuarioDao, metodo crearUsuario");
			e.printStackTrace();
		}
		
		return registrar;	
	
	}

	@Override
	public List<Capacitacion> leerCapacitacion() {

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_capacitacion, fechayhora, tema, contenido, profesional_id_profesional, cliente_id_cliente, nombre || ' ' || apellido as profesional, nombreempresa as cliente FROM capacitaciones INNER JOIN profesional ON profesional_id_profesional=id_profesional INNER JOIN cliente ON cliente_id_cliente=id_cliente";
		
		List<Capacitacion> listaCapacitaciones = new ArrayList<Capacitacion>();
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Capacitacion c = new Capacitacion();
				c.setId_capacitacion(rs.getInt("id_capacitacion"));
				c.setFechayhora(rs.getString("fechayhora"));
				c.setTema(rs.getString("tema"));
				c.setContenido(rs.getString("contenido"));
				c.setId_profesional(rs.getInt("profesional_id_profesional"));
				c.setId_cliente(rs.getInt("cliente_id_cliente"));
				c.setProfesional(rs.getString("profesional"));
				c.setCliente(rs.getString("cliente"));
				listaCapacitaciones.add(c);
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase CapacitacionDao, metodo leerCapacitacion ");
			e.printStackTrace();
		}
		
		return listaCapacitaciones;
	}

	@Override
	public boolean actualizarCapacitacion(Capacitacion cap) {

		Connection con = null;
		PreparedStatement stm = null;
		
		boolean actualizar = false;
		
		String sql = "UPDATE capacitaciones SET fechayhora = TO_DATE(?,'dd/mm/yyyy hh24:mi'), tema = ?, contenido = ?, profesional_id_profesional = ?, cliente_id_cliente = ? WHERE id_capacitacion = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, cap.getFechayhora());
			stm.setString(2, cap.getTema());
			stm.setString(3, cap.getContenido());
			stm.setInt(4, cap.getId_profesional());
			stm.setInt(5, cap.getId_cliente());
			stm.setInt(6, cap.getId_capacitacion());
			stm.executeUpdate();
			actualizar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase CapacitacionDao, metodo actualizar");
			e.printStackTrace();
		}
		
		return actualizar;

	}

	@Override
	public boolean eliminarCapacitacion(Capacitacion cap) {

		Connection con = null;
		PreparedStatement stm = null;
		
		boolean eliminar = false;
		
		String sql = "DELETE FROM capacitaciones WHERE id_capacitacion = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, cap.getId_capacitacion());
			stm.execute();
			eliminar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase CapacitacionDao, metodo eliminarCapacitacion");
			e.printStackTrace();
		}
		
		return eliminar;
	}

	@Override
	public Capacitacion obtenerCapacitacion(int id_capacitacion) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from capacitaciones where id_capacitacion = ?";
		
		Capacitacion u = new Capacitacion();
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, id_capacitacion);
			rs = stm.executeQuery();
			while (rs.next()) {
				u.setId_capacitacion(rs.getInt("id_capacitacion"));
				u.setFechayhora(rs.getString("fechayhora"));
				u.setTema(rs.getString("tema"));
				u.setContenido(rs.getString("contenido"));
				u.setId_profesional(rs.getInt("profesional_id_profesional"));
				u.setId_cliente(rs.getInt("cliente_id_cliente"));
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase CapacitacionDao, metodo obtenerCapacitacion ");
			e.printStackTrace();
		}
		
		return u;
	}

	
	
}