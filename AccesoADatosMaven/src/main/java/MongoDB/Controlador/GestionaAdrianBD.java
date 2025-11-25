package MongoDB.Controlador;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import MongoDB.Config.MongoDBConexion;
import MongoDB.Modelos.Estudiante;
import MongoDB.Servicios.EstudianteService;

public class GestionaAdrianBD {

	public static void main(String[] args) {
		MongoDBConexion conexion = new MongoDBConexion();
		MongoDatabase db= conexion.getDb();	
//TODO Aquí creamos los diferentes servicios a partir del objeto db	
		EstudianteService estudiantesS = new EstudianteService(db);
		
		Estudiante e1 = new Estudiante(23, "Viejo verde", 7.9, List.of("leer", "nadar"));
		estudiantesS.save(e1);
		List<Estudiante> estudiantes = estudiantesS.read();
		for (Estudiante e : estudiantes) {
			System.out.println(e);
		}
	}
}

