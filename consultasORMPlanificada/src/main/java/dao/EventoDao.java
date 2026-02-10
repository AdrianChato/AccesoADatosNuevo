package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import modelos.Evento;
import modelos.Participante;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class EventoDao extends AbstractDao<Evento> {

	public EventoDao() {
		setClase(Evento.class);
	}
	Session sesion = HibernateUtil.getFactoriaSession().openSession();
	HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();

	public List<Evento> duraMas91(){
		CriteriaQuery<Evento> query = cb.createQuery(Evento.class);
		Root<Evento> root = query.from(Evento.class);
		query.select(root);
		query.where(cb.greaterThanOrEqualTo(root.get("duracion"), 91));
		List<Evento> eventos = sesion.createQuery(query).getResultList();
		return eventos;
	}
	
	public List<Object[]> partipantesporEventos() {
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Participante> root = query.from(Participante.class);
        Join<Participante, Evento> joinEvento = root.join("eventos");
        query.multiselect(joinEvento.get("nombre"), cb.count(root));
        query.groupBy(joinEvento.get("nombre"));
        query.toString();
        return sesion.createQuery(query).getResultList();
    }
	
}