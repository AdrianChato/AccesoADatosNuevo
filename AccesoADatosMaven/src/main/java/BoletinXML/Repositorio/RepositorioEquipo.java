package BoletinXML.Repositorio;

import java.util.ArrayList;
import java.util.List;

import BoletinXML.Modelo.Equipo;

public class RepositorioEquipo {

	private List <Equipo> equipos = new ArrayList<>();

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	
}
