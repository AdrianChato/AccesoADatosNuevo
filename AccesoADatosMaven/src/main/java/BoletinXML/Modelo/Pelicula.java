package BoletinXML.Modelo;

import java.util.List;
import java.util.Objects;

public class Pelicula {

	private String titulo;
	private int año;
	private String director;
	private List<String> actores;

	public Pelicula() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		director = director;
	}

	public List<String> getActores() {
		return actores;
	}

	public void setActores(List<String> actores) {
		this.actores = actores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actores, año, director, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return Objects.equals(actores, other.actores) && año == other.año && Objects.equals(director, other.director)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", año=" + año + ", director=" + director + ", actores=" + actores + "]";
	}

}
