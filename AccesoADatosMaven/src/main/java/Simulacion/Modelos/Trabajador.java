package Simulacion.Modelos;

import java.time.LocalDate;
import java.util.Objects;

public class Trabajador {

	private String nombre;
	private String dni;
	private String fechaNacimiento;
	private TipoPersonal tipo;
	private String idCentro;
	@Override
	public String toString() {
		return "Trabajador [nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", tipo="
				+ tipo + ", idCentro=" + idCentro + "]";
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public TipoPersonal getTipo() {
		return tipo;
	}
	public void setTipo(TipoPersonal tipo) {
		this.tipo = tipo;
	}
	public String getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
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
		Trabajador other = (Trabajador) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
}
