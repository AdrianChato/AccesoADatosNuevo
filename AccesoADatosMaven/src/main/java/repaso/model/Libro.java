package repaso.model;

import java.time.LocalDate;
import java.util.Objects;

public class Libro{

	private String isbn;
	private String titulo;
	private String autor;
	private int año_publicacion;
	private Genero genero;
	private Editorial editorial;
	private int ejemplares_disponibles;

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}

	public Libro(String isbn, String titulo, String autor, int año_publicacion, Genero genero, Editorial editorial,
			int ejemplares_disponibles) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.año_publicacion = año_publicacion;
		this.genero = genero;
		this.editorial = editorial;
		this.ejemplares_disponibles = ejemplares_disponibles;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", año_publicacion="
				+ año_publicacion + ", genero=" + genero + ", editorial=" + editorial + ", ejemplares_disponibles="
				+ ejemplares_disponibles + "]";
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAño_publicacion() {
		return año_publicacion;
	}

	public void setAño_publicacion(int año_publicacion) {
		this.año_publicacion = año_publicacion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public int getEjemplares_disponibles() {
		return ejemplares_disponibles;
	}

	public void setEjemplares_disponibles(int ejemplares_disponibles) {
		this.ejemplares_disponibles = ejemplares_disponibles;
	}

	
}
