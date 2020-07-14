package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conectar.ConexionSingleton;
import modelo.Cliente;
import idao.iClienteDao;

public class ClienteDao implements iClienteDao{
	
public static boolean integridad =false;


	@Override
    public boolean crearCliente(Cliente cl) {
        // TODO Auto-generated method stub
    boolean registrar = false;
        
        Statement stm = null;
        Connection con = null;
        
        String sql = "INSERT INTO cliente(nombreempresa, rutempresa, fecharegistro) VALUES ('" +cl.getNombreEmpresa()+ "','"+ cl.getRut()+"', TO_DATE('"+cl.getFechaRegistro()+"', 'dd/mm/yyyy'))";       
        System.out.println(sql);
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
        }catch(SQLException e) {
                System.out.println("Error: Clase ClienteDao, metodo crearCliente");
                e.printStackTrace();
        }
        
        return registrar;
    }

    @Override
    public List<Cliente> leerCliente() {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String sql = "select * from cliente ORDER BY id_cliente";
        
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                        Cliente c = new Cliente();
                        c.setId_cliente(rs.getInt(1));
                        c.setNombreEmpresa(rs.getString(2));
                        c.setRut(rs.getString(3));
                        c.setFechaRegistro(rs.getString(4));
                        listaClientes.add(c);
                }
                stm.close();
                rs.close();
                con.close();
        } catch(SQLException e) {
                System.out.println("Error: Clase ClienteDao, metodo leerCliente ");
                e.printStackTrace();
        }
        
        return listaClientes;
    }

    @Override
    public boolean actualizarCliente(Cliente cl) {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        
        boolean actualizar = false;
        
        String sql = "UPDATE cliente SET nombreempresa = '" + cl.getNombreEmpresa()+ "', rutempresa = '"+ cl.getRut()+"', fecharegistro =TO_DATE( '"+cl.getFechaRegistro()+"','dd/mm/yyyy') WHERE ID_CLIENTE = "+ cl.getId_cliente();
        System.out.println(sql);
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                stm.execute(sql);
                actualizar = true;
                stm.close();
                con.close();
        }catch(SQLException e) {
                System.out.println("Error: Clase ClienteDao, metodo actualizarUsuario");
                e.printStackTrace();
        }
        
        return actualizar;

    }

    @Override
    public boolean eliminarCliente(Cliente cl) {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        
        boolean eliminar = false;

        String sql = "DELETE FROM cliente WHERE id_cliente = " + cl.getId_cliente();
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                stm.execute(sql);
                eliminar = true;
                stm.close();
                con.close();
        }catch(SQLIntegrityConstraintViolationException ex) {
        		System.out.println("Error de integridad: Debe eliminar primero los datos asociados a este campo en el resto de las tablas");
        		integridad = true;
        		ex.printStackTrace();
          }catch(SQLException e) {
                System.out.println("Error: Clase ClienteDao, metodo eliminarCliente");
                e.printStackTrace();
        }
        
        return eliminar;
    }

    @Override
    public Cliente obtenerCliente(int id_cliente) {
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        String sql = "select * from cliente where id_cliente = " + id_cliente;
        
        Cliente u = new Cliente();
        try {
                con = ConexionSingleton.getConnection();
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                        u.setId_cliente(rs.getInt(1));
                        u.setNombreEmpresa(rs.getString(2));
                        u.setRut(rs.getString(3));
                        u.setFechaRegistro(rs.getString(4));
                }
                stm.close();
                rs.close();
                con.close();
        } catch(SQLException e) {
                System.out.println("Error: Clase ClienteDao, metodo obtenerCliente ");
                e.printStackTrace();
        }
        
        return u;
    }

}
