package Service;

import Exceptions.MiExcepcion;
import Models.Jugador;
import Models.Partida;
import Models.TipoResultado;
import Repository.RepositorioJugador;
import Repository.RepositorioPartida;

public class TorneoService {

    private RepositorioJugador repoJugador;
    private RepositorioPartida repoPartida;

    public TorneoService(RepositorioJugador repoJugador, RepositorioPartida repoPartida) {
        this.repoJugador = repoJugador;
        this.repoPartida = repoPartida;
    }

    /**
     * Da de alta un jugador
     */
    public void darAltaJugador(Jugador jugador) {
        repoJugador.darAltaJugador(jugador);
    }

    /**
     * Regla de negocio: máximo 5 partidas
     */
    public void crearPartida(Partida partida) throws MiExcepcion {

        // Validación antes de acceder a BD
        if (repoPartida.contarPartidas() >= 5) {
            throw new MiExcepcion("No se pueden crear más de 5 partidas");
        }

        repoPartida.darAltaPartida(partida);
    }

    /**
     * Aplica puntuación al narrador según el resultado
     */
    public void actualizarPuntuacionNarrador(int idJugador, TipoResultado resultado) {

        if (resultado == TipoResultado.ALGUNOS) {
            repoJugador.sumarPuntos(idJugador, 3);
        }
    }

    /**
     * Puntuación para jugadores que no acertaron
     */
    public void actualizarPuntuacionNOAcertante(int idJugador, TipoResultado resultado) {

        if (resultado == TipoResultado.TODOS || resultado == TipoResultado.NADIE) {
            repoJugador.sumarPuntos(idJugador, 2);
        }
    }

    /**
     * Puntuación para jugadores que sí acertaron
     */
    public void actualizarPuntuacionAcertante(int idJugador, TipoResultado resultado) {

        if (resultado == TipoResultado.TODOS || resultado == TipoResultado.NADIE) {
            repoJugador.sumarPuntos(idJugador, 2);
        } else if (resultado == TipoResultado.ALGUNOS) {
            repoJugador.sumarPuntos(idJugador, 3);
        }
    }

    /**
     * Muestra el jugador con más puntos
     */
    public void mostrarJugadorConMasPuntos() {
        repoJugador.mostrarJugadorConMasPuntos();
    }

    /**
     * Ranking general
     */
    public void mostrarRanking() {
        repoJugador.mostrarRanking();
    }

    /**
     * Listado simple de partidas
     */
    public void listarPartidas() {
        repoPartida.listarPartidasPorFecha();
    }

    /**
     * JOIN: partidas con nombre del narrador
     */
    public void mostrarPartidasConNarrador() {
        repoPartida.listarPartidasConNarrador();
    }

    /**
     * JOIN: jugadores con número de partidas narradas
     */
    public void mostrarJugadoresConPartidas() {
        repoJugador.mostrarJugadoresConPartidasNarradas();
    }
}
