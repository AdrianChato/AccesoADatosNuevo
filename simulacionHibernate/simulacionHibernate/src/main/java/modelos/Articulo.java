package modelos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idArticulo;
	private String titulo;
	private int numPaginaInicio;
	private int numPaginaFin;

	@ManyToOne()
	@JoinColumn(name = "idRevista")
	private Revista revista;

	@ManyToMany()
	private Set<Autor> autores;

	public Articulo() {
		super();
		this.titulo = "";
		this.autores = new HashSet<Autor>();
	}

	public void addAutor(Autor a) {
		this.autores.add(a);
		if (!a.getArticulos().contains(this)) {
			a.getArticulos().add(this);
		}
	}

	public Articulo(String titulo) {
		super();
		this.titulo = titulo;
		this.autores = new HashSet<Autor>();
	}

	public Articulo(String titulo, int numPaginaInicio, int numPaginaFin) {
		super();
		this.titulo = titulo;
		this.numPaginaInicio = numPaginaInicio;
		this.numPaginaFin = numPaginaFin;
		this.autores = new HashSet<Autor>();
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumPaginaInicio() {
		return numPaginaInicio;
	}

	public void setNumPaginaInicio(int numPaginaInicio) {
		this.numPaginaInicio = numPaginaInicio;
	}

	public int getNumPaginaFin() {
		return numPaginaFin;
	}

	public void setNumPaginaFin(int numPaginaFin) {
		this.numPaginaFin = numPaginaFin;
	}

	@Override
	public String toString() {
		return "Articulo [idArticulo=" + idArticulo + ", titulo=" + titulo + ", numPaginaInicio=" + numPaginaInicio
				+ ", numPaginaFin=" + numPaginaFin + "]";
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public Revista getRevista() {
		return revista;
	}

	public void setRevista(Revista revista) {
		this.revista = revista;

	}

}
