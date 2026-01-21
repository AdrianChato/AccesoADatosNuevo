package Hibernate.Repositorio;

import java.util.List;

import org.hibernate.Session;

import Hibernate.Modelos.Acta;
import Hibernate.Modelos.Sala;
import Hibernate.Util.AbstractDao;
import Hibernate.Util.HibernateUtil;
import jakarta.persistence.TypedQuery;

public class RepositorioActa extends AbstractDao<Acta>{

	public RepositorioActa() {
		setClase(Acta.class);
	}
	
	public List<Acta> getAllActa() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String queryString = "FROM " + Acta.class.getName();
		
		TypedQuery<Acta> query = sesion.createQuery(queryString, Acta.class);
		List<Acta> actas = query.getResultList();
		
		return actas;
	}

	
}
