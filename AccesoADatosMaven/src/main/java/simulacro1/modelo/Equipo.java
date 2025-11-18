package unidad1.simulacro1.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {
	 private String identificadorEquipo;
	    private String nombreEquipo;
	    private int puntos;
	    private List<Piloto> pilotos;

	    public Equipo() {
	        pilotos = new ArrayList<>();
	    }

	    public Equipo(String id, String nombre, int puntos) {
	        this.identificadorEquipo = id;
	        this.nombreEquipo = nombre;
	        this.puntos = puntos;
	        this.pilotos = new ArrayList<>();
	    }
	    
	   

	    public String getIdentificadorEquipo() {
			return identificadorEquipo;
		}

		public void setIdentificadorEquipo(String identificadorEquipo) {
			this.identificadorEquipo = identificadorEquipo;
		}

		public String getNombreEquipo() {
			return nombreEquipo;
		}

		public void setNombreEquipo(String nombreEquipo) {
			this.nombreEquipo = nombreEquipo;
		}

		public int getPuntos() {
			return puntos;
		}

		public void setPuntos(int puntos) {
			this.puntos = puntos;
		}

		public List<Piloto> getPilotos() {
			return pilotos;
		}

		public void setPilotos(List<Piloto> pilotos) {
			this.pilotos = pilotos;
		}

		@Override
		public String toString() {
			return "Equipo [identificadorEquipo=" + identificadorEquipo + ", nombreEquipo=" + nombreEquipo + ", puntos="
					+ puntos + ", pilotos=" + pilotos + "]";
		}

		
	    

	
	
	
	
}
