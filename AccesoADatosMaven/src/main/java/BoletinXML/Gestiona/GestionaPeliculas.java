package BoletinXML.Gestiona;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BoletinXML.Modelo.Pelicula;
import BoletinXML.Utilidades.XMLDomPeliculas;
import Unidad1.Controlador.ControlaXML;

public class GestionaPeliculas {

	private static final Logger logger =LogManager.getLogger(GestionaPeliculas.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLDomPeliculas xml = new XMLDomPeliculas();
		try {
			List<Pelicula> pelis =xml.leerPeliculaDesdeXML("peliculas.xml");
			logger.debug(pelis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
