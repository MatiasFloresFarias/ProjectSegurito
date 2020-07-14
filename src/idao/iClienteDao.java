package idao;

import java.util.List;

import modelo.Cliente;

public interface iClienteDao {

    public boolean crearCliente(Cliente cl);
    public List<Cliente> leerCliente();
    public boolean actualizarCliente(Cliente cl);
    public boolean eliminarCliente(Cliente cl);
    Cliente obtenerCliente(int id_cliente);

}
