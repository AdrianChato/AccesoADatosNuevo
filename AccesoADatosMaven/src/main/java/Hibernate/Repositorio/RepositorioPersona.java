package Hibernate.Repositorio;

import java.util.List;

import org.hibernate.Session;

import Hibernate.Modelos.Persona;
import Hibernate.Modelos.Reunion;
import Hibernate.Util.AbstractDao;
import Hibernate.Util.HibernateUtil;
import jakarta.persistence.TypedQuery;

public class RepositorioPersona extends AbstractDao<Persona>{

	public RepositorioPersona() {
		setClase(Persona.class);
	}

	public List<Persona> getAllReuniones() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String queryString = "FROM " + Persona.class.getName();
		
		TypedQuery<Persona> query = sesion.createQuery(queryString, Persona.class);
		List<Persona> personas = query.getResultList();
		
		return personas;
	}
	
}
