package unidad1.simulacro1.servicios;

import java.util.Set;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import unidad1.simulacro1.modelo.Equipo;
import unidad1.simulacro1.modelo.Piloto;
import unidad1.simulacro1.repositorio.RepositorioEquipos;

public class ServicioEquipos {

    private static final Logger logger = LogManager.getLogger(ServicioEquipos.class);
    private final RepositorioEquipos repositorio;

    public ServicioEquipos(RepositorioEquipos repositorio) {
        this.repositorio = repositorio;
    }

    public void añadirPiloto(Piloto piloto) {
        if (piloto == null) return;

        String idPilotoStr = String.valueOf(piloto.getIdentificadorEquipo()).trim();

        Set<Equipo> equipos = repositorio.recuperarEquipos();
        if (equipos == null) {
            logger.warn("Repositorio no contiene equipos (recuperarEquipos() devolvió null).");
            return;
        }

        for (Equipo equipo : equipos) {
            String idEquipo = equipo.getIdentificadorEquipo();
            if (idEquipo == null) continue;
            if (idEquipo.trim().equals(idPilotoStr)) {
                equipo.getPilotos().add(piloto);
                //logger.debug("Piloto {} añadido al equipo {}", piloto.getNombrePiloto(), equipo.getNombreEquipo());
                return;
            }
        }

        logger.warn("No se encontró equipo para el piloto {} (ID equipo: {})", piloto.getNombrePiloto(), idPilotoStr);
    }

    public void añadirPilotos(Set<Piloto> pilotos) {
        if (pilotos == null) return;
        for (Piloto p : pilotos) añadirPiloto(p);
    }

    public Set<Piloto> obtenerPilotosConMasDeXPuntos(int puntos) {
        Set<Piloto> resultado = new HashSet<>();
        Set<Equipo> equipos = repositorio.recuperarEquipos();
        if (equipos == null) return resultado;

        for (Equipo e : equipos) {
            java.util.Collection<Piloto> pilotos = e.getPilotos(); // funciona con List o Set
            if (pilotos == null) continue;

            for (Piloto p : pilotos) {
                if (p.getPuntos() > puntos) {
                    resultado.add(p);
                }
            }
        }

        logger.info("Pilotos con más de {} puntos: {}", puntos, resultado.size());
        return resultado;
    }


}
