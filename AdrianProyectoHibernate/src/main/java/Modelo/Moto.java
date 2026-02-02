package Modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "motos")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;

    private String marca;
    private String modelo;

    @Column(unique = true)
    private String matricula;

    private int cilindrada;

    @Column(name = "itv_fecha")
    private LocalDate itvFecha;

    @Column(name = "qr_codigo", unique = true)
    private String qrCodigo;


    @ManyToOne
    @JoinColumn(name = "id_propietario")
    private Usuario propietario;

    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL)
    private List<Evento> eventos;

    @ManyToMany
    @JoinTable(
        name = "moto_ruta",
        joinColumns = @JoinColumn(name = "id_moto"),
        inverseJoinColumns = @JoinColumn(name = "id_ruta")
    )
    private List<Ruta> rutas;

    public Moto() {
    	super();
    }

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Long idMoto) {
		this.idMoto = idMoto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public LocalDate getItvFecha() {
		return itvFecha;
	}

	public void setItvFecha(LocalDate itvFecha) {
		this.itvFecha = itvFecha;
	}

	public String getQrCodigo() {
		return qrCodigo;
	}

	public void setQrCodigo(String qrCodigo) {
		this.qrCodigo = qrCodigo;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
    
    
}
