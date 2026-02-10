package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelos.Participante;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class ParticipanteDao extends AbstractDao<Participante> {

	public ParticipanteDao() {
		setClase(Participante.class);
	}
	
	Session sesion = HibernateUtil.getFactoriaSession().openSession();
	HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
	
	public List<Participante> participantesOrdenadosapellidoAsc() {
        CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
        Root<Participante> root = query.from(Participante.class);
        query.select(root).orderBy(cb.asc(root.get("apellidos")));
        return sesion.createQuery(query).getResultList();
    }
}