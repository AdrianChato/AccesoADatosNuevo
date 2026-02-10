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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cine")
public class Cine {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCine;
	
	@Column(nullable = false)
	private String nombre;
	
	private String ubicacion;
	
	@OneToMany(mappedBy = "cines", cascade = CascadeType.ALL)
	private Set<Sala> salas;

	public int getIdCine() {
		return idCine;
	}

	public void setIdCine(int idCine) {
		this.idCine = idCine;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Set<Sala> getSalas() {
		return salas;
	}

	public void setSalas(Set<Sala> salas) {
		this.salas = salas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cine other = (Cine) obj;
		return idCine == other.idCine;
	}

	@Override
	public String toString() {
		return "Cine [idCine=" + idCine + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", salas=" + salas + "]";
	}

	public Cine() {
		super();
		this.salas = new HashSet<>();
	}

	public Cine(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	
	
	
}
