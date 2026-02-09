package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Indica que esta clase se transformará en una tabla de la BD
@Table(name = "revistas")
public class Revista {

    @Id // Define la Clave Primaria (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID es autoincremental en la BD
    private int idRevista; 

    private String nombreRevista; 
    private int numeroRevista; 
    private LocalDate fecha; 
    private int unidadesImpresas; 

    /* MAPPEDBY: Indica que la relación ya está configurada en el atributo "revista" de la clase Articulo.
    CASCADE: Si borras una revista, se borran sus artículos (opcional, pero común en 1:N).
    BIDIRECCIONAL: Permite que desde la Revista puedas ver su lista de artículos.
 */
    
    // Relación 1:N bidireccional 
    @OneToMany(mappedBy = "revista", cascade = CascadeType.ALL)
    private List<Articulo> articulos = new ArrayList<>(); // [cite: 3]

    public Revista() {
    } 

    // Constructor solicitado por el main
    public Revista(String nombreRevista, int numeroRevista, LocalDate fecha, int unidadesImpresas) {
        this.nombreRevista = nombreRevista;
        this.numeroRevista = numeroRevista;
        this.fecha = fecha;
        this.unidadesImpresas = unidadesImpresas;
    }

    // Método para añadir artículos manteniendo la coherencia 
 // Método de conveniencia para asegurar que ambos lados de la relación se conocen
    public void addArticulo(Articulo a) {
        this.articulos.add(a);
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

    
}
