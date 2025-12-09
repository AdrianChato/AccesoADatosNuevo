package MongoDB.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MongoDB.Modelos.Habitaciones;
import MongoDB.Modelos.Hotel;
import MongoDB.Repositorios.HotelesRepositorio;
import com.mongodb.client.MongoDatabase;

public class HotelesServicios {

    private static final Logger logger = LogManager.getLogger(HotelesServicios.class);
    private HotelesRepositorio repo;

    public HotelesServicios(MongoDatabase db) {
        this.repo = new HotelesRepositorio(db);
        logger.info("Servicio de Hoteles inicializado");
    }

    // ------------------- CRUD -------------------
    public void save(Hotel hotel) {
        repo.save(hotel);
        logger.info("Hotel guardado en servicio: " + hotel.getNombre());
    }

    public List<Hotel> read() {
        return repo.read();
    }

    public Hotel buscarPorId(String idHotel) {
        return repo.buscarPorId(idHotel);
    }

    public void actualizar(Hotel hotel) {
        repo.actualizar(hotel.getIdHotel(), repo.convertirHotelADocument(hotel));
        logger.info("Hotel actualizado en servicio: " + hotel.getIdHotel());
    }

    public void eliminar(String idHotel) {
        repo.eliminar(idHotel);
    }

    // ------------------- Operaciones especiales -------------------
    public List<Hotel> filtrarHotelesMadridEstrellasOMascotas() {
        return repo.filtrarHotelesMadridEstrellasOMascotas();
    }

    public long contarSuiteJunior() {
        return repo.contarSuiteJunior();
    }

    public void añadirHabitacion(String idHotel, Habitaciones habitacion) {
        repo.añadirHabitacion(idHotel, habitacion);
    }

    public void actualizarCPGranVia(String nuevoCP) {
        repo.actualizarCPGranVia(nuevoCP);
    }

    public void actualizarPrecioIndividual(String idHotel, double nuevoPrecio) {
        repo.actualizarPrecioIndividual(idHotel, nuevoPrecio);
    }

    public void eliminarHabitacionesCaras(String nombreHotel) {
        repo.eliminarHabitacionesCaras(nombreHotel);
    }

    public double mediaEstrellasBarcelona() {
        return repo.mediaEstrellasBarcelona();
    }

    // Acceso al repositorio si hace falta
    public HotelesRepositorio getRepo() {
        return repo;
    }

}
