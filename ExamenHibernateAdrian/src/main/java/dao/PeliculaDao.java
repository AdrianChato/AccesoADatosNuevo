package dao;

import modelos.Pelicula;
import util.AbstractDao;

public class PeliculaDao extends AbstractDao<Pelicula>{

	public PeliculaDao() {
		setClase(Pelicula.class);
	}

}