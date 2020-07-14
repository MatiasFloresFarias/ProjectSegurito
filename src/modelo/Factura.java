package modelo;

import java.util.List;


public class Factura {

	private int id_factura;
	private String fechadecobro;
	private String fechaVencimiento;
	private int extras;
	private int impuestos;
	private int subtotal;
	private int total;
	private int id_cliente; //FK
	private String cliente;
	protected List <DetalleFactura> items;	
	private double iva = 0.19;
	
	//Constructores//
	public Factura() {
		super();
	}

	public Factura(String fechadecobro, String fechaVencimiento, int extras, int impuestos, int subtotal, int total,
			int id_cliente) {
		super();
		this.fechadecobro = fechadecobro;
		this.fechaVencimiento = fechaVencimiento;
		this.extras = extras;
		this.impuestos = impuestos;
		this.subtotal = subtotal;
		this.total = total;
		this.id_cliente = id_cliente;
	}
	
	public Factura(int id_factura, String fechadecobro, String fechaVencimiento, int extras, int impuestos, int subtotal, int total,
			int id_cliente) {
		super();
		this.id_factura = id_factura;
		this.fechadecobro = fechadecobro;
		this.fechaVencimiento = fechaVencimiento;
		this.extras = extras;
		this.impuestos = impuestos;
		this.subtotal = subtotal;
		this.total = total;
		this.id_cliente = id_cliente;
	}


	public Factura(int id_factura) {
		super();
		this.id_factura = id_factura;
	}

	
	//Getters & Setters//
	
	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getFechadecobro() {
		return fechadecobro;
	}

	public void setFechadecobro(String fechadecobro) {
		this.fechadecobro = fechadecobro;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getExtras() {
		return extras;
	}

	public void setExtras(int extras) {
		this.extras = extras;
	}

	public int getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(int impuestos) {
		this.impuestos = impuestos;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public List<DetalleFactura> getItems() {
		return items;
	}

	public void setItems(List<DetalleFactura> items) {
		this.items = items;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "Factura [id_factura=" + id_factura + ", fechadecobro=" + fechadecobro + ", fechaVencimiento="
				+ fechaVencimiento + ", extras=" + extras + ", impuestos=" + impuestos + ", subtotal=" + subtotal
				+ ", total=" + total + ", id_cliente=" + id_cliente + "]";
	}
	
	
    public double calcularSubtotal() {
        double subtotal=0;
        for(DetalleFactura item : items ) {
            subtotal+=item.calcularTotal();
        }
        return subtotal;
    }
    
    public double calcularIVA()   { 
    	return (calcularSubtotal()+ this.extras) * iva;
    	}
    
    public double calcularTotal() { 
    	return calcularSubtotal() + calcularIVA()+this.extras; 
    	}
    
}
