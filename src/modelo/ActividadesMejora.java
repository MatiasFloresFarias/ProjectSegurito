package modelo;

public class ActividadesMejora {

	//Variables//
	
	private int idActMejora;
	private String nombre;
	private String fechaInicio;
	private String fechaTermino;
	private String estado;
	private String detalle;
	private int id_profesional;
	private int id_cliente;
	private String profesional;
	private String cliente;
	
	//Constructores//
	
	public ActividadesMejora() {
		super();
	}

	public ActividadesMejora(String nombre, String fechaInicio, String fechaTermino, String estado, String detalle, int id_profesional, int id_cliente) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.estado = estado;
		this.detalle = detalle;
		this.id_profesional = id_profesional;
		this.id_cliente = id_cliente;
	}
	
	public ActividadesMejora(int idActMejora, String nombre, String fechaInicio, String fechaTermino, String estado, String detalle, int id_profesional, int id_cliente) {
		super();
		this.idActMejora = idActMejora;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.estado = estado;
		this.detalle = detalle;
		this.id_profesional = id_profesional;
		this.id_cliente = id_cliente;
	}
	
	//Getters & Setters

	
	
	public int getIdActMejora() {
		return idActMejora;
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

	public void setIdActMejora(int idActMejora) {
		this.idActMejora = idActMejora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
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



	
}
