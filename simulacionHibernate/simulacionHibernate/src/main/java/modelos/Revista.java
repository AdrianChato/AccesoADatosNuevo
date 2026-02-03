package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "revista")
public class Revista {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRevista;
	private String nombreRevista;
	private int numeroRevista;
	private LocalDate fecha;
	private int unidadesImpresas;

	@OneToMany(mappedBy = "revista", cascade = CascadeType.ALL)
	private List<Articulo> articulos;

	public void addArticulo(Articulo a) {

		if (!this.articulos.contains(a)) {
			this.articulos.add(a);
		}
		a.setRevista(this);
	}

	public int getIdRevista() {
		return idRevista;
	}

	public void setIdRevista(int idRevista) {
		this.idRevista = idRevista;
	}

	public String getNombreRevista() {
		return nombreRevista;
	}

	public void setNombreRevista(String nombreRevista) {
		this.nombreRevista = nombreRevista;
	}

	public int getNumeroRevista() {
		return numeroRevista;
	}

	public void setNumeroRevista(int numeroRevista) {
		this.numeroRevista = numeroRevista;
	}

	public int getUnidadesImpresas() {
		return unidadesImpresas;
	}

	public void setUnidadesImpresas(int unidadesImpresas) {
		this.unidadesImpresas = unidadesImpresas;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public Revista() {
		super();
		this.nombreRevista = "";
		this.articulos = new ArrayList<Articulo>();

	}

	public Revista(String nombreRevista, int numeroRevista, LocalDate fecha, int unidadesImpresas) {
		super();
		this.nombreRevista = nombreRevista;
		this.numeroRevista = numeroRevista;
		this.fecha = fecha;
		this.unidadesImpresas = unidadesImpresas;
		this.articulos = new ArrayList<Articulo>();
	}

	public Revista(String nombreRevista, int numeroRevista, LocalDate fecha) {
		super();
		this.nombreRevista = nombreRevista;
		this.numeroRevista = numeroRevista;
		this.fecha = fecha;
		this.articulos = new ArrayList<Articulo>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRevista);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revista other = (Revista) obj;
		return idRevista == other.idRevista;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
