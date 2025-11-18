package BoletinXML.Modelo;

import java.util.List;
import java.util.Objects;

public class Equipo {

	private String nombre;
	private int puntuacion;
	private int identificadorequipo;
	List <Piloto> pilotos;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getIdentificadorequipo() {
		return identificadorequipo;
	}
	public void setIdentificadorequipo(int identificadorequipo) {
		this.identificadorequipo = identificadorequipo;
	}
	public List<Piloto> getPilotos() {
		return pilotos;
	}
	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}
	@Override
	public int hashCode() {
		return Objects.hash(identificadorequipo, nombre, pilotos, puntuacion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return identificadorequipo == other.identificadorequipo && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pilotos, other.pilotos) && puntuacion == other.puntuacion;
	}
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", puntuacion=" + puntuacion + ", identificadorequipo="
				+ identificadorequipo + ", pilotos=" + pilotos + "]";
	}
	public Equipo() {
		super();
	}
	public Equipo(List<Piloto> pilotos) {
		super();
		this.pilotos = pilotos;
	}
	
	
}
