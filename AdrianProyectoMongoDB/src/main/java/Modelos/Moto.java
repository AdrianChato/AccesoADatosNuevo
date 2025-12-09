package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Moto {

	private int num_bas;
	private float cc;
	private int cv;
	private TipoMotor motor;
	private String modelo;
	private boolean enVenta;
	private List <Propietario> propietarios;
	private Especificacion especificaciones;
	public int getNum_bas() {
		return num_bas;
	}
	public void setNum_bas(int num_bas) {
		this.num_bas = num_bas;
	}
	public float getCc() {
		return cc;
	}
	public void setCc(float cc) {
		this.cc = cc;
	}
	public int getCv() {
		return cv;
	}
	public void setCv(int cv) {
		this.cv = cv;
	}
	public TipoMotor getMotor() {
		return motor;
	}
	public void setMotor(TipoMotor motor) {
		this.motor = motor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public boolean isEnVenta() {
		return enVenta;
	}
	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}
	public List<Propietario> getPropietarios() {
		return propietarios;
	}
	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}
	public Especificacion getEspecificaciones() {
		return especificaciones;
	}
	public void setEspecificaciones(Especificacion especificaciones) {
		this.especificaciones = especificaciones;
	}
	@Override
	public String toString() {
		return "Moto [num_bas=" + num_bas + ", cc=" + cc + ", cv=" + cv + ", motor=" + motor + ", modelo=" + modelo
				+ ", enVenta=" + enVenta + ", propietarios=" + propietarios + ", especificaciones=" + especificaciones
				+ "]";
	}
	public Moto(int num_bas, float cc, int cv, TipoMotor motor, String modelo, boolean enVenta,
			List<Propietario> propietarios, Especificacion especificaciones) {
		super();
		this.num_bas = num_bas;
		this.cc = cc;
		this.cv = cv;
		this.motor = motor;
		this.modelo = modelo;
		this.enVenta = enVenta;
		this.propietarios = new ArrayList<>();
		this.especificaciones = especificaciones;
	}
	
	
	
	
}
