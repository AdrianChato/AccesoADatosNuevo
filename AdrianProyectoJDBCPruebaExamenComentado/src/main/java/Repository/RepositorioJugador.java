package Repository;

import java.sql.*;

import Exceptions.MiExcepcion;
import Models.Jugador;
import Utiles.MySqlConector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositorioJugador {

    private static final Logger logger = LogManager.getLogger(RepositorioJugador.class);

    // Objeto que gestiona la conexión con la base de datos
    private MySqlConector mysqlConector;

    // Constructor: crea la conexión
    public RepositorioJugador() throws MiExcepcion {
        mysqlConector = new MySqlConector();
    }

    /**
     * INSERT: da de alta un jugador en la base de datos
     */
    public void darAltaJugador(Jugador jugador) {

        // Consulta SQL con parámetros
        String sql = "INSERT INTO jugadores (nombre, email, puntosTotales) VALUES (?, ?, ?)";

        try (PreparedStatement ps = mysqlConector.getConnect()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Asignamos valores a los parámetros
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getEmail());
            ps.setInt(3, jugador.getPuntosTotales());

            // Ejecutamos el INSERT
            ps.executeUpdate();

            // Recuperamos el ID autogenerado
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                jugador.setId(rs.getInt(1));
            }

            logger.info("Jugador dado de alta: {}", jugador.getNombre());

        } catch (SQLException e) {
            logger.error("Error al insertar jugador", e);
        }
    }

    /**
     * UPDATE: suma puntos a un jugador
     */
    public void sumarPuntos(int idJugador, int puntos) {

        String sql = "UPDATE jugadores SET puntosTotales = puntosTotales + ? WHERE id = ?";

        try (PreparedStatement ps = mysqlConector.getConnect().prepareStatement(sql)) {

            ps.setInt(1, puntos);
            ps.setInt(2, idJugador);

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error al sumar puntos", e);
        }
    }

    /**
     * SELECT: muestra el jugador con más puntos
     */
    public void mostrarJugadorConMasPuntos() {

        String sql = "SELECT nombre, puntosTotales FROM jugadores ORDER BY puntosTotales DESC LIMIT 1";

        try (Statement st = mysqlConector.getConnect().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                logger.info("Jugador con más puntos: {} ({} puntos)",
                        rs.getString("nombre"),
                        rs.getInt("puntosTotales"));
            }

        } catch (SQLException e) {
            logger.error("Error al obtener jugador con más puntos", e);
        }
    }

    /**
     * SELECT: ranking completo de jugadores
     */
    public void mostrarRanking() {

        String sql = "SELECT nombre, puntosTotales FROM jugadores ORDER BY puntosTotales DESC";

        try (Statement st = mysqlConector.getConnect().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                logger.info("Jugador: {} | Puntos: {}",
                        rs.getString("nombre"),
                        rs.getInt("puntosTotales"));
            }

        } catch (SQLException e) {
            logger.error("Error mostrando ranking", e);
        }
    }

    /**
     * JOIN: muestra jugadores junto al número de partidas narradas
     * ⚠️ MUY IMPORTANTE PARA EXAMEN
     */
    public void mostrarJugadoresConPartidasNarradas() {

        // Consulta JOIN usando Strings normales
        String sql =
            "SELECT j.nombre, COUNT(p.id) AS total_partidas " +
            "FROM jugadores j " +
            "LEFT JOIN partidas p ON j.id = p.narrador_id " +
            "GROUP BY j.nombre " +
            "ORDER BY total_partidas DESC";

        try (Statement st = mysqlConector.getConnect().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                logger.info("Jugador: {} | Partidas narradas: {}",
                        rs.getString("nombre"),
                        rs.getInt("total_partidas"));
            }

        } catch (SQLException e) {
            logger.error("Error en JOIN jugadores-partidas", e);
        }
    }

}
