package modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdPelicula;
	
	@Column(nullable = false)
	private String titulo;
	
	private String genero;
	private int duracion;
	
	@ManyToMany(mappedBy = "peliculas", cascade = CascadeType.ALL)
	private Set<Sala> salas;

	public int getIdPelicula() {
		return IdPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		IdPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Set<Sala> getSalas() {
		return salas;
	}

	public void setSalas(Set<Sala> salas) {
		this.salas = salas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IdPelicula);
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
		return IdPelicula == other.IdPelicula;
	}

	@Override
	public String toString() {
		return "Pelicula [IdPelicula=" + IdPelicula + ", titulo=" + titulo + ", genero=" + genero + ", duracion="
				+ duracion + ", salas=" + salas + "]";
	}

	public Pelicula() {
		super();
		this.salas = new HashSet<>();
	}

	public Pelicula( String titulo) {
		super();
		this.titulo = titulo;
	}
	
	
	
	
}
