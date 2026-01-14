package Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.Usuario;
import Repository.RepositorioUsuario;



public class ServicioUsuario {
	private static final Logger logger = LogManager.getLogger(ServicioUsuario.class);
	private RepositorioUsuario repo;
	
	public ServicioUsuario() {
		super();
		this.repo = new RepositorioUsuario();
	}
	
	public List<Usuario> obtenerUsuario() {
		return repo.obtenerUsuario();
	}
	
	public void registrarNuevoUsuario(Usuario usuario) {
		repo.registrarNuevoUsuario(usuario);
	}
	
	public void numUsuarios () {
		repo.numUsuarios();
	}
	
	public void buscarPorID (String id) {
		repo.buscarPorID(id);
	}
	
}
