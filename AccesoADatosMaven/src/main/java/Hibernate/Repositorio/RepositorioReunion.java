package Hibernate.Repositorio;

import java.util.List;

import org.hibernate.Session;

import Hibernate.Modelos.Reunion;
import Hibernate.Util.AbstractDao;
import Hibernate.Util.HibernateUtil;
import jakarta.persistence.TypedQuery;

public class RepositorioReunion extends AbstractDao<Reunion>{

	public RepositorioReunion() {
		setClase(Reunion.class);
	}
	
	public List<Reunion> getAllReuniones() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String queryString = "FROM " + Reunion.class.getName();
		
		TypedQuery<Reunion> query = sesion.createQuery(queryString, Reunion.class);
		List<Reunion> reuniones = query.getResultList();
		
		return reuniones;
	}

	
}
