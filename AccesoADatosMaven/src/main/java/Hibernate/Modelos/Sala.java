package Hibernate.Modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSala;
	private String nombre;
	private int capacidad;

	@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
	private List<Reunion> reuniones;

	public Sala(String nombre) {
		super();
		this.nombre = nombre;
		this.capacidad = 5;
		this.reuniones = new ArrayList<>();
	}

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

	public List<Reunion> getReuniones() {
		return reuniones;
	}

	public void setReuniones(List<Reunion> reuniones) {
		this.reuniones = reuniones;
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
		return "Sala [idSala=" + idSala + ", nombre=" + nombre + ", capacidad=" + capacidad + ", reuniones=" + reuniones
				+ "]";
	}

	public Sala() {
		super();
	}
	
	public void addReunion(Reunion r) {
		this.reuniones.add(r);
		r.setSala(this);
	}

	
}
