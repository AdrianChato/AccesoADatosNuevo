package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import modelos.Articulo;
import modelos.Revista;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class RevistaDao extends AbstractDao<Revista> {

	public RevistaDao() {
		setClase(Revista.class);
	}

	/*
	  CONSULTA 4: Número de artículos que tiene cada revista. Usamos COUNT y GROUP
	  BY.
	 */
	public List<Object[]> getConteoArticulosPorRevista() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<Revista> root = query.from(Revista.class);

		// Unimos con artículos para poder contarlos [cite: 36]
		Join<Revista, Articulo> joinArticulos = root.join("articulos", JoinType.LEFT);

		// Seleccionamos nombre de revista y el conteo de sus artículos
		query.multiselect(root.get("nombreRevista"), cb.count(joinArticulos)).groupBy(root.get("nombreRevista"));

		List<Object[]> lista = sesion.createQuery(query).getResultList();
		sesion.close();
		return lista;
	}

	/*
	 CONSULTA 5: Datos de revistas (nombre, fecha, número) publicadas antes de una
	 fecha
	 */
	public List<Object[]> getRevistasPreviasAFecha(LocalDate fechaLimite) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		// Ejemplo en HQL 
		String hql = "SELECT r.nombreRevista, r.fecha, r.numeroRevista FROM Revista r WHERE r.fecha < :miFecha";

		List<Object[]> lista = sesion.createQuery(hql, Object[].class).setParameter("miFecha", fechaLimite)
				.getResultList();
		sesion.close();
		return lista;
	}
}
