package Hibernate.Modelos;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "acta")
public class Acta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idActa;
	private String contenido;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn (name="idReunion")
	private Reunion reunion;
	
	public Acta() {
		super();
	}
	public int getIdActa() {
		return idActa;
	}
	public void setIdActa(int idActa) {
		this.idActa = idActa;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Reunion getReunion() {
		return reunion;
	}
	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
	@Override
	public int hashCode() {
		return Objects.hash(contenido, idActa, reunion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acta other = (Acta) obj;
		return Objects.equals(contenido, other.contenido) && idActa == other.idActa
				&& Objects.equals(reunion, other.reunion);
	}
	@Override
	public String toString() {
		return "Acta [idActa=" + idActa + ", contenido=" + contenido + ", reunion=" + reunion + "]";
	}
	public Acta(String contenido, Reunion reunion) {
		super();
		this.contenido = contenido;
		this.reunion = reunion;
	}
	
	
}
