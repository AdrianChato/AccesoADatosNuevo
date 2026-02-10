package Hibernate.Modelos;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugador")
public class Jugador {
	@Id
	private String dni;
	private String nombre;
	@ManyToOne
	@JoinColumn (name = "idEquipo")
	private Equipo equipo;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(dni, other.dni);
	}
	@Override
	public String toString() {
		return "Jugador [dni=" + dni + ", nombre=" + nombre + "]";
	}
	public Jugador(String nombre, Equipo equipo) {
		super();
		this.nombre = nombre;
		this.equipo = equipo;
	}
	public Jugador() {
		super();
	}

	
	
	
	
	
}
