package MongoDB.Modelos;

public class Habitaciones {

	private TipoHabitacion tipo;
	private float precio;
	private int capacidad;
	private boolean disponible;
	public TipoHabitacion getTipo() {
		return tipo;
	}
	public void setTipo(TipoHabitacion tipo) {
		this.tipo = tipo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	@Override
	public String toString() {
		return "Habitaciones [tipo=" + tipo + ", precio=" + precio + ", capacidad=" + capacidad + ", disponible="
				+ disponible + "]";
	}
	public Habitaciones(TipoHabitacion tipo, float precio, int capacidad, boolean disponible) {
		super();
		this.tipo = tipo;
		this.precio = precio;
		this.capacidad = capacidad;
		this.disponible = disponible;
	}
	
	
}
