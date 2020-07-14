package idao;

import java.util.List;

import modelo.Asesoria;


public interface iAsesoriaDao {
	public boolean crearAsesoria(Asesoria asesoria);
	public List<Asesoria> leerAsesoria();
	public boolean actualizarAsesoria(Asesoria asesoria);
	public boolean eliminarAsesoria(Asesoria asesoria);
	public Asesoria obtenerAsesoria(int idasesoria);
}
