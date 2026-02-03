package modelos;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {
	
	@Id
	private String dni;
	private String nombre;
	private String email;
	//Mapeo
	private Set<Articulo> articulos;
	
	

}
