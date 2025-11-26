package MongoDB.Repositorios;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import MongoDB.Modelos.Direccion;
import MongoDB.Modelos.Estudiante;
import MongoDB.Modelos.Puntuacion;

public class EstudianteRepositorio {
	 private static final String NOMBRE_COLECCION = "estudiantes";
	  private final MongoCollection<Document> coleccion;
	  private List<Estudiante> estudiantes;
	  
	  

	   public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	  public void setEstudiantes(List<Estudiante> estudiantes) {
		  this.estudiantes = estudiantes;
	  }

	   public EstudianteRepositorio(MongoDatabase db) {
	       this.coleccion = db.getCollection(NOMBRE_COLECCION);
	       this.estudiantes = this.read();
	   }

	   public void save(Estudiante e) {
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
	   public List<Estudiante> read() {
		    List<Estudiante> estudiantes = new ArrayList<>();
		    FindIterable<Document> documentos = coleccion.find();

		    for (Document doc : documentos) {
		        Estudiante e = new Estudiante();

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


