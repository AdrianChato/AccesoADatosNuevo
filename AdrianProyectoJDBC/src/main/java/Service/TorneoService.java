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

    public void darAltaJugador(Jugador jugador) {
        repoJugador.darAltaJugador(jugador);
    }

    public void crearPartida(Partida partida) throws MiExcepcion {
        if (repoPartida.contarPartidas() >= 5) {
            throw new MiExcepcion("No se pueden crear más de 5 partidas");
        }
        repoPartida.darAltaPartida(partida);
    }

    public void actualizarPuntuacionNarrador(int idJugador, TipoResultado resultado) {
        if (resultado == TipoResultado.ALGUNOS) {
            repoJugador.sumarPuntos(idJugador, 3);
        }
    }

    public void actualizarPuntuacionNOAcertante(int idJugador, TipoResultado resultado) {
        if (resultado == TipoResultado.TODOS || resultado == TipoResultado.NADIE) {
            repoJugador.sumarPuntos(idJugador, 2);
        }
    }

    public void actualizarPuntuacionAcertante(int idJugador, TipoResultado resultado) {
        if (resultado == TipoResultado.TODOS || resultado == TipoResultado.NADIE) {
            repoJugador.sumarPuntos(idJugador, 2);
        } else if (resultado == TipoResultado.ALGUNOS) {
            repoJugador.sumarPuntos(idJugador, 3);
        }
    }

    public void mostrarJugadorConMasPuntos() {
        repoJugador.mostrarJugadorConMasPuntos();
    }

    public void mostrarRanking() {
        repoJugador.mostrarRanking();
    }

    public void listarPartidas() {
        repoPartida.listarPartidasPorFecha();
    }
}
