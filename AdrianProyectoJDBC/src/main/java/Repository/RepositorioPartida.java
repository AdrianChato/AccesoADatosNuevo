package Repository;

import java.sql.*;

import Exceptions.MiExcepcion;
import Models.Partida;
import Utiles.MySqlConector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositorioPartida {

    private static final Logger logger = LogManager.getLogger(RepositorioPartida.class);
    private MySqlConector mysqlConector;

    public RepositorioPartida() throws MiExcepcion {
        mysqlConector = new MySqlConector();
    }

    public int contarPartidas() {
        String sql = "SELECT COUNT(*) FROM partidas";

        try (Statement st = mysqlConector.getConnect().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            logger.error("Error contando partidas", e);
        }
        return 0;
    }

    public void darAltaPartida(Partida partida) {
        String sql = "INSERT INTO partidas (narrador_id, fecha, resultado) VALUES (?, ?, ?)";

        try (PreparedStatement ps = mysqlConector.getConnect().prepareStatement(sql)) {
            ps.setInt(1, partida.getNarrador().getId());
            ps.setDate(2, Date.valueOf(partida.getFecha()));
            ps.setString(3, partida.getResultado().name());
            ps.executeUpdate();

            logger.info("Partida dada de alta");

        } catch (SQLException e) {
            logger.error("Error al insertar partida", e);
        }
    }

    public void listarPartidasPorFecha() {
        String sql = "SELECT * FROM partidas ORDER BY fecha";

        try (Statement st = mysqlConector.getConnect().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                logger.info("Partida ID {} | Fecha {} | Resultado {}",
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getString("resultado"));
            }
        } catch (SQLException e) {
            logger.error("Error listando partidas", e);
        }
    }
}
