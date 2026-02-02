package Hibernate.Repositorio;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import Hibernate.Modelos.Alumno;
import Hibernate.Util.AbstractDao;
import Hibernate.Util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class RepositorioAlumno extends AbstractDao<Alumno>{
	private static final Logger logger =LogManager.getLogger(RepositorioAlumno.class);

	public RepositorioAlumno() {
		setClase(Alumno.class);
	}
	
	public List<Alumno> getAlumnosCB()
	{
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
		Root<Alumno> root = query.from(Alumno.class);
		query.select(root);
		List<Alumno> alumnos = sesion.createQuery(query).getResultList();
		return alumnos;
	}
	
	public List<Object[]> getAlumnosNombreEmailCB()
	{
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<Alumno> root = query.from(Alumno.class);
		query.multiselect(root.get("nombre"), root.get("email"));
		List<Object[]> resultado = sesion.createQuery(query).getResultList();
		for (Object[] fila : resultado) {
			logger.debug("Nombre: " + fila[0] + " Email: " + fila[1]);
		}
		return resultado;
	}
	
	/*public List<Alumno> getAlumnos18CB()
	{
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
		Root<Alumno> root = query.from(Alumno.class);
		query.select(root);
		query.where(cb.greaterThanOrEqualTo(root.get("edad", 18));
		List<Alumno> alumnos = sesion.createQuery(query).getResultList();
		return alumnos;
	}*/
	
	public List<Alumno> getAlumnosNombreCB(String nombre)
	{
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
		Root<Alumno> root = query.from(Alumno.class);
		query.select(root);
		query.where(cb.equal(root.get("nombre"), nombre));
		List<Alumno> alumnos = sesion.createQuery(query).getResultList();
		return alumnos;
	}

	
	

}
