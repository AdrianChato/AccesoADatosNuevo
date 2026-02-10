package Hibernate.Modelos;

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
@Table(name = "equipo")
public class Equipo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEquipo;
	private String nombre;
	private int puntosAcumulados;
	
	@OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
	private Set<Jugador> jugadores;
	
	@ManyToMany(mappedBy = "equipos", cascade = CascadeType.ALL)
	private Set<Fase> fases;

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}

	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Set<Fase> getFases() {
		return fases;
	}

	public void setFases(Set<Fase> fases) {
		this.fases = fases;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEquipo);
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
		return idEquipo == other.idEquipo;
	}

	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombre=" + nombre + ", puntosAcumulados=" + puntosAcumulados
				+ "]";
	}

	public Equipo() {
		this.jugadores = new HashSet<>(); 
	    this.fases = new HashSet<>();     
	}

	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public void addJugador(Jugador j) {
		jugadores.add(j);
	}
	
	public void addFase(Fase f) {
		fases.add(f);
	}
}

