package unidad1.simulacro1.modelo;

import java.util.Objects;

public class Piloto {

    private int identificadorPiloto;
    private String nombrePiloto;
    private String pais;
    private int puntos;
    private int identificadorEquipo;

    public Piloto() {}

    public Piloto(int identificadorPiloto, String nombrePiloto, String pais, int puntos, int identificadorEquipo) {
        this.identificadorPiloto = identificadorPiloto;
        this.nombrePiloto = nombrePiloto;
        this.pais = pais;
        this.puntos = puntos;
        this.identificadorEquipo = identificadorEquipo;
    }

    public int getIdentificadorPiloto() {
        return identificadorPiloto;
    }

    public void setIdentificadorPiloto(int identificadorPiloto) {
        this.identificadorPiloto = identificadorPiloto;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getIdentificadorEquipo() {
        return identificadorEquipo;
    }

    public void setIdentificadorEquipo(int identificadorEquipo) {
        this.identificadorEquipo = identificadorEquipo;
    }

    

    @Override
	public String toString() {
		return "Piloto [identificadorPiloto=" + identificadorPiloto + ", nombrePiloto=" + nombrePiloto + ", pais="
				+ pais + ", puntos=" + puntos + ", identificadorEquipo=" + identificadorEquipo + "]";
	}

	@Override
    public int hashCode() {
        return Objects.hash(identificadorPiloto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piloto other = (Piloto) obj;
        return identificadorPiloto == other.identificadorPiloto;
    }
}
