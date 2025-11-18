package Unidad1.ejemploStream;

import java.util.List;

public class Persona {

	private String nombre;
	private List<Double>notas;
	public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Double> getNotas() {
		return notas;
	}
	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", notas=" + notas + "]";
	}
	
	
}
