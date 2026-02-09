package modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {

    @Id // El DNI es único y no nulo por ser PK, tal como pide el enunciado
    @Column(length = 9) // Limitamos el tamaño por ser un DNI
    private String dni; 

    private String nombre; 
    private String email; 

    /* MAPPEDBY: Siempre en el lado que NO tiene el @JoinTable. 
    Dice: "Mira cómo se llama mi lista en la clase Articulo (autores)".
 */
    @ManyToMany(mappedBy = "autores") // Lado inverso de la relación N:M [cite: 25, 41]
    private List<Articulo> articulos = new ArrayList<>();

    public Autor() {
    	
    } 

    public Autor(String dni, String nombre, String email) { // [cite: 28]
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    @Override
    public String toString() { 
        return "Autor [dni=" + dni + ", nombre=" + nombre + ", email=" + email + "]";
    }

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

    
}
