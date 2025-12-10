package Servicios;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoDatabase;

import Modelos.Usuario;
import Repositorios.RepositorioEvento;


public class ServicioEvento {

	private static final Logger logger = LogManager.getLogger(ServicioEvento.class);
	private RepositorioEvento repo;
	
	public ServicioEvento(MongoDatabase db) {
		super();
		this.repo = new RepositorioEvento(db);
	}
	
	public void save (Usuario usuario) {
		repo.save(usuario);
	}
	
	public List <Usuario> read () {
		return repo.read();
	}
	
	public List <Usuario> ordenarPorEmail() {
		return repo.ordenarPorEmail();
	}
	
	public Usuario busquedaporID (String id) {
		return repo.busquedaporID(id);
	}
	
	public void actualizaIdioma (String idioma) {
		repo.actualizaIdioma(idioma);
	}
	
	public void eliminarUsuarioPlanActivo () {
		repo.eliminarUsuarioPlanActivo();
	}
	}

