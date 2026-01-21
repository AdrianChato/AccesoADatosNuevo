package Hibernate.Modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dni;
	
	
	@ManyToMany()
	private Set<Reunion> reuniones;
	
	public void addReunion (Reunion r) {
		this.reuniones.add(r);
		if (!r.getPersonas().contains(this)) {
			r.getPersonas().add(this);
		}
	}
	
	public void removeReunion (Reunion r) {
		this.reuniones.remove(r);
		if (!r.getPersonas().contains(this)) {
			r.getPersonas().remove(this);
		}
	}
	
	public Set<Reunion> getReuniones() {
		return reuniones;
	}

	public Persona() {
		super();
		this.reuniones = new HashSet<>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, reuniones);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return dni == other.dni && Objects.equals(reuniones, other.reuniones);
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + "]";
	}

	
}
