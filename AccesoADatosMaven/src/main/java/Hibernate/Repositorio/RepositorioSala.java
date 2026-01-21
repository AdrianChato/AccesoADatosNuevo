package Hibernate.Repositorio;

import java.util.List;

import org.hibernate.Session;

import Hibernate.Modelos.Sala;
import Hibernate.Util.AbstractDao;
import Hibernate.Util.HibernateUtil;
import jakarta.persistence.TypedQuery;

public class RepositorioSala extends AbstractDao<Sala>{

	public RepositorioSala() {
		setClase(Sala.class);
	}
	
	public List<Sala> getAllSala() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String queryString = "FROM " + Sala.class.getName();
		
		TypedQuery<Sala> query = sesion.createQuery(queryString, Sala.class);
		List<Sala> salas = query.getResultList();
		
		return salas;
	}

	
}

