package modelo;

public class ReporteAccidente {
    
    private int idReporteAccidente;
    private String fecha;
    private String direccion;
    private String labor;
    private String descripcion;
    private int id_cliente;
    private String cliente;
    
    public ReporteAccidente() {
        super();
    }
    
       
    public ReporteAccidente(int idReporteAccidente, String fecha, String direccion, String labor, String descripcion,
			int id_cliente) {
		super();
		this.idReporteAccidente = idReporteAccidente;
		this.fecha = fecha;
		this.direccion = direccion;
		this.labor = labor;
		this.descripcion = descripcion;
		this.id_cliente = id_cliente;
	}



	public ReporteAccidente(String fecha, String direccion, String labor, String descripcion, int id_cliente) {
        this.fecha = fecha;
        this.direccion = direccion;
        this.labor = labor;
        this.descripcion = descripcion;
        this.id_cliente = id_cliente;
    }


    public ReporteAccidente(int idReporteAccidente) {
		super();
		this.idReporteAccidente = idReporteAccidente;
	}
    
    
    

	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public int getIdReporteAccidente() {
        return idReporteAccidente;
    }


    public void setIdReporteAccidente(int idReporteAccidente) {
        this.idReporteAccidente = idReporteAccidente;
    }


    public String getFecha() {
        return fecha;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getLabor() {
        return labor;
    }


    public void setLabor(String labor) {
        this.labor = labor;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	@Override
    public String toString() {
        return "ReporteAccidente [idReporteAccidente=" + idReporteAccidente + ", fecha=" + fecha + ", direccion="
                + direccion + ", labor=" + labor + ", descripcion=" + descripcion + "]";
    }
    
    
    
}


 