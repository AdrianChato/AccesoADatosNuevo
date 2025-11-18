package Repaso2.service;

import java.time.LocalDate;

import Repaso2.exceptions.ConversacionException;
import Repaso2.model.Conversacion;
import Repaso2.model.TipoAgente;

public interface IServicioConversaciones {
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta,
			String respuesta);
			public Conversacion getRecuperaConversacion(TipoAgente tipo, String
			pregunta, LocalDate fecha);
			public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo)
			throws ConversacionException;
			public boolean incrementaNumeroValoraciones(LocalDate fecha,
			TipoAgente tipo, String pregunta);
			public double getValoracionMediaParaHumanos();
			public double getValoracionMedidaParaBots();
			}

