package MongoDB.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
	private int id;
	private String name;
	private double notaMedia;
	private List<String> aficiones;
	private List<Puntuacion> puntuaciones;
	private Direccion direccion;

	public Estudiante() {
		super();
		this.aficiones = new ArrayList<>();
		this.puntuaciones = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}

	public List<String> getAficiones() {
		return aficiones;
	}

	public void setAficiones(List<String> aficiones) {
		this.aficiones = aficiones;
	}

	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Estudiante(int id, String name, double notaMedia, List<String> aficiones, List<Puntuacion> puntuaciones,
			Direccion direccion) {
		super();
		this.id = id;
		this.name = name;
		this.notaMedia = notaMedia;
		this.aficiones = aficiones;
		this.puntuaciones = puntuaciones;
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", name=" + name + ", notaMedia=" + notaMedia + ", aficiones=" + aficiones
				+ ", puntuaciones=" + puntuaciones + ", direccion=" + direccion + "]";
	}

	

}
