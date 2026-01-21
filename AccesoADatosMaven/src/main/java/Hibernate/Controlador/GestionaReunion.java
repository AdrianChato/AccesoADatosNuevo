package Hibernate.Controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Hibernate.Modelos.Acta;
import Hibernate.Modelos.Persona;
import Hibernate.Modelos.Reunion;
import Hibernate.Modelos.Sala;
import Hibernate.Repositorio.RepositorioActa;
import Hibernate.Repositorio.RepositorioPersona;
import Hibernate.Repositorio.RepositorioReunion;
import Hibernate.Repositorio.RepositorioSala;


public class GestionaReunion {
	private static final Logger logger =LogManager.getLogger(GestionaReunion.class);

	public static void main(String[] args) {
		
		RepositorioReunion repo = new RepositorioReunion();
		RepositorioSala repoSala = new RepositorioSala();
		RepositorioActa repoActa = new RepositorioActa();
		RepositorioPersona repoPersona = new RepositorioPersona();
		Sala sala = new Sala("salita de estar nueva");
		Reunion reunion = new Reunion(LocalDateTime.now().plusDays(3), "Reunion futura1, prueba de las actas",sala);
		sala.addReunion(reunion);
		repo.create(reunion);
		repo.mergeaObjeto(reunion);
	
		Acta acta = new Acta("Huevodfdfgf", reunion);
		Persona p = new Persona();
		repoPersona.create(p);
		p.addReunion(reunion);
		repoPersona.update(p);
		/*repo.create(reunion);*/
		/*repoSala.create(sala);
		repoActa.create(acta);*/
		/*List<Reunion> reuniones = repo.getAllReuniones();*/
		/*List<Sala> salas = repoSala.getAllSala();*/
		List<Acta> actas = repoActa.getAllActa();
		
		/*for (Reunion r : reuniones) {
			logger.debug(r);
		}*/
		
		for (Acta c : actas) {
			logger.debug(c);
		}
		
		/*for (Sala s : salas) {
			logger.debug(s);
		}*/
		
		
	}

}
