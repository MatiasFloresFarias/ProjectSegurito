package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import idao.iActividadesMejorasDao;
import modelo.ActividadesMejora;
import conectar.ConexionSingleton;

public class ActividadesMejoraDao implements iActividadesMejorasDao {

	@Override
	public boolean crearActividad(ActividadesMejora act) {

		boolean registrar = false;

		PreparedStatement stm = null;
		Connection con = null;
		String sql = "INSERT INTO actividadesmejora(nombre, fechainicio, fechatermino, estado, detalle, profesional_id_profesional, cliente_id_cliente) VALUES ( ?, TO_DATE(?,'dd/mm/yyyy'), TO_DATE(?,'dd/mm/yyyy'), ?, ?, ?, ?)";

		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, act.getNombre());
			stm.setString(2, act.getFechaInicio());
			stm.setString(3, act.getFechaTermino());
			stm.setString(4, act.getEstado());
			stm.setString(5, act.getDetalle());
			stm.setInt(6, act.getId_profesional());
			stm.setInt(7, act.getId_cliente());
			stm.execute();
			registrar = true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ActividadesMejorasDao, metodo crearActividad");
			e.printStackTrace();
		}

		return registrar;

	}

	@Override
	public List<ActividadesMejora> leerActividad() {
		// TODO Auto-generated method stub

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;

		String sql = "SELECT idactividadmejora, actividadesmejora.nombre as nombre, fechainicio, fechatermino, estado, detalle, profesional_id_profesional, cliente_id_cliente, profesional.nombre || ' ' || apellido as profesional, nombreempresa as cliente FROM actividadesmejora INNER JOIN profesional ON profesional_id_profesional=id_profesional INNER JOIN cliente ON cliente_id_cliente=id_cliente";

		List<ActividadesMejora> listaActividades = new ArrayList<ActividadesMejora>();

		try {
			con = ConexionSingleton.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				ActividadesMejora c = new ActividadesMejora();
				c.setIdActMejora(rs.getInt("idactividadmejora"));
				c.setNombre(rs.getString("nombre"));
				c.setFechaInicio(rs.getString("fechainicio"));
				c.setFechaTermino(rs.getString("fechatermino"));
				c.setEstado(rs.getString("estado"));
				c.setDetalle(rs.getString("detalle"));
				c.setProfesional(rs.getString("profesional"));
				c.setCliente(rs.getString("cliente"));
				listaActividades.add(c);
			}
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ActividadesMejoraDao, metodo leerActividad ");
			e.printStackTrace();
		}

		return listaActividades;
	}

	@Override
	public boolean actualizarActividad(ActividadesMejora act) {

		Connection con = null;
		PreparedStatement stm = null;

		boolean actualizar = false;
		
		String sql = "UPDATE actividadesmejora SET nombre = ?, fechainicio = TO_DATE(?,'dd/mm/yyyy'), fechatermino = TO_DATE(?,'dd/mm/yyyy'), estado = ?, detalle = ?, profesional_id_profesional = ?, cliente_id_cliente = ? WHERE idactividadmejora = ?";
		
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, act.getNombre());
			stm.setString(2, act.getFechaInicio());
			stm.setString(3, act.getFechaTermino());
			stm.setString(4, act.getEstado());
			stm.setString(5, act.getDetalle());
			stm.setInt(6, act.getId_profesional());
			stm.setInt(7, act.getId_cliente());
			stm.setInt(8, act.getIdActMejora());
			stm.executeUpdate();
			actualizar = true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ActividadesMejoraDao, metodo actualizar");
			e.printStackTrace();
		}

		return actualizar;

	}

	@Override
	public boolean eliminarActividad(ActividadesMejora act) {

		Connection con = null;
		PreparedStatement stm = null;

		boolean eliminar = false;

		String sql = "DELETE FROM actividadesmejora WHERE idactividadmejora = ?";

		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, act.getIdActMejora());
			stm.execute();
			eliminar = true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ActividadesMejorasDao, metodo eliminarActividad");
			e.printStackTrace();
		}

		return eliminar;
	}

	@Override
	public ActividadesMejora obtenerActividad(int idActMejora) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		String sql = "select * from actividadesmejora where idactividadmejora = ?";

		ActividadesMejora u = new ActividadesMejora();
		try {
			con = ConexionSingleton.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, idActMejora);
			rs = stm.executeQuery();
			while (rs.next()) {
				u.setIdActMejora(rs.getInt("idactividadmejora"));
				u.setNombre(rs.getString("nombre"));
				u.setFechaInicio(rs.getString("fechainicio"));
				u.setFechaTermino(rs.getString("fechatermino"));
				u.setEstado(rs.getString("estado"));
				u.setDetalle(rs.getString("detalle"));
				u.setId_profesional(rs.getInt("profesional_id_profesional"));
				u.setId_cliente(rs.getInt("cliente_id_cliente"));
			}
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ActividadesMejorasDao, metodo obtenerActividad ");
			e.printStackTrace();
		}

		return u;
	}

}