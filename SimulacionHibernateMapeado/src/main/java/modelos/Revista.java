package modelos;

import java.time.LocalDate;
import java.util.List;

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
	
	
}
