package controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelos.Cine;
import modelos.Pelicula;
import modelos.Sala;
import servicio.ServicioFestival;


public class GestionaFestivalCine {

	private static final Logger logger =LogManager.getLogger(GestionaFestivalCine.class);

	public static void main(String[] args) {
		ServicioFestival servicio = new ServicioFestival();
		
		Cine c1 = new Cine("Cine 1");
		servicio.getCineDao().create(c1);
		Sala s1 = new Sala("sala 1");
		Sala s2 = new Sala("sala 2");
		servicio.getSalaDao().create(s1);
		servicio.getSalaDao().create(s2);
		c1.getSalas().add(s1);
		c1.getSalas().add(s2);
		Cine c2 = new Cine("Cine 2");
		servicio.getCineDao().create(c2);
		Sala s3 = new Sala("sala 3");
		Sala s4 = new Sala("sala 4");
		servicio.getSalaDao().create(s3);
		servicio.getSalaDao().create(s4);
		c1.getSalas().add(s3);
		c1.getSalas().add(s4);
		
		Pelicula p1 = new Pelicula("Pelicula 1");
		Pelicula p2 = new Pelicula("Pelicula 2");
		Pelicula p3 = new Pelicula("Pelicula 3");
		Pelicula p4 = new Pelicula("Pelicula 4");
		Pelicula p5 = new Pelicula("Pelicula 5");
		
		servicio.getPeliculaDao().create(p5);
		servicio.getPeliculaDao().create(p4);
		servicio.getPeliculaDao().create(p3);
		servicio.getPeliculaDao().create(p2);
		servicio.getPeliculaDao().create(p1);

		s1.getPeliculas().add(p5);
		s1.getPeliculas().add(p3);
		
	}
}
