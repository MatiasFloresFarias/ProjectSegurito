package idao;

import java.util.List;

import modelo.ReporteAccidente;

public interface iReporteAccidenteDao {
    public boolean crearReporteAccidente(ReporteAccidente reporte);
    public List<ReporteAccidente> leerReporteAccidente();
    public boolean actualizarReporteAccidente(ReporteAccidente reporte);
    public boolean eliminarReporteAccidente(ReporteAccidente reporte);
    ReporteAccidente obtenerReporteAccidente(int id_ReporteAccidente);

}
