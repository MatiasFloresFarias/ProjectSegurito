package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conectar.ConexionSingleton;
import modelo.Usuario;

public class UsuarioDao {

	public Usuario obtenerUsuarioByLogin(String nickname) {
		Connection con = null;
//		Statement stm = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		String sql = "select * from usuario where nickname = ?";
//		String sql = "select * from asesorias where id_asesoria = " + idAsesoria;
		
		Usuario u = new Usuario();
		try {
			con = ConexionSingleton.getConnection();
//			stm = con.createStatement();
			stm = con.prepareStatement(sql);
			stm.setString(1, nickname);
//			rs = stm.executeQuery(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				u.setId_usuario(rs.getInt("id"));
				u.setPassword(rs.getString("password"));
				u.setNickname(rs.getString("nickname"));
				u.setRol(rs.getString("rol"));
			}
			stm.close();
			rs.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("Error: Clase UsuarioDao, metodo obtener ");
			e.printStackTrace();
		}
		
		return u;
	}
}
