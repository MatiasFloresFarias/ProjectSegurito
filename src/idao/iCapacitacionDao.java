package idao;

import java.util.List;

import modelo.Capacitacion;


public interface iCapacitacionDao {
	
	public boolean crearCapacitacion(Capacitacion cap);
	public List<Capacitacion> leerCapacitacion();
	public boolean actualizarCapacitacion(Capacitacion cap);
	public boolean eliminarCapacitacion(Capacitacion cap);
	Capacitacion obtenerCapacitacion(int id_capacitacion);

}
