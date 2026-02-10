package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelos.Evento;
import modelos.Participante;
import modelos.Ubicacion;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class UbicacionDao extends AbstractDao<Ubicacion> {

	public UbicacionDao() {
		setClase(Ubicacion.class);
	}
	Session sesion = HibernateUtil.getFactoriaSession().openSession();
	HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
	
	/*public List <Ubicacion> buscarPorUbi (String nombre) {
        return  (List<Ubicacion>) sesion.createQuery("FROM Evento WHERE ubicacion = :nombre", Ubicacion.class).setParameter("nombre", nombre);
*/
	}

