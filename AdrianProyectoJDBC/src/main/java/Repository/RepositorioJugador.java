package Repository;

import java.sql.*;

import Exceptions.MiExcepcion;
import Models.Jugador;
import Utiles.MySqlConector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositorioJugador {

    private static final Logger logger = LogManager.getLogger(RepositorioJugador.class);
    private MySqlConector mysqlConector;

    public RepositorioJugador() throws MiExcepcion {
        mysqlConector = new MySqlConector();
    }

    public void darAltaJugador(Jugador jugador) {
    	String sql = "INSERT INTO jugadores (nombre, email, puntosTotales) VALUES (?, ?, ?)";

    	try (PreparedStatement ps = mysqlConector.getConnect()
    	        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

    	    ps.setString(1, jugador.getNombre());
    	    ps.setString(2, jugador.getEmail());
    	    ps.setInt(3, jugador.getPuntosTotales());
    	    ps.executeUpdate();

    	    ResultSet rs = ps.getGeneratedKeys();
    	    if (rs.next()) {
    	        jugador.setId(rs.getInt(1)); // 🔑 AQUÍ ESTÁ LA CLAVE
    	    }

    	    logger.info("Jugador dado de alta: {}", jugador.getNombre());

    	} catch (SQLException e) {
    	    logger.error("Error al dar de alta jugador", e);
    	}
    }

    public void sumarPuntos(int idJugador, int puntos) {
        String sql = "UPDATE jugadores SET puntosTotales = puntosTotales + ? WHERE id = ?";

        try (PreparedStatement ps = mysqlConector.getConnect().prepareStatement(sql)) {
            ps.setInt(1, puntos);
            ps.setInt(2, idJugador);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error al actualizar puntos", e);
        }
    }

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
            logger.error("Error obteniendo jugador con más puntos", e);
        }
    }

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
}
