package modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSala;
	
	private String nombre;
	private int capacidad;
	
	@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
	private Set<Cine> cines;
	
	@ManyToMany(mappedBy = "sala", cascade = CascadeType.ALL)
	private Set<Pelicula> peliculas;

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Set<Cine> getCines() {
		return cines;
	}

	public void setCines(Set<Cine> cines) {
		this.cines = cines;
	}

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSala);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return idSala == other.idSala;
	}

	

	@Override
	public String toString() {
		return "Sala [idSala=" + idSala + ", nombre=" + nombre + ", capacidad=" + capacidad + ", peliculas=" + peliculas
				+ "]";
	}

	public Sala() {
		super();
		this.cines = new HashSet<>();
		this.peliculas = new HashSet<>();
	}

	public Sala(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	
	
}
