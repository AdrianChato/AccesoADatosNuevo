package Repositorios;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Modelos.Moto;

public class RepositorioMotos {

	  private static final String NOMBRE_COLECCION = "Hoteles";
	  private final MongoCollection<Document> coleccion;
	  private List <Moto> motos;
	  
	  public List<Moto> getMotos() {
		  return motos;
	  }
	  public void setMotos(List<Moto> motos) {
		  this.motos = motos;
	  }
	  public RepositorioMotos(MongoDatabase db) {
		super();
		this.coleccion = db.getCollection(NOMBRE_COLECCION);
		this.motos = this.read();
	  }
	  
	  public void save(Moto e) {
		   Document docDireccion = new Document("city", e.getDireccion().getCity())
				   .append("zip", e.getDireccion().getZip())
				   .append("street", e.getDireccion().getStreet())
				   .append("number", e.getDireccion().getNumber());
		   List<Document> listadoc = new ArrayList<>();
		   for (Puntuacion p : e.getPuntuaciones()) {
			   
			   Document docPuntuacion = new Document("score", p.getPuntuacion())
					   .append("type", p.getTipo());
			   listadoc.add(docPuntuacion);
			   
		   }
	       Document doc = new Document("id", e.getId())
	               .append("name", e.getName())
	               .append("notaMedia", e.getNotaMedia())
	               .append("aficiones", e.getAficiones())
	               .append("scores", listadoc)
	               .append("address", docDireccion)
	                ;
	       	
	       coleccion.insertOne(doc);
		}
	   public List<Moto> read() {
		    List<Moto> estudiantes = new ArrayList<>();
		    FindIterable<Document> documentos = coleccion.find();

		    for (Document doc : documentos) {
		        Moto e = new Moto();

		        // Campos básicos
		        e.setId(doc.getInteger("id", 0));
		        e.setName(doc.getString("name"));
		        e.setNotaMedia(doc.getDouble("notaMedia"));
		        List<String> aficiones = doc.getList("aficiones", String.class);
		        e.setAficiones(aficiones != null ? aficiones : new ArrayList<>());

		        // Dirección
		        Document direccionDoc = (Document) doc.get("address");
		        if (direccionDoc != null) {
		            Direccion direccion = new Direccion(
		                direccionDoc.getString("city"),
		                direccionDoc.getInteger("zip", 0),
		                direccionDoc.getString("street"),
		                direccionDoc.getInteger("number", 0)
		            );
		            e.setDireccion(direccion);
		        }

		        // Puntuaciones
		        List<Document> scoresDoc = (List<Document>) doc.get("scores");
		        List<Puntuacion> puntuaciones = new ArrayList<>();
		        if (scoresDoc != null) {
		            for (Document s : scoresDoc) {
		                puntuaciones.add(new Puntuacion(
		                    s.getDouble("score"),
		                    s.getString("type")
		                ));
		            }
		        }
		        e.setPuntuaciones(puntuaciones);

		        // Añadir estudiante completo
		        estudiantes.add(e);
		    }

		    return estudiantes;
		}
}