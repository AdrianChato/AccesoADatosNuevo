package MongoDB.Repositorios;

// Importamos todas las clases necesarias para manejar MongoDB, documentos BSON,
// listas, filtros, modelos propios y logging.
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
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import MongoDB.Modelos.Coordenadas;
import MongoDB.Modelos.Habitaciones;
import MongoDB.Modelos.Hotel;
import MongoDB.Modelos.TipoHabitacion;
import MongoDB.Modelos.Ubicacion;

public class HotelesRepositorio {

    // Creamos un logger para registrar información de este repositorio.
    private static final Logger logger = LogManager.getLogger(HotelesRepositorio.class);

    // Nombre de la colección que se va a utilizar en MongoDB.
    private static final String NOMBRE_COLECCION = "hoteles";

    // Referencia a la colección MongoDB que almacena documentos de hoteles.
    private final MongoCollection<Document> coleccion;

    // Lista en memoria que mantiene los hoteles leídos desde la colección.
    private List<Hotel> hoteles;

    // Constructor del repositorio
    public HotelesRepositorio(MongoDatabase db) {
        // Obtenemos la colección "hoteles" desde la base de datos.
        this.coleccion = db.getCollection(NOMBRE_COLECCION);

        // Carga inicial de hoteles desde MongoDB.
        this.hoteles = this.read();

        // Registramos cuántos hoteles fueron cargados.
        logger.info("Repositorio de Hoteles inicializado. Hoteles cargados: " + hoteles.size());
    }

    // Getter de la lista de hoteles.
    public List<Hotel> getHoteles() {
        return hoteles;
    }

    // Setter para modificar la lista en memoria.
    public void setHoteles(List<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    // ------------------- CRUD -------------------

    // Método para guardar un hotel en MongoDB.
    public void save(Hotel hotel) {
        // Convertimos el objeto Hotel a un documento BSON de MongoDB.
        Document doc = convertirHotelADocument(hotel);

        // Insertamos el documento en la colección.
        coleccion.insertOne(doc);

        // Añadimos el hotel también a la lista en memoria.
        hoteles.add(hotel);

        // Registramos la operación de guardado.
        logger.info("Hotel guardado: " + hotel.getNombre());
    }

    // Método para leer todos los hoteles desde MongoDB.
    public List<Hotel> read() {
        // Lista donde guardaremos los hoteles convertidos.
        List<Hotel> lista = new ArrayList<>();

        // Ejecutamos la consulta sin filtro (trae todos los documentos).
        FindIterable<Document> docs = coleccion.find();

        // Convertimos cada documento en un objeto Hotel.
        for (Document doc : docs) {
            lista.add(convertirDocumentoAHotel(doc));
        }

        // Devolvemos la lista completa.
        return lista;
    }

    // Buscar un hotel por su ID.
    public Hotel buscarPorId(String idHotel) {
        // Buscamos el primer documento donde idHotel coincida.
        Document doc = coleccion.find(Filters.eq("idHotel", idHotel)).first();

        // Si existe lo convertimos en objeto Hotel.
        if (doc != null) {
            return convertirDocumentoAHotel(doc);
        } else {
            // Si no existe registramos una advertencia.
            logger.warn("No se encontró hotel con ID: " + idHotel);
            return null;
        }
    }

    // Actualizar un hotel reemplazando por completo su documento.
    public void actualizar(String idHotel, Document hotelActualizado) {
        // Reemplazamos el documento donde idHotel coincida.
        coleccion.replaceOne(Filters.eq("idHotel", idHotel), hotelActualizado);

        // Informamos en el log.
        logger.info("Hotel actualizado: " + idHotel);

        // Actualizamos la lista en memoria leyendo todo de nuevo.
        this.hoteles = read();
    }

    // Eliminar un hotel por ID.
    public void eliminar(String idHotel) {
        // Eliminamos el documento cuyo idHotel coincida.
        coleccion.deleteOne(Filters.eq("idHotel", idHotel));

        // Registramos la operación.
        logger.info("Hotel eliminado: " + idHotel);

        // Refrescamos la lista en memoria.
        this.hoteles = read();
    }

    // ------------------- Operaciones especiales -------------------

    // Filtrar hoteles ubicados en Madrid que tengan 5 estrellas o permitan mascotas.
    public List<Hotel> filtrarHotelesMadridEstrellasOMascotas() {
        // Lista donde guardaremos los hoteles filtrados.
        List<Hotel> lista = new ArrayList<>();

        // Consulta combinando filtros AND + OR.
        FindIterable<Document> docs = coleccion.find(
                Filters.and(
                        Filters.eq("ubicacion.ciudad", "Madrid"),
                        Filters.or(Filters.eq("estrellas", 5), Filters.eq("admiteMascotas", true))
                )
        );

        // Convertimos y añadimos cada documento encontrado.
        for (Document doc : docs) {
            lista.add(convertirDocumentoAHotel(doc));
        }

        // Registramos cuántos hoteles cumplen con el filtro.
        logger.info("Hoteles filtrados en Madrid (5* o mascotas): " + lista.size());
        return lista;
    }

    // Contar cuántos hoteles tienen al menos una habitación tipo SUITE_JUNIOR.
    public long contarSuiteJunior() {
        // Usamos elemMatch para buscar elementos dentro del array habitaciones.
        long count = coleccion.countDocuments(Filters.elemMatch("habitaciones", Filters.eq("tipo", "SUITE_JUNIOR")));

        // Registramos el resultado.
        logger.info("Hoteles con Suite Junior: " + count);
        return count;
    }

    // Añadir una habitación a un hotel usando $push.
    public void añadirHabitacion(String idHotel, Habitaciones habitacion) {
        // Convertimos la habitación en Document.
        Document habitacionDoc = convertirHabitacionADocument(habitacion);

        // Actualizamos el documento añadiendo la habitación al array.
        coleccion.updateOne(Filters.eq("idHotel", idHotel),
                new Document("$push", new Document("habitaciones", habitacionDoc)));

        // Registramos la operación.
        logger.info("Habitación añadida al hotel: " + idHotel);

        // Refrescamos la lista en memoria.
        this.hoteles = read();
    }

    // Actualizar el código postal de todos los hoteles en la calle Gran Vía.
    public void actualizarCPGranVia(String nuevoCP) {
        // Aplicamos $set a todos los documentos que tengan esa calle.
        coleccion.updateMany(
                Filters.eq("ubicacion.calle", "Gran Vía"),
                new Document("$set", new Document("ubicacion.codigoPostal", nuevoCP))
        );

        // Registramos.
        logger.info("Código postal actualizado en Gran Vía a: " + nuevoCP);

        // Refrescamos memoria.
        this.hoteles = read();
    }

    // Actualizar el precio de habitaciones INDIVIDUAL usando arrayFilters.
    public void actualizarPrecioIndividual(String idHotel, double nuevoPrecio) {
        coleccion.updateOne(
                Filters.eq("idHotel", idHotel),
                new Document("$set", new Document("habitaciones.$[elem].precio", nuevoPrecio))
                        .append("arrayFilters", List.of(Filters.eq("elem.tipo", "INDIVIDUAL")))
        );

        logger.info("Precio de habitación Individual actualizado en hotel: " + idHotel);

        this.hoteles = read();
    }

    // Eliminar habitaciones con precio mayor a 300 usando $pull.
    public void eliminarHabitacionesCaras(String nombreHotel) {
        coleccion.updateOne(
                Filters.eq("nombre", nombreHotel),
                new Document("$pull",
                        new Document("habitaciones",
                                new Document("precio", new Document("$gt", 300))))
        );

        logger.info("Habitaciones con precio > 300 eliminadas en hotel: " + nombreHotel);

        this.hoteles = read();
    }

    // Calcular la media de estrellas de los hoteles de Barcelona.
    public double mediaEstrellasBarcelona() {
        // Lista temporal de hoteles de Barcelona.
        List<Hotel> hotelesBarcelona = new ArrayList<>();

        // Buscamos todos los documentos donde la ciudad sea Barcelona.
        FindIterable<Document> docs = coleccion.find(Filters.eq("ubicacion.ciudad", "Barcelona"));

        int totalEstrellas = 0;
        int contador = 0;

        // Convertimos cada documento y sumamos estrellas.
        for (Document doc : docs) {
            Hotel h = convertirDocumentoAHotel(doc);
            totalEstrellas += h.getEstrellas();
            contador++;
        }

        // Calculamos media (evitamos dividir entre cero).
        double media = contador > 0 ? (double) totalEstrellas / contador : 0;

        logger.info("Media de estrellas en Barcelona: " + media);
        return media;
    }

    // ------------------- Conversión -------------------

    // Convertir un documento MongoDB a un objeto Hotel.
    public Hotel convertirDocumentoAHotel(Document doc) {
        // Lista donde guardaremos las habitaciones convertidas.
        List<Habitaciones> habList = new ArrayList<>();

        // Extraemos la lista de habitaciones del documento.
        List<Document> habDocs = (List<Document>) doc.get("habitaciones");

        // Si existen habitaciones, las convertimos una por una.
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

        // Extraemos el documento de ubicación.
        Document ubiDoc = (Document) doc.get("ubicacion");

        // Extraemos el subdocumento de coordenadas.
        Document coordDoc = (Document) ubiDoc.get("coordenadas");

        // Construimos el objeto Coordenadas.
        Coordenadas coords = new Coordenadas(
                coordDoc.getDouble("lat").floatValue(),
                coordDoc.getDouble("lon").floatValue()
        );

        // Construimos el objeto Ubicacion.
        Ubicacion ubicacion = new Ubicacion(
                ubiDoc.getString("calle"),
                ubiDoc.getInteger("numero"),
                ubiDoc.getInteger("codigoPostal"),
                coords
        );

        // Construimos el objeto Hotel principal.
        Hotel hotel = new Hotel(
                doc.getString("idHotel"),
                doc.getString("nombre"),
                doc.getInteger("estrellas"),
                doc.getBoolean("admiteMascotas"),
                doc.getString("fecha_apertura"),
                ubicacion
        );

        // Asignamos la lista de habitaciones convertida.
        hotel.setHabitaciones(habList);

        return hotel;
    }

    // Convertir un hotel Java a un documento MongoDB.
    public Document convertirHotelADocument(Hotel hotel) {
        // Convertimos cada habitación a documento.
        List<Document> habDocs = new ArrayList<>();
        for (Habitaciones h : hotel.getHabitaciones()) {
            habDocs.add(convertirHabitacionADocument(h));
        }

        // Construimos las coordenadas como subdocumento.
        Document coordDoc = new Document("lat", hotel.getUbicacion().getCoordenadas().getLat())
                .append("lon", hotel.getUbicacion().getCoordenadas().getLon());

        // Construimos el documento de ubicación.
        Document ubiDoc = new Document("calle", hotel.getUbicacion().getCalle())
                .append("numero", hotel.getUbicacion().getNumero())
                .append("codigoPostal", hotel.getUbicacion().getCodigoPostal())
                .append("coordenadas", coordDoc)
                // Asignamos ciudad según una regla simple.
                .append("ciudad", hotel.getUbicacion().getCalle().equalsIgnoreCase("Gran Vía")
                        ? "Madrid"
                        : "Barcelona");

        // Construimos el documento final del hotel.
        Document doc = new Document("idHotel", hotel.getIdHotel())
                .append("nombre", hotel.getNombre())
                .append("estrellas", hotel.getEstrellas())
                .append("admiteMascotas", hotel.isAdmiteMascotas())
                .append("fecha_apertura", hotel.getFecha_apertura())
                .append("ubicacion", ubiDoc)
                .append("habitaciones", habDocs);

        return doc;
    }

    // Convertir una habitación Java a Document MongoDB.
    public Document convertirHabitacionADocument(Habitaciones habitacion) {
        return new Document("tipo", habitacion.getTipo().name())
                .append("precio", habitacion.getPrecio())
                .append("capacidad", habitacion.getCapacidad())
                .append("disponible", habitacion.isDisponible());
    }

    // Eliminar habitaciones más caras que cierto precio usando $pull.
    public void pullHabitacionesCaras(String nombreHotel, double precioMinimo) {
        coleccion.updateOne(
                Filters.eq("nombre", nombreHotel),
                Updates.pull(
                        "habitaciones",
                        new Document("precio", new Document("$gt", precioMinimo))
                )
        );

        logger.info("Habitaciones eliminadas mediante $pull en '" + nombreHotel +
                "' con precio > " + precioMinimo);
    }

    // Agregar una nueva habitación usando $push.
    public void pushHabitacion(String idHotel, Habitaciones nuevaHabitacion) {

        // Convertimos la habitación a documento MongoDB.
        Document habDoc = new Document("tipo", nuevaHabitacion.getTipo().name())
                .append("precio", nuevaHabitacion.getPrecio())
                .append("capacidad", nuevaHabitacion.getCapacidad())
                .append("disponible", nuevaHabitacion.isDisponible());

        // La añadimos al array habitaciones del hotel.
        coleccion.updateOne(
                Filters.eq("idHotel", idHotel),
                Updates.push("habitaciones", habDoc)
        );

        logger.info("Habitación añadida al hotel mediante $push: " + idHotel);
    }

}
