package Hibernate.Controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Hibernate.Modelos.Alumno;
import Hibernate.Repositorio.RepositorioAlumno;

public class GestionaAlumno {
	
	private static final Logger logger =LogManager.getLogger(GestionaAlumno.class);

	public static void main(String[] args) {
		RepositorioAlumno repo = new RepositorioAlumno();
		Alumno a = new Alumno();
		repo.create(a);
		//logger.debug(repo.getAlumnosCB());
		//repo.getAlumnosNombreEmailCB();
		//repo.getAlumnos18CB(0);
		repo.getAlumnosNombreCB("Ana");
	}
}
