package Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.PlanActivo;
import Models.Preferencias;
import Models.Usuario;
import Service.ServicioUsuario;


public class ControladorUsuario {

	private static final Logger logger = LogManager.getLogger(ControladorUsuario.class);
	public static void main(String[] args) {
		
		ServicioUsuario repo = new ServicioUsuario();
		Preferencias pre = new Preferencias();
		Usuario user = new Usuario("pebawergv5", "wvwvpepito", "@dvsuregspenso", PlanActivo.ANUAL, "Iphone", pre);
		//Obtiene Usuarios
		repo.obtenerUsuario();
		//registra Usuario
		repo.registrarNuevoUsuario(user);
		//CuentaUsuarios
		repo.numUsuarios();
		//busquedaporID
		repo.buscarPorID("usr002");
	}

}
