package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import idao.iProfesionalDao;
import modelo.Profesional;
import conectar.ConexionSingleton;

public class ProfesionalDao implements iProfesionalDao {
	public static boolean integridad =false;
	@Override
	public boolean crearProfesional(Profesional prf) {
		// TODO Auto-generated method stub

		boolean registrar = false;
		
		PreparedStatement stm = null;
		Connection con = null;
		
		String sql = "INSERT INTO profesional(nombre, apellido, correo, telefono, cargo) VALUES (?, ?, ?, ?, ?)";

		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1,prf.getNombre());
			stm.setString(2,prf.getApellido());
			stm.setString(3, prf.getCorreo());
			stm.setString(4, prf.getTelefono());
			stm.setString(5, prf.getCargo());
			stm.execute();
			registrar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase ProfesionalDao, metodo crearProfesional");
			e.printStackTrace();
		}
		
		return registrar;	
	
	}

	@Override
	public List<Profesional> leerProfesional() {
		// TODO Auto-generated method stub

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from profesional ORDER BY ID_profesional";
		
		List<Profesional> listaProfesionales = new ArrayList<Profesional>();
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Profesional c = new Profesional();
				c.setId_profesional(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setApellido(rs.getString(3));
				c.setCorreo(rs.getString(4));
				c.setTelefono(rs.getString(5));
				c.setCargo(rs.getString(6));
				listaProfesionales.add(c);
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase ProfesionalDao, metodo leerProfesional ");
			e.printStackTrace();
		}
		
		return listaProfesionales;
	}

	@Override
	public boolean actualizarProfesional(Profesional prf) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement stm = null;
		
		boolean actualizar = false;
		
		String sql = "UPDATE profesional SET nombre = ?, apellido = ?, correo = ?, telefono = ?, cargo = ? WHERE id_profesional = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, prf.getNombre());
			stm.setString(2, prf.getApellido());
			stm.setString(3, prf.getCorreo());
			stm.setString(4, prf.getTelefono());
			stm.setString(5, prf.getCargo());
			stm.setInt(6, prf.getId_profesional());
			stm.executeUpdate();
			actualizar = true;
			stm.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("Error: Clase ProfesionalDao, metodo actualizar");
			e.printStackTrace();
		}
		
		return actualizar;

	}

	@Override
	public boolean eliminarProfesional(Profesional prf) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement stm = null;
		
		boolean eliminar = false;
		
		String sql = "DELETE FROM profesional WHERE id_profesional = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, prf.getId_profesional());
			stm.execute();
			eliminar = true;
			stm.close();
			con.close();
		}catch(SQLIntegrityConstraintViolationException ex) {
    		System.out.println("Error de integridad: Debe eliminar primero los datos asociados a este campo en el resto de las tablas");
    		integridad = true;
    		ex.printStackTrace();
      }catch(SQLException e) {
			System.out.println("Error: Clase ProfesionalDao, metodo eliminarProfesional");
			e.printStackTrace();
		}
		
		return eliminar;
	}

	@Override
	public Profesional obtenerProfesional(int id_profesional) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from profesional where id_profesional = ?";
		
		Profesional u = new Profesional();
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, id_profesional);
			rs = stm.executeQuery();
			while (rs.next()) {
				u.setId_profesional(rs.getInt("id_profesional"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setCorreo(rs.getString("correo"));
				u.setTelefono(rs.getString("telefono"));
				u.setCargo(rs.getString("cargo"));
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase ProfesionalDao, metodo obtenerProfesional ");
			e.printStackTrace();
		}
		
		return u;
	}

	
	
}