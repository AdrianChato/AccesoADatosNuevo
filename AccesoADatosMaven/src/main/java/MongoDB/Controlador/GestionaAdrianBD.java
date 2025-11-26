package MongoDB.Controlador;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoDatabase;

import MongoDB.Config.MongoDBConexion;
import MongoDB.Modelos.Direccion;
import MongoDB.Modelos.Estudiante;
import MongoDB.Modelos.Puntuacion;
import MongoDB.Servicios.EstudianteService;

public class GestionaAdrianBD {

	public static void main(String[] args) {
		MongoDBConexion conexion = new MongoDBConexion();
		MongoDatabase db= conexion.getDb();	
//TODO Aquí creamos los diferentes servicios a partir del objeto db	
		EstudianteService estudiantesS = new EstudianteService(db);
		Direccion d = new Direccion("Sevilla",23520, "calle", 56);
		Puntuacion p = new Puntuacion(25, "viejisimo");
		List <Puntuacion> p1 = new ArrayList<>();
		p1.add(p);
		Estudiante e1 = new Estudiante(30, "Viejo azul", 7.9, List.of("leer", "nadar"), p1, d);
		estudiantesS.save(e1);
		List<Estudiante> estudiantes = estudiantesS.getRepo().getEstudiantes();
		for (Estudiante e : estudiantes) {
			System.out.println(e);
		}
	}
}

