package MongoDB.Repositorios;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import MongoDB.Modelos.Coordenadas;
import MongoDB.Modelos.Habitaciones;
import MongoDB.Modelos.Hotel;
import MongoDB.Modelos.TipoHabitacion;
import MongoDB.Modelos.Ubicacion;

public class HotelesRepositorio {

    private static final Logger logger = LogManager.getLogger(HotelesRepositorio.class);
    private static final String NOMBRE_COLECCION = "hoteles";
    private final MongoCollection<Document> coleccion;
    private List<Hotel> hoteles;

    public HotelesRepositorio(MongoDatabase db) {
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        this.hoteles = this.read();
        logger.info("Repositorio de Hoteles inicializado. Hoteles cargados: " + hoteles.size());
    }

    public List<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(List<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    // ------------------- CRUD -------------------
    public void save(Hotel hotel) {
        Document doc = convertirHotelADocument(hotel);
        coleccion.insertOne(doc);
        hoteles.add(hotel);
        logger.info("Hotel guardado: " + hotel.getNombre());
    }

    public List<Hotel> read() {
        List<Hotel> lista = new ArrayList<>();
        FindIterable<Document> docs = coleccion.find();
        for (Document doc : docs) {
            lista.add(convertirDocumentoAHotel(doc));
        }
        return lista;
    }

    public Hotel buscarPorId(String idHotel) {
        Document doc = coleccion.find(Filters.eq("idHotel", idHotel)).first();
        if (doc != null) {
            return convertirDocumentoAHotel(doc);
        } else {
            logger.warn("No se encontró hotel con ID: " + idHotel);
            return null;
        }
    }

    public void actualizar(String idHotel, Document hotelActualizado) {
        coleccion.replaceOne(Filters.eq("idHotel", idHotel), hotelActualizado);
        logger.info("Hotel actualizado: " + idHotel);
        this.hoteles = read();
    }

    public void eliminar(String idHotel) {
        coleccion.deleteOne(Filters.eq("idHotel", idHotel));
        logger.info("Hotel eliminado: " + idHotel);
        this.hoteles = read();
    }

    // ------------------- Operaciones especiales -------------------

    // Hoteles en Madrid con 5 estrellas o que admitan mascotas
    public List<Hotel> filtrarHotelesMadridEstrellasOMascotas() {
        List<Hotel> lista = new ArrayList<>();
        FindIterable<Document> docs = coleccion.find(
                Filters.and(
                        Filters.eq("ubicacion.ciudad", "Madrid"),
                        Filters.or(Filters.eq("estrellas", 5), Filters.eq("admiteMascotas", true))
                )
        );
        for (Document doc : docs) {
            lista.add(convertirDocumentoAHotel(doc));
        }
        logger.info("Hoteles filtrados en Madrid (5* o mascotas): " + lista.size());
        return lista;
    }

    // Contar hoteles con al menos una habitación SUITE_JUNIOR
    public long contarSuiteJunior() {
        long count = coleccion.countDocuments(Filters.elemMatch("habitaciones", Filters.eq("tipo", "SUITE_JUNIOR")));
        logger.info("Hoteles con Suite Junior: " + count);
        return count;
    }

    // Añadir habitación a hotel
    public void añadirHabitacion(String idHotel, Habitaciones habitacion) {
        Document habitacionDoc = convertirHabitacionADocument(habitacion);
        coleccion.updateOne(Filters.eq("idHotel", idHotel), new Document("$push", new Document("habitaciones", habitacionDoc)));
        logger.info("Habitación añadida al hotel: " + idHotel);
        this.hoteles = read();
    }

    // Actualizar código postal en Gran Vía
    public void actualizarCPGranVia(String nuevoCP) {
        coleccion.updateMany(Filters.eq("ubicacion.calle", "Gran Vía"),
                new Document("$set", new Document("ubicacion.codigoPostal", nuevoCP)));
        logger.info("Código postal actualizado en Gran Vía a: " + nuevoCP);
        this.hoteles = read();
    }

    // Actualizar precio de habitación tipo INDIVIDUAL en hotel específico
    public void actualizarPrecioIndividual(String idHotel, double nuevoPrecio) {
        coleccion.updateOne(
                Filters.eq("idHotel", idHotel),
                new Document("$set", new Document("habitaciones.$[elem].precio", nuevoPrecio))
                        .append("arrayFilters", List.of(Filters.eq("elem.tipo", "INDIVIDUAL")))
        );
        logger.info("Precio de habitación Individual actualizado en hotel: " + idHotel);
        this.hoteles = read();
    }

    // Eliminar habitaciones con precio > 300 en hotel por nombre
    public void eliminarHabitacionesCaras(String nombreHotel) {
        coleccion.updateOne(Filters.eq("nombre", nombreHotel),
                new Document("$pull", new Document("habitaciones", new Document("precio", new Document("$gt", 300)))));
        logger.info("Habitaciones con precio > 300 eliminadas en hotel: " + nombreHotel);
        this.hoteles = read();
    }

    // Media de estrellas de hoteles en Barcelona
    public double mediaEstrellasBarcelona() {
        List<Hotel> hotelesBarcelona = new ArrayList<>();
        FindIterable<Document> docs = coleccion.find(Filters.eq("ubicacion.ciudad", "Barcelona"));
        int totalEstrellas = 0;
        int contador = 0;
        for (Document doc : docs) {
            Hotel h = convertirDocumentoAHotel(doc);
            totalEstrellas += h.getEstrellas();
            contador++;
        }
        double media = contador > 0 ? (double) totalEstrellas / contador : 0;
        logger.info("Media de estrellas en Barcelona: " + media);
        return media;
    }

    // ------------------- Conversión -------------------

    public Hotel convertirDocumentoAHotel(Document doc) {
        List<Habitaciones> habList = new ArrayList<>();
        List<Document> habDocs = (List<Document>) doc.get("habitaciones");
        if (habDocs != null) {
            for (Document h : habDocs) {
                habList.add(new Habitaciones(
                        TipoHabitacion.valueOf(h.getString("tipo")),
                        h.getDouble("precio").floatValue(),
                        h.getInteger("capacidad"),
                        h.getBoolean("disponible")
                ));
            }
        }

        Document ubiDoc = (Document) doc.get("ubicacion");
        Document coordDoc = (Document) ubiDoc.get("coordenadas");
        Coordenadas coords = new Coordenadas(
                coordDoc.getDouble("lat").floatValue(),
                coordDoc.getDouble("lon").floatValue()
        );
        Ubicacion ubicacion = new Ubicacion(
                ubiDoc.getString("calle"),
                ubiDoc.getInteger("numero"),
                ubiDoc.getInteger("codigoPostal"),
                coords
        );

        Hotel hotel = new Hotel(
                doc.getString("idHotel"),
                doc.getString("nombre"),
                doc.getInteger("estrellas"),
                doc.getBoolean("admiteMascotas"),
                doc.getString("fecha_apertura"),
                ubicacion
        );

        hotel.setHabitaciones(habList);
        return hotel;
    }

    public Document convertirHotelADocument(Hotel hotel) {
        List<Document> habDocs = new ArrayList<>();
        for (Habitaciones h : hotel.getHabitaciones()) {
            habDocs.add(convertirHabitacionADocument(h));
        }

        Document coordDoc = new Document("lat", hotel.getUbicacion().getCoordenadas().getLat())
                .append("lon", hotel.getUbicacion().getCoordenadas().getLon());

        Document ubiDoc = new Document("calle", hotel.getUbicacion().getCalle())
                .append("numero", hotel.getUbicacion().getNumero())
                .append("codigoPostal", hotel.getUbicacion().getCodigoPostal())
                .append("coordenadas", coordDoc)
                .append("ciudad", hotel.getUbicacion().getCalle().equalsIgnoreCase("Gran Vía") ? "Madrid" : "Barcelona"); // simplificación ciudad

        Document doc = new Document("idHotel", hotel.getIdHotel())
                .append("nombre", hotel.getNombre())
                .append("estrellas", hotel.getEstrellas())
                .append("admiteMascotas", hotel.isAdmiteMascotas())
                .append("fecha_apertura", hotel.getFecha_apertura())
                .append("ubicacion", ubiDoc)
                .append("habitaciones", habDocs);

        return doc;
    }

    public Document convertirHabitacionADocument(Habitaciones habitacion) {
        return new Document("tipo", habitacion.getTipo().name())
                .append("precio", habitacion.getPrecio())
                .append("capacidad", habitacion.getCapacidad())
                .append("disponible", habitacion.isDisponible());
    }
}
