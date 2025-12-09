package MongoDB.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

	private String idHotel;
	private String nombre;
	private int estrellas;
	private boolean admiteMascotas;
	private String fecha_apertura;
	private Ubicacion ubicacion;
	private List <Habitaciones> habitaciones;
	public String getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(String idHotel) {
		this.idHotel = idHotel;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	public boolean isAdmiteMascotas() {
		return admiteMascotas;
	}
	public void setAdmiteMascotas(boolean admiteMascotas) {
		this.admiteMascotas = admiteMascotas;
	}
	public String getFecha_apertura() {
		return fecha_apertura;
	}
	public void setFecha_apertura(String fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public List<Habitaciones> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<Habitaciones> habitaciones) {
		this.habitaciones = habitaciones;
	}
	@Override
	public String toString() {
		return "Hotel [idHotel=" + idHotel + ", nombre=" + nombre + ", estrellas=" + estrellas + ", admiteMascotas="
				+ admiteMascotas + ", fecha_apertura=" + fecha_apertura + ", ubicacion=" + ubicacion + ", habitaciones="
				+ habitaciones + "]";
	}
	public Hotel(String idHotel, String nombre, int estrellas, boolean admiteMascotas, String fecha_apertura,
			Ubicacion ubicacion) {
		super();
		this.idHotel = idHotel;
		this.nombre = nombre;
		this.estrellas = estrellas;
		this.admiteMascotas = admiteMascotas;
		this.fecha_apertura = fecha_apertura;
		this.ubicacion = ubicacion;
		this.habitaciones = new ArrayList<>();
	}
	
	
}
