package Repositorios;

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

import Modelos.AppException;
import Modelos.Evento;
import Modelos.PlanActivo;
import Modelos.Preferencias;
import Modelos.Usuario;


public class RepositorioEvento {
	private static final Logger logger = LogManager.getLogger(RepositorioEvento.class);
    private static final String NOMBRE_COLECCION = "usuariosApp";
    private final MongoCollection<Document> coleccion;
    private List<Usuario> usuarios;
    
    public RepositorioEvento(MongoDatabase db) {
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        this.usuarios = this.read();
    }
    
    public Usuario convertirDocumentoAUsuario(Document doc) {
        List<Evento> eventos = new ArrayList<>();
        List<Document> propDocs = (List<Document>) doc.get("eventosLogs");
        if (propDocs != null) {
            for (Document p : propDocs) {
            	eventos.add(new Evento(
                        p.getString("id_evento"),
                        p.getString("tag"),
                        p.getString("mensaje"),
                        p.getString("timestamp")
                ));
            }
        }

        Document especDoc = (Document) doc.get("preferencias");
        Preferencias espec = new Preferencias(
                especDoc.getBoolean("tema_oscuro"),
                especDoc.getString("idioma"),
                especDoc.getBoolean("notificaciones_push"),
                especDoc.getBoolean("limite_datos_moviles")
        );

        return new Usuario(
                doc.getString("id"),
                doc.getString("username"),
                doc.getString("email"),
                PlanActivo.valueOf(doc.getString("plan_activo")),
                doc.getString("dispositivo"),
                espec,
                eventos
        );
    }
    
    public Document convertirUsuarioADocumento(Usuario usuario) {
    	List<Document> eventosDoc = new ArrayList<>();
        for (Evento p : usuario.getLogs_eventos()) {
            Document doc = new Document("id_evento", p.getId_evento())
                    .append("tag", p.getTag())
                    .append("mensaje", p.getMensaje())
                    .append("timestamp", p.getTimestamp());
            eventosDoc.add(doc);
        }

        Document especDoc = new Document("tema_oscuro", usuario.getPreferencias().isTema_oscuro())
                .append("idioma", usuario.getPreferencias().getIdioma())
                .append("notificaciones_push", usuario.getPreferencias().isNotificaciones_push())
                .append("limite_datos_moviles", usuario.getPreferencias().isLimite_datos_moviles());

        Document doc = new Document("id", usuario.getId())
                .append("username", usuario.getUsername())
                .append("email", usuario.getEmail())
                .append("plan_activo", usuario.getPlan_activo().name())
                .append("dispositivo", usuario.getDispositivo())
                .append("eventosLogs", eventosDoc)
                .append("preferencias", especDoc);

        return doc;
    }    
    public List<Usuario> read() {
    	List <Usuario> lista = new ArrayList<>();
    	FindIterable<Document> docs = coleccion.find();
    	for (Document doc : docs) {
    		lista.add(convertirDocumentoAUsuario(doc));
    	}
    	return lista;
    }
    
    public void save (Usuario usuario) {
    	Document doc = convertirUsuarioADocumento(usuario);
    	if (usuario != null) {
    	coleccion.insertOne(doc);
    	} else {
    		throw new AppException("no se puede");
    	}
    }
    
    public List <Usuario> ordenarPorEmail() {
    	List <Usuario> lista = new ArrayList<>();
    	FindIterable<Document> docs = coleccion.find().sort(Sorts.ascending("email"));
    	for (Document doc: docs) {
    		lista.add(convertirDocumentoAUsuario(doc));
    	}
    	return lista;
    }
    
    public Usuario busquedaporID (String id) {
    	Document doc = coleccion.find(Filters.eq("id", id)).first();
    	logger.info(doc);
        return convertirDocumentoAUsuario(doc);
    }
    
    public void actualizaIdioma (String idioma) {
    	coleccion.updateMany(Filters.eq("preferencias.idioma", "ES"),
    			new Document("$set", new Document("preferencias.idioma", idioma)));
    	logger.info("Idioma actualizado");
    	this.usuarios = read();
    }
    
    public void eliminarUsuarioPlanActivo () {
    	coleccion.deleteMany(Filters.eq("plan_activo", PlanActivo.ANUAL));
    	this.usuarios = read();
    }
    
    
}
