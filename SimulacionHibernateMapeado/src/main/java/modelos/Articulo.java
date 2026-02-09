package modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado según enunciado
    private int idArticulo; 

    @Column(unique = true, nullable = false) // "No hay dos artículos con el mismo título"
    private String titulo; 

    private int numPaginaInicio; 
    private int numPaginaFin; 

    /* MANYTOONE: Muchos artículos pertenecen a una revista
    JOINCOLUMN: Crea físicamente la columna "id_revista" (Foreign Key) en la tabla articulos.
 */
    
    @ManyToOne
    @JoinColumn(name = "id_revista")
    private Revista revista; 

    
    /* MANYTOMANY: Un artículo tiene varios autores y un autor varios artículos.
    JOINTABLE: Como es N:M, Hibernate necesita crear una "tabla intermedia".
    JOINCOLUMNS: La columna que apunta a este modelo (Articulo).
    INVERSEJOINCOLUMNS: La columna que apunta al otro modelo (Autor).
 */
    // Relación N:M bidireccional 
    @ManyToMany
    @JoinTable(
        name = "articulos_autores",
        joinColumns = @JoinColumn(name = "id_articulo"),
        inverseJoinColumns = @JoinColumn(name = "dni_autor")
    )
    private List<Autor> autores = new ArrayList<>();

    public Articulo() {} // [cite: 15]

    public Articulo(String titulo, int numPaginaInicio, int numPaginaFin) {
        this.titulo = titulo;
        this.numPaginaInicio = numPaginaInicio;
        this.numPaginaFin = numPaginaFin;
    }

 // Al añadir un autor, lo meto en mi lista y yo me meto en la suya (bidireccional)
    public void addAutor(Autor a) { // [cite: 20]
        this.autores.add(a);
        a.getArticulos().add(this);
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumPaginaInicio() {
		return numPaginaInicio;
	}

	public void setNumPaginaInicio(int numPaginaInicio) {
		this.numPaginaInicio = numPaginaInicio;
	}

	public int getNumPaginaFin() {
		return numPaginaFin;
	}

	public void setNumPaginaFin(int numPaginaFin) {
		this.numPaginaFin = numPaginaFin;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Revista getRevista() {
		return revista;
	}
    
}
