package Repaso2.repository;

import java.time.LocalDate;

import Repaso2.exceptions.ConversacionException;
import Repaso2.model.Conversacion;
import Repaso2.model.TipoAgente;

public interface IRepositorioConversaciones {

	public void agregaConversacion(TipoAgente tipo, String pregunta,
			String respuesta);
			
			public Conversacion getConversacion(LocalDate fecha, TipoAgente tipo,
			String pregunta) throws ConversacionException;
			
			public boolean contieneConversacionConversacion(Conversacion
			conversacion);
			
			public void eliminaConversacion(LocalDate fecha, TipoAgente
			tipo, String pregunta) throws ConversacionException;
			
			public void incrementaNumeroValoraciones(LocalDate fecha,
			TipoAgente tipo, String pregunta, double valoracion) throws
			ConversacionException;
			}

