package Hibernate.Modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Table(name = "fase")
public class Fase {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFase;
	private String nombreFase;
	private LocalDateTime fecha;

	@ManyToMany()
	private Set<Equipo> equipos;

	public int getIdFase() {
		return idFase;
	}

	public void setIdFase(int idFase) {
		this.idFase = idFase;
	}

	public String getNombreFase() {
		return nombreFase;
	}

	public void setNombreFase(String nombreFase) {
		this.nombreFase = nombreFase;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idFase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fase other = (Fase) obj;
		return idFase == other.idFase;
	}

	@Override
	public String toString() {
		return "Fase [idFase=" + idFase + ", nombreFase=" + nombreFase + ", fecha=" + fecha + 
				 "]";
	}

	public Fase(String nombreFase, LocalDateTime fecha) {
		this();
		this.nombreFase = nombreFase;
		this.fecha = fecha;
		
	}
	
	public void addEquipo(Equipo e) {
		equipos.add(e);
	}

	public Fase() {
		this.equipos = new HashSet<>();
	}
	
	
}
