package Repositorios;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Filters;

import Modelos.Moto;
import Modelos.Propietario;
import Modelos.Especificacion;
import Modelos.TipoMotor;

public class RepositorioMotos {

    private static final Logger logger = LogManager.getLogger(RepositorioMotos.class);
    private static final String NOMBRE_COLECCION = "Motos";
    private final MongoCollection<Document> coleccion;
    private List<Moto> motos;

    public RepositorioMotos(MongoDatabase db) {
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        this.motos = this.read();
        logger.info("Repositorio de Motos inicializado. Motos cargadas: " + motos.size());
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }

    // Guardar moto en BD
    public void save(Moto moto) {
        Document doc = convertirMotoADocument(moto);
        coleccion.insertOne(doc);
        logger.info("Moto guardada: " + moto.getModelo());
    }

    // Leer todas las motos
    public List<Moto> read() {
        List<Moto> lista = new ArrayList<>();
        FindIterable<Document> docs = coleccion.find();
        for (Document doc : docs) {
            lista.add(convertirDocumentoAMoto(doc));
        }
        return lista;
    }

    // FILTRO: motos en venta usando MongoDB
    public List<Moto> filtrarEnVentaQuery() {
        List<Moto> lista = new ArrayList<>();
        FindIterable<Document> docs = coleccion.find(Filters.eq("enVenta", true));
        for (Document doc : docs) {
            lista.add(convertirDocumentoAMoto(doc));
        }
        return lista;
    }

    // ORDENACIÓN: por cilindrada (cc) ascendente usando MongoDB
    public List<Moto> ordenarPorCCQuery() {
        List<Moto> lista = new ArrayList<>();
        FindIterable<Document> docs = coleccion.find().sort(Sorts.ascending("cc"));
        for (Document doc : docs) {
            lista.add(convertirDocumentoAMoto(doc));
        }
        return lista;
    }

    // De Document a Moto
    public Moto convertirDocumentoAMoto(Document doc) {
        List<Propietario> propietarios = new ArrayList<>();
        List<Document> propDocs = (List<Document>) doc.get("propietarios");
        if (propDocs != null) {
            for (Document p : propDocs) {
                propietarios.add(new Propietario(
                        p.getString("dni"),
                        p.getString("nombre"),
                        p.getString("direccion"),
                        p.getInteger("telefono")
                ));
            }
        }

        Document especDoc = (Document) doc.get("especificaciones");
        Especificacion espec = new Especificacion(
                especDoc.getInteger("anio_matriculacion"),
                especDoc.getInteger("propietarios_totales"),
                especDoc.getBoolean("revisiones"),
                especDoc.getBoolean("modificaciones")
        );

        return new Moto(
                doc.getInteger("num_bas"),
                doc.getDouble("cc").floatValue(),
                doc.getInteger("cv"),
                TipoMotor.valueOf(doc.getString("motor")),
                doc.getString("modelo"),
                doc.getBoolean("enVenta"),
                propietarios,
                espec
        );
    }

    public Document convertirMotoADocument(Moto moto) {
        List<Document> propietariosDoc = new ArrayList<>();
        for (Propietario p : moto.getPropietarios()) {
            Document doc = new Document("dni", p.getDni())
                    .append("nombre", p.getNombre())
                    .append("direccion", p.getDireccion())
                    .append("telefono", p.getTelefono());
            propietariosDoc.add(doc);
        }

        Document especDoc = new Document("anio_matriculacion", moto.getEspecificaciones().getAnio_matriculacion())
                .append("propietarios_totales", moto.getEspecificaciones().getPropietarios_totales())
                .append("revisiones", moto.getEspecificaciones().isRevisiones())
                .append("modificaciones", moto.getEspecificaciones().isModificaciones());

        Document doc = new Document("num_bas", moto.getNum_bas())
                .append("cc", moto.getCc())
                .append("cv", moto.getCv())
                .append("motor", moto.getMotor().name())
                .append("modelo", moto.getModelo())
                .append("enVenta", moto.isEnVenta())
                .append("propietarios", propietariosDoc)
                .append("especificaciones", especDoc);

        return doc;
    }
}
