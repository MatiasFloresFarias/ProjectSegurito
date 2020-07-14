package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conectar.ConexionSingleton;
import idao.iReporteAccidenteDao;
import modelo.ReporteAccidente;

public class ReporteAccidenteDao implements iReporteAccidenteDao {

    @Override
    public boolean crearReporteAccidente(ReporteAccidente reporte) {
        // TODO Auto-generated method stub
        boolean registrar = false;
        
        Statement stm = null;
        Connection con = null;
        
        String sql = "INSERT INTO reporteaccidente (fecha,direccion,labor,descripcion,cliente_id_cliente) VALUES (TO_DATE('"+reporte.getFecha()+"', 'dd/mm/yyyy'),'"+ reporte.getDireccion()+"','"+reporte.getLabor()+"','"+reporte.getDescripcion()+"', '"+reporte.getId_cliente()+"')";       
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
        }catch(SQLException e) {
                System.out.println("Error: Clase ReporteAccidenteDao, metodo crearReporteAccidente");
                e.printStackTrace();
        }
        
        return registrar;
    }

    @Override
    public List<ReporteAccidente> leerReporteAccidente() {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String sql = "SELECT id_reporte, fecha, direccion, labor, descripcion, cliente_id_cliente, nombreempresa as cliente FROM reporteaccidente INNER JOIN cliente ON cliente_id_cliente=id_cliente";

        
        List<ReporteAccidente> listaReporteAccidentes = new ArrayList<ReporteAccidente>();
        
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                        ReporteAccidente c = new ReporteAccidente();
                        c.setIdReporteAccidente(rs.getInt(1));
                        c.setFecha(rs.getString(2));
                        c.setDireccion(rs.getString(3));
                        c.setLabor(rs.getString(4));
                        c.setDescripcion(rs.getString(5));
                        c.setId_cliente(rs.getInt(6));
                        c.setCliente(rs.getString(7));
                        listaReporteAccidentes.add(c);
                }
                stm.close();
                rs.close();
                con.close();
        } catch(SQLException e) {
                System.out.println("Error: Clase ReporteAccidenteDao, metodo leerReporteAccidente ");
                e.printStackTrace();
        }
        
        return listaReporteAccidentes;
    }

    @Override
    public boolean actualizarReporteAccidente(ReporteAccidente reporte) {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        
        boolean actualizar = false;
        
        String sql = "UPDATE reporteaccidente SET fecha = TO_DATE('"+reporte.getFecha()+"', 'dd/mm/yyyy'), direccion = '"+ reporte.getDireccion()+"', labor = '"+reporte.getLabor()+"', descripcion = '"+reporte.getDescripcion()+"', cliente_id_cliente = '"+reporte.getId_cliente()+"' WHERE id_reporte = '"+reporte.getIdReporteAccidente()+"'";
        System.out.println(sql);
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                stm.execute(sql);
                actualizar = true;
                stm.close();
                con.close();
        }catch(SQLException e) {
                System.out.println("Error: Clase ReporteAccidenteDao, metodo actualizarReporteAccidente");
                e.printStackTrace();
        }
        
        return actualizar;

    }

    @Override
    public boolean eliminarReporteAccidente(ReporteAccidente reporte) {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        
        boolean eliminar = false;
        
        String sql = "DELETE FROM reporteaccidente WHERE id_reporte = " + reporte.getIdReporteAccidente();
        
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                stm.execute(sql);
                eliminar = true;
                stm.close();
                con.close();
        }catch(SQLException e) {
                System.out.println("Error: Clase ReporteAccidenteDao, metodo eliminarReporteAccidente");
                e.printStackTrace();
        }
        
        return eliminar;
    }

    @Override
    public ReporteAccidente obtenerReporteAccidente(int id_ReporteAccidente) {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String sql = "select * from reporteaccidente where id_reporte = " + id_ReporteAccidente;
        
        ReporteAccidente u = new ReporteAccidente();
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                        u.setIdReporteAccidente(rs.getInt(1));
                        u.setFecha(rs.getString(2));
                        u.setDireccion(rs.getString(3));
                        u.setLabor(rs.getString(4));
                        u.setDescripcion(rs.getString(5));
                        u.setId_cliente(rs.getInt(6));
                }
                stm.close();
                rs.close();
                con.close();
        } catch(SQLException e) {
                System.out.println("Error: Clase ReporteAccidenteDao, metodo obtenerReporteAccidente ");
                e.printStackTrace();
        }
        
        return u;
    }

    

}
