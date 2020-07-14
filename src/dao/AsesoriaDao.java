package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import conectar.ConexionSingleton;
import idao.iAsesoriaDao;
import modelo.Asesoria;

public class AsesoriaDao implements iAsesoriaDao {

	@Override
	public boolean crearAsesoria(Asesoria asesoria) {

		boolean registrar = false;
		
		PreparedStatement stm = null;
		Connection con = null;
		
		String sql = "INSERT INTO asesorias(fechayhora, motivo, detalle, profesional_id_profesional, cliente_id_cliente) VALUES (TO_DATE(?,'dd/mm/yyyy hh24:mi'), ?, ?, ?, ?)";
		try {
			con = ConexionSingleton.getConnection();

			stm = con.prepareStatement(sql);
			stm.setString(1,asesoria.getFechayhora());
			stm.setString(2, asesoria.getMotivo());
			stm.setString(3, asesoria.getDetalle());
			stm.setInt(4, asesoria.getId_profesional());
			stm.setInt(5, asesoria.getId_cliente());
			stm.execute();
			registrar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase AsesoriaDao, metodo crearAsesoria");
			e.printStackTrace();
		}
		
		return registrar;	
	
	}

	@Override
	public List<Asesoria> leerAsesoria() {

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_asesoria, fechayhora, motivo, detalle, profesional_id_profesional, cliente_id_cliente, nombre || ' ' || apellido as profesional, nombreempresa as cliente FROM asesorias INNER JOIN profesional ON profesional_id_profesional=id_profesional INNER JOIN cliente ON cliente_id_cliente=id_cliente";
//		System.out.println(sql);
		List<Asesoria> listaAsesorias = new ArrayList<Asesoria>();
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Asesoria c = new Asesoria();
				c.setId_asesoria(rs.getInt("id_asesoria"));
				c.setFechayhora(rs.getString("fechayhora"));
				c.setMotivo(rs.getString("motivo"));
				c.setDetalle(rs.getString("detalle"));
				c.setId_profesional(rs.getInt("profesional_id_profesional"));
				c.setId_cliente(rs.getInt("cliente_id_cliente"));
				c.setProfesional(rs.getString("profesional"));
				c.setCliente(rs.getString("cliente"));
				listaAsesorias.add(c);
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase AsesoriaDao, metodo leerAsesoria ");
			e.printStackTrace();
		}
		
		return listaAsesorias;
	}

	@Override
	public boolean actualizarAsesoria(Asesoria asesoria) {

		Connection con = null;
//		Statement stm = null;
		PreparedStatement stm = null;
		
		boolean actualizar = false;
		
		String sql = "UPDATE asesorias SET fechayhora = TO_DATE(?,'dd/mm/yyyy hh24:mi'), motivo = ?, detalle = ?, profesional_id_profesional = ?, cliente_id_cliente = ? WHERE id_asesoria = ?";
//		String sql = "UPDATE asesorias SET fechayhora = TO_DATE('" + asesoria.getFechayhora() +"','dd/mm/yyyy hh24:mi'), motivo = '" + asesoria.getMotivo() + "', detalle = '"+asesoria.getDetalle()+"', profesional_id_profesional = '"+ asesoria.getId_profesional()+"', cliente_id_cliente = '"+ asesoria.getId_cliente()+"' WHERE id_asesoria = '" + asesoria.getId_asesoria() + "'";
//		System.out.println(sql);
		try {
			con = ConexionSingleton.getConnection();
//			stm = con.createStatement();
			stm = con.prepareStatement(sql);
			stm.setString(1,asesoria.getFechayhora());
			stm.setString(2, asesoria.getMotivo());
			stm.setString(3, asesoria.getDetalle());
			stm.setInt(4, asesoria.getId_profesional());
			stm.setInt(5, asesoria.getId_cliente());
			stm.setInt(6, asesoria.getId_asesoria());
//			stm.execute(sql);
			stm.executeUpdate();
			actualizar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase AsesoriaDao, metodo actualizar");
			e.printStackTrace();
		}
		
		return actualizar;

	}

	@Override
	public boolean eliminarAsesoria(Asesoria asesoria) {

		Connection con = null;
//		Statement stm = null;
		PreparedStatement stm = null;
		boolean eliminar = false;
		
		String sql = "DELETE FROM asesorias WHERE id_asesoria = ?";
//		String sql = "DELETE FROM asesorias WHERE id_asesoria = " + asesoria.getId_asesoria();
		
		try {
			con = ConexionSingleton.getConnection();
//			stm = con.createStatement();
			stm = con.prepareStatement(sql);
			stm.setInt(1, asesoria.getId_asesoria());
//			stm.execute(sql);
			stm.execute();
			eliminar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase AsesoriaDao, metodo eliminar");
			e.printStackTrace();
		}
		
		return eliminar;
	}

	@Override
	public Asesoria obtenerAsesoria(int idAsesoria) {
		Connection con = null;
//		Statement stm = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from asesorias where id_asesoria = ?";
//		String sql = "select * from asesorias where id_asesoria = " + idAsesoria;
		
		Asesoria u = new Asesoria();
		try {
			con = ConexionSingleton.getConnection();
//			stm = con.createStatement();
			stm = con.prepareStatement(sql);
			stm.setInt(1, idAsesoria);
//			rs = stm.executeQuery(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				u.setId_asesoria(rs.getInt("id_asesoria"));
				u.setFechayhora(rs.getString("fechayhora"));
				u.setMotivo(rs.getString("motivo"));
				u.setDetalle(rs.getString("detalle"));
				u.setId_profesional(rs.getInt("profesional_id_profesional"));
				u.setId_cliente(rs.getInt("cliente_id_cliente"));
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase AsesoriaDao, metodo obtener ");
			e.printStackTrace();
		}
		
		return u;
	}

	
	
}