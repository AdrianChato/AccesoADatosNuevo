package Modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String tipoEvento;

    private String descripcion;

    @Column(name = "fecha_evento")
    private LocalDate fechaEvento;

    private int km;

    private boolean esValidado;

    @Column(name = "fecha_validacion")
    private LocalDate fechaValidacion;


    @ManyToOne
    @JoinColumn(name = "id_moto")
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "id_usuario_creador")
    private Usuario usuarioCreador;

    @ManyToOne
    @JoinColumn(name = "id_taller_validador", nullable = true)
    private Taller tallerValidador;

    public Evento() {
    	super();
    }

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(LocalDate fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public boolean isEsValidado() {
		return esValidado;
	}

	public void setEsValidado(boolean esValidado) {
		this.esValidado = esValidado;
	}

	public LocalDate getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(LocalDate fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Usuario usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public Taller getTallerValidador() {
		return tallerValidador;
	}

	public void setTallerValidador(Taller tallerValidador) {
		this.tallerValidador = tallerValidador;
	}
    
    
}
