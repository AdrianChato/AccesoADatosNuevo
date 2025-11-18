package Repaso2.repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Repaso2.exceptions.ConversacionException;
import Repaso2.model.Conversacion;
import Repaso2.model.TipoAgente;

public class RepositorioConversaciones implements IRepositorioConversaciones{
	

	private Set<Conversacion> conversaciones;
	
	
	
	public RepositorioConversaciones() {
		super();
		this.conversaciones = new HashSet<Conversacion>();
	}

	public void agregaConversacion(TipoAgente tipo, String pregunta,
			String respuesta) {
		
		Conversacion a = new Conversacion(pregunta, respuesta, tipo);
		conversaciones.add(a);
		
	}
			
			public Conversacion getConversacion(LocalDate fecha, TipoAgente tipo,
			String pregunta) throws ConversacionException {
				boolean encontrado = false;
				Conversacion a = null;
				Iterator<Conversacion> it = conversaciones.iterator();
				while (it.hasNext() && !encontrado) {
					Conversacion nueva = it.next();
					
					if(nueva.getTipo().equals(tipo) && nueva.getFechaConversacion().equals(fecha)&& nueva.getPregunta().equals(pregunta)) {
						encontrado = true;
						a = nueva;
					} else {
						throw new ConversacionException("No existe esa conversacion");
					}
				}
				
				return a;
			}
			
			public boolean contieneConversacionConversacion(Conversacion
			conversacion) {
				
				return conversaciones.contains(conversacion);
			}
			
			public void eliminaConversacion(LocalDate fecha, TipoAgente
			tipo, String pregunta) throws ConversacionException {
				
				Conversacion eliminaconversacion = getConversacion(fecha, tipo, pregunta);
				if (contieneConversacionConversacion(eliminaconversacion) == true) {
					conversaciones.remove(eliminaconversacion);
				} else {
					throw new ConversacionException("No existe");
				}
			}
			
			public void incrementaNumeroValoraciones(LocalDate fecha,
			TipoAgente tipo, String pregunta, double valoracion) throws
			ConversacionException {
				
				Conversacion c = getConversacion(fecha, tipo, pregunta);
				
				if (c==null) {
					throw new ConversacionException("No existe");
				} else  {
					c.setNumValoracionesPositivas(valoracion++);
				}
				
			}
			}
