package idao;

import java.util.List;

import modelo.ActividadesMejora;


public interface iActividadesMejorasDao {
	
	public boolean crearActividad(ActividadesMejora act);
	public List<ActividadesMejora> leerActividad();
	public boolean actualizarActividad(ActividadesMejora act);
	public boolean eliminarActividad(ActividadesMejora act);
	ActividadesMejora obtenerActividad(int idActMejora);

}
