package Modelos;

public class Propietario {
	
	private String dni;
	private String nombre;
	private String direccion;
	private int telefono;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Propietario [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ "]";
	}
	public Propietario(String dni, String nombre, String direccion, int telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
	

}
