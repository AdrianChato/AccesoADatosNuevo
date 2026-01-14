package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.PlanActivo;
import Models.Preferencias;
import Models.Usuario;
import Utiles.MySqlConector;

public class RepositorioUsuario {
	private static final Logger logger = LogManager.getLogger(RepositorioUsuario.class);

	private MySqlConector mysqlConector;
	private List<Usuario> usuarios;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public RepositorioUsuario() {
		super();
		this.mysqlConector = new MySqlConector();
		this.usuarios = new ArrayList<>();
	}

	public List<Usuario> obtenerUsuario() {
		String sql = "SELECT * FROM usuarios, preferencias";

		try {
			Statement st = mysqlConector.getConnect().createStatement();
			ResultSet rs = st.executeQuery(sql);
			Usuario u = new Usuario();
			if (rs.next()) {
				Preferencias prefe = new Preferencias(
						rs.getBoolean("tema_oscuro"),
						rs.getString("idioma"),
						rs.getBoolean("notificaciones_push"),
						rs.getBoolean("limite_datos_moviles")
						);
				u = new Usuario(
				rs.getString("id"),
				rs.getString("username"),
				rs.getString("email"),
				PlanActivo.valueOf(rs.getString("plan_activo")),
				rs.getString("dispositivo"),
				prefe);
				usuarios.add(u);
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(usuarios);
		return usuarios;
	}
	
	public void registrarNuevoUsuario(Usuario usuario) {

        // Consulta SQL con parámetros
        String sql = "INSERT INTO usuarios (id, username, email, dispositivo) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO preferencias (usuario_id, tema_oscuro, idioma, notificaciones_push, limite_datos_moviles) VALUES (?,?,?)";
       try {
    	
		PreparedStatement ps = mysqlConector.getConnect().prepareStatement(sql);
		ps.setString(1, usuario.getId());
		ps.setString(2, usuario.getUsername());
		ps.setString(3, usuario.getEmail());
		//PlanActivo.valueOf(ps.setString(4, usuario.getPlan_activo()));
		ps.setString(4, usuario.getDispositivo());
		//ps.setString(6, usuario.getPlan_activo());
		
		ps.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
    }
	
	public void numUsuarios () {
		String sql = "SELECT COUNT(*) FROM usuarios WHERE plan_activo = 'VIP'";
		try {
			Statement st = mysqlConector.getConnect().createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.info(st);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void buscarPorID (String id) {
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		
		try {
			PreparedStatement ps = mysqlConector.getConnect().prepareStatement(sql);
			ps.setString(1, id);
			
			ps.execute();
			logger.info(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
