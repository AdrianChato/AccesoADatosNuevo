package Hibernate.Modelos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reunion")
public class Reunion {
	// Este campo es la clave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReunion;
	
	@ManyToMany( mappedBy="reuniones", cascade= CascadeType.ALL)
	private Set<Persona> personas;
//Es obligatorio usar la notación @Column(name="nombreCampo")
	// Si las columnas de la tablas se llaman diferentes que los atributos
	// @Column(name="fecha")
	private LocalDateTime fecha;
	// @Column(name="asunto")
	private String asunto; 
// Generamos el constructor sin parámetros y los métodos get/set
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idSala")
	private Sala sala;
	
	public void addPersona(Persona p) {
		this.personas.add(p);
		if (!p.getReuniones().contains(this)) {
			p.getReuniones().add(this);
		}
	}
	
	public void removePersona(Persona p) {
		this.personas.remove(p);
		if (!p.getReuniones().contains(this)) {
			p.getReuniones().remove(this);
		}
	}
	
	public Set<Persona> getPersonas(){
		return personas;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public int getIdReunion() {
		return idReunion;
	}
	public void setIdReunion(int idReunion) {
		this.idReunion = idReunion;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public Reunion(LocalDateTime fecha, String asunto, Sala sala) {
		super();
		this.fecha = fecha;
		this.asunto = asunto;
		this.sala = sala;
		this.personas = new HashSet<>();
	}
	public Reunion() {
		super();
	}
	@Override
	public String toString() {
		return "Reunion [idReunion=" + idReunion + ", fecha=" + fecha + ", asunto=" + asunto + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idReunion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reunion other = (Reunion) obj;
		return idReunion == other.idReunion;
	}


	
	
	
	
	
	
}

