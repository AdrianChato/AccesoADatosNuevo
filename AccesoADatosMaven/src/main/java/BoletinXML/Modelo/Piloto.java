package BoletinXML.Modelo;

import java.util.Objects;

public class Piloto {
	
	private String nombre;
	private String pais;
	private int puntos;
	private int identificadorpiloto;
	private int identificadorequipo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getIdentificadorpiloto() {
		return identificadorpiloto;
	}
	public void setIdentificadorpiloto(int identificadorpiloto) {
		this.identificadorpiloto = identificadorpiloto;
	}
	public int getIdentificadorequipo() {
		return identificadorequipo;
	}
	public void setIdentificadorequipo(int identificadorequipo) {
		this.identificadorequipo = identificadorequipo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(identificadorequipo, identificadorpiloto, nombre, pais, puntos);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piloto other = (Piloto) obj;
		return identificadorequipo == other.identificadorequipo && identificadorpiloto == other.identificadorpiloto
				&& Objects.equals(nombre, other.nombre) && Objects.equals(pais, other.pais) && puntos == other.puntos;
	}
	@Override
	public String toString() {
		return "Piloto [nombre=" + nombre + ", pais=" + pais + ", puntos=" + puntos + ", identificadorpiloto="
				+ identificadorpiloto + ", identificadorequipo=" + identificadorequipo + "]";
	}
	
	

}
