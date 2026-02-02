package Modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;

    @ManyToOne
    @JoinColumn(name = "id_moto")
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "id_prop_anterior")
    private Usuario propietarioAnterior;

    @ManyToOne
    @JoinColumn(name = "id_prop_nuevo")
    private Usuario propietarioNuevo;

    private LocalDate fechaTransferencia;
    private String estado;

    public Transferencia() {
    	super();
    }

	public Long getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(Long idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public Usuario getPropietarioAnterior() {
		return propietarioAnterior;
	}

	public void setPropietarioAnterior(Usuario propietarioAnterior) {
		this.propietarioAnterior = propietarioAnterior;
	}

	public Usuario getPropietarioNuevo() {
		return propietarioNuevo;
	}

	public void setPropietarioNuevo(Usuario propietarioNuevo) {
		this.propietarioNuevo = propietarioNuevo;
	}

	public LocalDate getFechaTransferencia() {
		return fechaTransferencia;
	}

	public void setFechaTransferencia(LocalDate fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
}
