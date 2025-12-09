package Modelos;

public class Especificacion {

	private int anio_matriculacion;
	private int propietarios_totales;
	private boolean revisiones;
	private boolean modificaciones;
	public int getAnio_matriculacion() {
		return anio_matriculacion;
	}
	public void setAnio_matriculacion(int anio_matriculacion) {
		this.anio_matriculacion = anio_matriculacion;
	}
	public int getPropietarios_totales() {
		return propietarios_totales;
	}
	public void setPropietarios_totales(int propietarios_totales) {
		this.propietarios_totales = propietarios_totales;
	}
	public boolean isRevisiones() {
		return revisiones;
	}
	public void setRevisiones(boolean revisiones) {
		this.revisiones = revisiones;
	}
	public boolean isModificaciones() {
		return modificaciones;
	}
	public void setModificaciones(boolean modificaciones) {
		this.modificaciones = modificaciones;
	}
	@Override
	public String toString() {
		return "Especificacion [anio_matriculacion=" + anio_matriculacion + ", propietarios_totales="
				+ propietarios_totales + ", revisiones=" + revisiones + ", modificaciones=" + modificaciones + "]";
	}
	public Especificacion(int anio_matriculacion, int propietarios_totales, boolean revisiones,
			boolean modificaciones) {
		super();
		this.anio_matriculacion = anio_matriculacion;
		this.propietarios_totales = propietarios_totales;
		this.revisiones = revisiones;
		this.modificaciones = modificaciones;
	}
	
	
}
