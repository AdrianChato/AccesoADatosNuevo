package Modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "talleres")
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTaller;

    private String nombreTaller;
    private String direccion;
    private String telefono;
    private String cif;

    private boolean estadoVerificacion;


    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "tallerValidador")
    private List<Evento> eventosValidados;

    public Taller() {
    	super();
    }

	public Long getIdTaller() {
		return idTaller;
	}

	public void setIdTaller(Long idTaller) {
		this.idTaller = idTaller;
	}

	public String getNombreTaller() {
		return nombreTaller;
	}

	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public boolean isEstadoVerificacion() {
		return estadoVerificacion;
	}

	public void setEstadoVerificacion(boolean estadoVerificacion) {
		this.estadoVerificacion = estadoVerificacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Evento> getEventosValidados() {
		return eventosValidados;
	}

	public void setEventosValidados(List<Evento> eventosValidados) {
		this.eventosValidados = eventosValidados;
	}
    
    
}
