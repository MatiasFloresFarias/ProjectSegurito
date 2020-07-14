package modelo;

public class Capacitacion {
	
	//Variables//
	private int id_capacitacion;
	private String fechayhora;
	private String tema;
	private String contenido;
	private int id_profesional;
	private int id_cliente;
	private String profesional;
	private String cliente;
	
	
	//Constructores//
	
	public Capacitacion() {
		super();
	}

	public Capacitacion(String fechayhora, String tema, String contenido,int id_profesional, int id_cliente) {
		super();
		this.fechayhora = fechayhora;
		this.tema = tema;
		this.contenido = contenido;
		this.id_profesional = id_profesional;
		this.id_cliente = id_cliente;
	}

	public Capacitacion(int id_capacitacion, String fechayhora, String tema, String contenido, int id_profesional, int id_cliente) {
		super();
		this.id_capacitacion = id_capacitacion;
		this.fechayhora = fechayhora;
		this.tema = tema;
		this.contenido = contenido;
		this.id_profesional = id_profesional;
		this.id_cliente = id_cliente;
	}

	
	//Getters & Setters
	
	public int getId_capacitacion() {
		return id_capacitacion;
	}

	public void setId_capacitacion(int id_capacitacion) {
		this.id_capacitacion = id_capacitacion;
	}

	public String getFechayhora() {
		return fechayhora;
	}

	public void setFechayhora(String fechayhora) {
		this.fechayhora = fechayhora;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_profesional() {
		return id_profesional;
	}

	public void setId_profesional(int id_profesional) {
		this.id_profesional = id_profesional;
	}

	public String getProfesional() {
		return profesional;
	}

	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Capacitacion [id_capacitacion=" + id_capacitacion + ", fechayhora=" + fechayhora + ", tema=" + tema
				+ ", contenido=" + contenido + ", id_cliente=" + id_cliente + ", id_profesional=" + id_profesional
				+ ", profesional=" + profesional + ", cliente=" + cliente + "]";
	}	
	
}
