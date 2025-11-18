package unidad1.simulacro1.gestiona;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import unidad1.simulacro1.modelo.Equipo;
import unidad1.simulacro1.modelo.Piloto;
import unidad1.simulacro1.repositorio.RepositorioEquipos;
import unidad1.simulacro1.servicios.ServicioEquipos;
import unidad1.simulacro1.utilidades.XMLDomEquipo;
import unidad1.simulacro1.utilidades.XMLDomPiloto;
import unidad1.simulacro1.utilidades.UtilidadesJsonPilotos;

public class GestionaClasificacion {
    private static final Logger logger = LogManager.getLogger(GestionaClasificacion.class);

    public static void main(String[] args) throws Exception {
        XMLDomEquipo xmlEquipos = new XMLDomEquipo();
        XMLDomPiloto xmlPilotos = new XMLDomPiloto();

        // 1️ Leer equipos desde el XML
        Set<Equipo> equipos = xmlEquipos.leerEquiposDesdeXML("formula1.xml");

        // 2️ Leer pilotos desde el XML
        Set<Piloto> pilotos = xmlPilotos.leerPilotosDesdeXML("formula1.xml");

        // 3️ Crear repositorio y servicio
        RepositorioEquipos repo = new RepositorioEquipos();
        ServicioEquipos servicio = new ServicioEquipos(repo);

        // 4️ Cargar equipos en el repositorio
        for (Equipo e : equipos) {
            repo.addEquipo(e);
        }

        // 5️ Asignar pilotos a sus equipos
        servicio.añadirPilotos(pilotos);

        // 6️ Mostrar resumen de equipos con sus pilotos
        for (Equipo e : repo.recuperarEquipos()) {
            logger.info("Equipo: {} ({} pts) - Pilotos: {}", e.getNombreEquipo(), e.getPuntos(), e.getPilotos().size());
        }

        // 7️ Obtener pilotos con más de 4 puntos
        Set<Piloto> destacados = servicio.obtenerPilotosConMasDeXPuntos(4);
        logger.info("Pilotos con más de 4 puntos: {}", destacados.size());

        // 8️ Generar JSON usando UtilidadesJsonPilotos
        UtilidadesJsonPilotos utilJson = new UtilidadesJsonPilotos();
        utilJson.escribePilotosJson(destacados, "src/main/resources/pilotosmasde4.json");

    }
}
