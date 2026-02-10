package Hibernate.Controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Hibernate.Modelos.Equipo;
import Hibernate.Modelos.Fase;
import Hibernate.Modelos.Jugador;
import Hibernate.Repositorio.EquipoDao;
import Hibernate.Repositorio.FaseDao;
import Hibernate.Repositorio.JugadorDao;



public class Gestiona {
	private static final Logger logger = LogManager.getLogger(Gestiona.class);

	
	public static void main(String[] args) {
        // Inicialización de DAOs
        EquipoDao equipoDao = new EquipoDao();
        JugadorDao jugadorDao = new JugadorDao();
        FaseDao faseDao = new FaseDao();

        logger.info("--- Iniciando Gestión del Torneo Rocket League ---");

        // 1. Generar 4 equipos con 2 participantes cada uno
        // Equipo 1
        Equipo e1 = new Equipo("Alpha Team");
        equipoDao.create(e1);
        Jugador j1 = new Jugador("Juan", e1); j1.setDni("1113145111A");
        Jugador j2 = new Jugador("Pedro", e1); j2.setDni("2212432122B");
        jugadorDao.create(j1); jugadorDao.create(j2);

        // Equipo 2
        Equipo e2 = new Equipo("Beta Squad");
        equipoDao.create(e2);
        Jugador j3 = new Jugador("Luis", e2); j3.setDni("332453133133C");
        Jugador j4 = new Jugador("Ana", e2); j4.setDni("4441254144D");
        jugadorDao.create(j3); jugadorDao.create(j4);

        // Equipo 3
        Equipo e3 = new Equipo("Gamma Warriors");
        equipoDao.create(e3);
        Jugador j5 = new Jugador("Marta", e3); j5.setDni("55152455555E");
        Jugador j6 = new Jugador("Elena", e3); j6.setDni("6612456666F");
        jugadorDao.create(j5); jugadorDao.create(j6);

        // Equipo 4
        Equipo e4 = new Equipo("Delta Force");
        equipoDao.create(e4);
        Jugador j7 = new Jugador("Ivan", e4); j7.setDni("11772457777G");
        Jugador j8 = new Jugador("Sara", e4); j8.setDni("1888245888H");
        jugadorDao.create(j7); jugadorDao.create(j8);

        // 2. Registrar equipos en Semifinal y asignar puntuaciones
        Fase semifinal = new Fase("Semifinal", LocalDateTime.now());
        faseDao.create(semifinal);

        // Asignamos puntos (según enunciado: clasificarán los de mayor puntuación)
        e1.setPuntosAcumulados(80);
        e2.setPuntosAcumulados(95);
        e3.setPuntosAcumulados(40);
        e4.setPuntosAcumulados(100);

        // Añadimos equipos a la fase y actualizamos (merge)
        semifinal.addEquipo(e1);
        semifinal.addEquipo(e2);
        semifinal.addEquipo(e3);
        semifinal.addEquipo(e4);
        faseDao.update(semifinal); // O mergeaObjeto según tu DAO

        // 3. Registrar los 2 con mayor puntuación en la Fase Final (E4 y E2)
        Fase faseFinal = new Fase("Gran Final", LocalDateTime.now().plusHours(5));
        faseDao.create(faseFinal);
        
        faseFinal.addEquipo(e4);
        faseFinal.addEquipo(e2);
        faseDao.update(faseFinal);

        logger.info("--- Listado de Equipos Registrados ---");
        List<Equipo> equipos = equipoDao.getAll();
        for (Equipo e : equipos) {
            logger.debug(e);
        }

        logger.info("--- Listado de Fases y sus Equipos ---");
        List<Fase> fases = faseDao.getAll();
        for (Fase f : fases) {
            logger.debug("Fase: " + f.getNombreFase() + " | Equipos: " + f.getEquipos().size());
        }
    }
}