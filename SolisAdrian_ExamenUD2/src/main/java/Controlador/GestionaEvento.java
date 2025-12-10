package Controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoDatabase;

import Config.MongoDBConexion;
import Modelos.Evento;
import Modelos.PlanActivo;
import Modelos.Preferencias;
import Modelos.Usuario;
import Servicios.ServicioEvento;


public class GestionaEvento {

    private static final Logger logger = LogManager.getLogger(GestionaEvento.class);

	public static void main(String[] args) {
		
		MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();
        ServicioEvento repo = new ServicioEvento(db);
        
        //Creamos un usuario y lo añadimos
        Evento evento = new Evento("wef2", "rtnn", "espero que funcione", "fgbwerh");
        List <Evento> eventos = new ArrayList<>();
        eventos.add(evento);
        Preferencias preferencias = new Preferencias(false, "EN", false, true);
        Usuario usu1 = new Usuario("usr042", "Pepe", "adrian@gmail.com", PlanActivo.FREE, "Iphone", preferencias, eventos);
        
        repo.save(usu1);

        // Actualiza los idiomas
        repo.actualizaIdioma("ESP");
        //Elimina los plan activo anuales
        repo.eliminarUsuarioPlanActivo();
        //leemos el documento
        List <Usuario> usuarios = repo.read();
        for (Usuario u : usuarios) {
        	logger.info(u);
        }
        //Busqueda por ID del usuario
        repo.busquedaporID("usr002");
        
        
	}

}
