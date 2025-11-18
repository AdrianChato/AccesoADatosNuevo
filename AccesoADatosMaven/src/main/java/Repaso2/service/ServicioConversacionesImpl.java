package Repaso2.service;

import java.time.LocalDate;

import Repaso2.exceptions.ConversacionException;
import Repaso2.model.Conversacion;
import Repaso2.model.TipoAgente;
import Repaso2.repository.RepositorioConversaciones;

public class ServicioConversacionesImpl implements IServicioConversaciones{

	RepositorioConversaciones repo= new RepositorioConversaciones();
	
	@Override
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta, String respuesta) {
		repo.agregaConversacion(tipo, pregunta, respuesta);
		
	}

	@Override
	public Conversacion getRecuperaConversacion(TipoAgente tipo, String pregunta, LocalDate fecha) {
		Conversacion c = new Conversacion();	
		try {
			c = repo.getConversacion(fecha, tipo, pregunta);
		} catch (ConversacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo) throws ConversacionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getValoracionMediaParaHumanos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValoracionMedidaParaBots() {
		// TODO Auto-generated method stub
		return 0;
	}

}
