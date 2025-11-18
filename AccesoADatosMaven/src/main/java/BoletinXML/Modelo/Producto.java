package BoletinXML.Modelo;

import java.util.Objects;

public class Producto {

	private String id;
	private boolean enVenta;
	private String nombre;
	private double precio;
	private int stock;
	private String fecha;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEnVenta() {
		return enVenta;
	}
	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(enVenta, fecha, id, nombre, precio, stock);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return enVenta == other.enVenta && Objects.equals(fecha, other.fecha) && id == other.id
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio) && stock == other.stock;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", enVenta=" + enVenta + ", nombre=" + nombre + ", precio=" + precio + ", stock="
				+ stock + ", fecha=" + fecha + "]";
	}
	public Producto() {
		super();
		this.id = id;
		this.enVenta = enVenta;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.fecha = fecha;
	}
	
	
	
}
