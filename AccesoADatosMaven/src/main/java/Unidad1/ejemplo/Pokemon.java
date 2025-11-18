package Unidad1.ejemplo;

import java.util.Objects;

public class Pokemon {

	private int id;
    private String nombre;
    private String tipo;
    private double altura_m;
    private double peso_kg;
    private String habilidadPrincipal;
    private String evoluciona_a;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getAltura_m() {
		return altura_m;
	}
	public void setAltura_m(double altura_m) {
		this.altura_m = altura_m;
	}
	public double getPeso_kg() {
		return peso_kg;
	}
	public void setPeso_kg(double peso_kg) {
		this.peso_kg = peso_kg;
	}
	public String getHabilidadPrincipal() {
		return habilidadPrincipal;
	}
	public void setHabilidadPrincipal(String habilidadPrincipal) {
		this.habilidadPrincipal = habilidadPrincipal;
	}
	public String getEvoluciona_a() {
		return evoluciona_a;
	}
	public void setEvoluciona_a(String evoluciona_a) {
		this.evoluciona_a = evoluciona_a;
	}
	public Pokemon(int id, String nombre, String tipo, double altura_m, double peso_kg, String habilidadPrincipal,
			String evoluciona_a) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.altura_m = altura_m;
		this.peso_kg = peso_kg;
		this.habilidadPrincipal = habilidadPrincipal;
		this.evoluciona_a = evoluciona_a;
	}
	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", altura_m=" + altura_m + ", peso_kg="
				+ peso_kg + ", habilidadPrincipal=" + habilidadPrincipal + ", evoluciona_a=" + evoluciona_a + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(altura_m, evoluciona_a, habilidadPrincipal, id, nombre, peso_kg, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return Double.doubleToLongBits(altura_m) == Double.doubleToLongBits(other.altura_m)
				&& Objects.equals(evoluciona_a, other.evoluciona_a)
				&& Objects.equals(habilidadPrincipal, other.habilidadPrincipal) && id == other.id
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(peso_kg) == Double.doubleToLongBits(other.peso_kg)
				&& Objects.equals(tipo, other.tipo);
	}
    
    
}
