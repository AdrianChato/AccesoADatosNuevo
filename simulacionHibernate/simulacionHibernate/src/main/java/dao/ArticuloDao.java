package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import modelos.Articulo;
import modelos.Autor;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class ArticuloDao extends AbstractDao<Articulo> {

	public ArticuloDao() {
		setClase(Articulo.class);
	}

	/*
	  CONSULTA 1: Artículos de un autor por su nombre (Ordenados alfabéticamente).
	 */
	public List<Articulo> getArticulosPorNombreAutor(String nombreAutor) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<Articulo> query = cb.createQuery(Articulo.class);
		Root<Articulo> root = query.from(Articulo.class);

		// JOIN: Unimos Articulo con su colección de autores
		Join<Articulo, Autor> joinAutor = root.join("autores");

		query.select(root).where(cb.equal(joinAutor.get("nombre"), nombreAutor)).orderBy(cb.asc(root.get("titulo"))); // Orden
																														// alfabético
																														// por
																														// título

		List<Articulo> lista = sesion.createQuery(query).getResultList();
		sesion.close();
		return lista;
	}

	/*
	  CONSULTA 2 y 3: Nombre, páginas, revista y fecha de artículos con > 6
	  páginas. Devuelve Object[] porque pedimos campos específicos de varias
	  tablas.
	 */
	public List<Object[]> getArticulosLargosConRevista() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<Articulo> root = query.from(Articulo.class);

		// Calculamos la diferencia de páginas: fin - inicio
		Expression<Integer> numPaginas = cb.diff(root.get("numPaginaFin"), root.get("numPaginaInicio"));

		// multiselect para: título, nº páginas, nombre revista, fecha revista
		query.multiselect(root.get("titulo"), numPaginas, root.get("revista").get("nombreRevista"),
				root.get("revista").get("fecha")).where(cb.gt(numPaginas, 6)); // Filtro: más de 6 páginas

		List<Object[]> resultados = sesion.createQuery(query).getResultList();
		sesion.close();
		return resultados;
	}
}