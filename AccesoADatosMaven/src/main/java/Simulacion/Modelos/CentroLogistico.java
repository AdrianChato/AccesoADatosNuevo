package Simulacion.Modelos;

import java.util.List;
import java.util.Objects;

public class CentroLogistico {

	private String id;
	private String nombre;
	private String ciudad;
	private int comedores;
	private List <Trabajador> personal;
	public CentroLogistico() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getComedores() {
		return comedores;
	}
	public void setComedores(int comedores) {
		this.comedores = comedores;
	}
	public List<Trabajador> getPersonal() {
		return personal;
	}
	public void setPersonal(List<Trabajador> personal) {
		this.personal = personal;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CentroLogistico other = (CentroLogistico) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "CentroLogistico [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", comedores=" + comedores
				+ ", personal=" + personal + "]";
	}
	
	
}
