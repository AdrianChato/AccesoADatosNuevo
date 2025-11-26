package MongoDB.Modelos;

public class Puntuacion {

	private double puntuacion;
	private String tipo;
	public double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Puntuacion(double puntuacion, String tipo) {
		super();
		this.puntuacion = puntuacion;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Puntuacion [puntuacion=" + puntuacion + ", tipo=" + tipo + "]";
	}
	
	
}
