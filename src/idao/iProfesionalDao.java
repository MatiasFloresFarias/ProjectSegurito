package idao;

import java.util.List;

import modelo.Profesional;

public interface iProfesionalDao {
	
	public boolean crearProfesional(Profesional prf);
	public List<Profesional> leerProfesional();
	public boolean actualizarProfesional(Profesional prf);
	public boolean eliminarProfesional(Profesional prf);
	Profesional obtenerProfesional(int id_profesional);

}
