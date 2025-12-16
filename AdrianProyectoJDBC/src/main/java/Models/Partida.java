package Models;

import java.time.LocalDate;

public class Partida {

	private int id;
	private Jugador narrador;
	private LocalDate fecha;
	private TipoResultado resultado;

	public Partida(Jugador narrador, LocalDate fecha, TipoResultado resultado) {
		this.narrador = narrador;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jugador getNarrador() {
		return narrador;
	}

	public void setNarrador(Jugador narrador) {
		this.narrador = narrador;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public TipoResultado getResultado() {
		return resultado;
	}

	public void setResultado(TipoResultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", narrador=" + narrador + ", fecha=" + fecha + ", resultado=" + resultado + "]";
	}

	

}
