package Controller;

import java.time.LocalDate;

import Exceptions.MiExcepcion;
import Models.Jugador;
import Models.Partida;
import Models.TipoResultado;
import Repository.RepositorioJugador;
import Repository.RepositorioPartida;
import Service.TorneoService;

public class GestionaTorneo {

    public static void main(String[] args) {

        try {
            RepositorioJugador repoJugador = new RepositorioJugador();
            RepositorioPartida repoPartida = new RepositorioPartida();
            TorneoService servicio = new TorneoService(repoJugador, repoPartida);

            Jugador j1 = new Jugador("JCViejo", "JCViejo@mail.com", "jici");
            Jugador j2 = new Jugador("Rendon", "Rendon@mail.com", "alcala");
            Jugador j3 = new Jugador("Fernando", "Fernando@mail.com", "loco");
            Jugador j4 = new Jugador("Maste", "Maste@mail.com", "noviene");

            servicio.darAltaJugador(j1);
            servicio.darAltaJugador(j2);
            servicio.darAltaJugador(j3);
            servicio.darAltaJugador(j4);

            servicio.actualizarPuntuacionAcertante(1, TipoResultado.TODOS);
            servicio.crearPartida(new Partida(j1, LocalDate.now(), TipoResultado.ALGUNOS));
            servicio.crearPartida(new Partida(j2, LocalDate.now(), TipoResultado.TODOS));
            servicio.crearPartida(new Partida(j3, LocalDate.now(), TipoResultado.NADIE));
            servicio.crearPartida(new Partida(j4, LocalDate.now(), TipoResultado.ALGUNOS));
            servicio.crearPartida(new Partida(j1, LocalDate.now(), TipoResultado.TODOS));

            // Sexta partida → excepción
            servicio.crearPartida(new Partida(j2, LocalDate.now(), TipoResultado.ALGUNOS));
			
            servicio.mostrarJugadorConMasPuntos();
            servicio.mostrarRanking();
            servicio.listarPartidas();
        } catch (MiExcepcion e) {
            System.err.println(e.getMessage());
        }
    }
}
