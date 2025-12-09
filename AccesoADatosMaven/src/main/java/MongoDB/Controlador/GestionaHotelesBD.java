package MongoDB.Controlador;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MongoDB.Config.MongoDBConexion;
import MongoDB.Modelos.Coordenadas;
import MongoDB.Modelos.Habitaciones;
import MongoDB.Modelos.Hotel;
import MongoDB.Modelos.TipoHabitacion;
import MongoDB.Modelos.Ubicacion;
import MongoDB.Servicios.HotelesServicios;

import com.mongodb.client.MongoDatabase;

public class GestionaHotelesBD {

    private static final Logger logger = LogManager.getLogger(GestionaHotelesBD.class);

    public static void main(String[] args) {

        logger.info("=== Iniciando aplicación Hoteles ===");

        // Conexión a MongoDB
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();

        // Crear servicio
        HotelesServicios servicio = new HotelesServicios(db);

        logger.info("Conexión establecida y servicio inicializado.");

        // ------------------- Insertar hotel -------------------
        Ubicacion ubicacion = new Ubicacion("Gran Vía", 10, 28004, new Coordenadas(40.4200f, -3.7050f));
        Hotel hotel1 = new Hotel("h101", "Grand Hotel Central", 5, true, "2000-05-01", ubicacion);

        hotel1.getHabitaciones().add(new Habitaciones(TipoHabitacion.INDIVIDUAL, 80.0f, 1, true));
        hotel1.getHabitaciones().add(new Habitaciones(TipoHabitacion.SUITE_JUNIOR, 250.0f, 2, true));

        servicio.save(hotel1);

        // ------------------- Leer todos los hoteles -------------------
        logger.info("Leyendo todos los hoteles...");
        List<Hotel> todos = servicio.read();
        logger.info("Total hoteles: " + todos.size());
        for (Hotel h : todos) {
            logger.info(h);
        }

        // ------------------- Búsqueda por ID -------------------
        logger.info("Buscando hotel con ID h101...");
        Hotel buscado = servicio.buscarPorId("h101");
        logger.info(buscado);

        // ------------------- Filtrar hoteles Madrid (5* o mascotas) -------------------
        logger.info("Filtrando hoteles en Madrid (5* o mascotas)...");
        List<Hotel> filtrados = servicio.filtrarHotelesMadridEstrellasOMascotas();
        for (Hotel h : filtrados) {
            logger.info(h);
        }

        // ------------------- Contar hoteles con Suite Junior -------------------
        long countSuite = servicio.contarSuiteJunior();
        logger.info("Hoteles con Suite Junior: " + countSuite);

        // ------------------- Añadir habitación -------------------
        logger.info("Añadiendo habitación Penthouse a h101...");
        Habitaciones penthouse = new Habitaciones(TipoHabitacion.SUITE_JUNIOR, 500.0f, 4, true);
        servicio.añadirHabitacion("h101", penthouse);

        // ------------------- Actualizar código postal Gran Vía -------------------
        logger.info("Actualizando código postal de Gran Vía a 28013...");
        servicio.actualizarCPGranVia("28013");

        // ------------------- Actualizar precio habitación Individual -------------------
        logger.info("Actualizando precio de habitación Individual de h101 a 90.0...");
        servicio.actualizarPrecioIndividual("h101", 90.0);

        // ------------------- Eliminar habitaciones caras -------------------
        logger.info("Eliminando habitaciones con precio > 300 en Grand Hotel Central...");
        servicio.eliminarHabitacionesCaras("Grand Hotel Central");

        // ------------------- Media de estrellas en Barcelona -------------------
        double media = servicio.mediaEstrellasBarcelona();
        logger.info("Media de estrellas en Barcelona: " + media);

        logger.info("=== FIN DE PRUEBAS HOTELS ===");
    }
}
