package modelos;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idArticulo;
	private String titulo;
	private int numPaginaInicio;
	private int numPaginaFin;
	
	private Revista revista;
	
	private Set<Autor> autores;
	

}
