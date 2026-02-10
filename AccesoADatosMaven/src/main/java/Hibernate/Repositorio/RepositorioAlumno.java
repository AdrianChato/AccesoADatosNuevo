package Hibernate.Repositorio;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import Hibernate.Modelos.Alumno;
import Hibernate.Modelos.Curso;
import Hibernate.Util.AbstractDao;
import Hibernate.Util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

public class RepositorioAlumno extends AbstractDao<Alumno> {

	public RepositorioAlumno() {
		setClase(Alumno.class); 

	}
	Session sesion = HibernateUtil.getFactoriaSession().openSession();
	HibernateCriteriaBuilder cb = sesion.getCriteriaBuilder();
//1
	public List<Alumno> obtenertodosalumnos() {
		
		CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
		Root<Alumno> root = query.from(Alumno.class);
		query.select(root);
		List<Alumno> alumnos = sesion.createQuery(query).getResultList();
		return alumnos;
	}
//2
	public List<Object[]> nomyemail(){
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<Alumno> root = query.from(Alumno.class);
		query.multiselect(
					root.get("nombre"),root.get("email")
					);		
		
		List<Object[]> resultados = sesion.createQuery(query).getResultList();
		for (Object[] fila : resultados) {
	           System.out.println("Nombre: " + fila[0] + ", Email: " + fila[1]);
	       }
		return resultados;
	}
	
	
	//3 
	public List<Alumno> mas18(){
		CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
		Root<Alumno> root = query.from(Alumno.class);
		query.select(root);
		query.where(cb.greaterThanOrEqualTo(root.get("edad"), 18));
		List<Alumno> anas = sesion.createQuery(query).getResultList();
		return anas;
	}
	
	
	
	
	
	
	
	
	//4
	public List<Alumno> ana(){
		CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
		Root<Alumno> root = query.from(Alumno.class);
		query.select(root);
		query.where(cb.equal(root.get("nombre"), "ana"));
		List<Alumno> anas = sesion.createQuery(query).getResultList();
		return anas;	
		}
	// 5. Obtener los alumnos ordenados por edad de forma descendente
    public List<Alumno> alumnosOrdenadosEdadDesc() {
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> root = query.from(Alumno.class);
        query.select(root).orderBy(cb.desc(root.get("edad")));
        return sesion.createQuery(query).getResultList();
    }

    // 6. Obtener los alumnos cuyo curso se llame "DAM"
    public List<Alumno> alumnosEnDAM() {
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> root = query.from(Alumno.class);
        Join<Alumno, Curso> joinCurso = root.join("curso");
        query.select(root).where(cb.equal(joinCurso.get("nombre"), "DAM"));
        return sesion.createQuery(query).getResultList();
    }

    // 7. Obtener los alumnos cuyo curso sea de nivel "Superior"
    public List<Alumno> alumnosNivelSuperior() {
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> root = query.from(Alumno.class);
        Join<Alumno, Curso> joinCurso = root.join("curso");
        query.select(root).where(cb.equal(joinCurso.get("nivel"), "Superior"));
        return sesion.createQuery(query).getResultList();
    }

    // 8. Obtener los cursos que tengan alumnos matriculados
    public List<Curso> cursosConAlumnos() {
        CriteriaQuery<Curso> query = cb.createQuery(Curso.class);
        Root<Curso> root = query.from(Curso.class);
        query.select(root).where(cb.isNotEmpty(root.get("alumnos")));
        return sesion.createQuery(query).getResultList();
    }

    // 9. Obtener solo los nombres de los alumnos
    public List<String> soloNombres() {
        CriteriaQuery<String> query = cb.createQuery(String.class);
        Root<Alumno> root = query.from(Alumno.class);
        query.select(root.get("nombre"));
        return sesion.createQuery(query).getResultList();
    }

    // 10. Obtener el nombre y la edad de los alumnos
    public List<Object[]> nombreYEdad() {
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Alumno> root = query.from(Alumno.class);
        query.multiselect(root.get("nombre"), root.get("edad"));
        return sesion.createQuery(query).getResultList();
    }

    // 11. Obtener el nombre del alumno y el nombre de su curso
    public List<Object[]> nombreAlumnoYCurso() {
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Alumno> root = query.from(Alumno.class);
        Join<Alumno, Curso> joinCurso = root.join("curso");
        query.multiselect(root.get("nombre"), joinCurso.get("nombre"));
        return sesion.createQuery(query).getResultList();
    }

    // 12. Obtener el nombre de los cursos sin repetir (distinct)
    public List<String> nombresCursosUnicos() {
        CriteriaQuery<String> query = cb.createQuery(String.class);
        Root<Curso> root = query.from(Curso.class);
        query.select(root.get("nombre")).distinct(true);
        return sesion.createQuery(query).getResultList();
    }

    // 13. Contar el número total de alumnos
    public Long contarTotalAlumnos() {
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Alumno> root = query.from(Alumno.class);
        query.select(cb.count(root));
        return sesion.createQuery(query).getSingleResult();
    }

    // 14. Obtener la edad media de los alumnos
    public Double edadMediaAlumnos() {
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Alumno> root = query.from(Alumno.class);
        query.select(cb.avg(root.get("edad")));
        return sesion.createQuery(query).getSingleResult();
    }

    // 15. Obtener el número de alumnos por curso
    public List<Object[]> alumnosPorCurso() {
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Alumno> root = query.from(Alumno.class);
        Join<Alumno, Curso> joinCurso = root.join("curso");
        query.multiselect(joinCurso.get("nombre"), cb.count(root));
        query.groupBy(joinCurso.get("nombre"));
        return sesion.createQuery(query).getResultList();
    }

 // 16. Incrementar en 1 año la edad de todos los alumnos
    public void incrementarEdadTodos() {
        sesion.beginTransaction();
        try {
            CriteriaUpdate<Alumno> update = cb.createCriteriaUpdate(Alumno.class);
            Root<Alumno> root = update.from(Alumno.class);
            
            // Solución a la ambigüedad: Casteo explícito del Path
            Path<Integer> edadPath = root.get("edad");
            Expression<Integer> nuevaEdad = cb.sum(edadPath, 1);
            
            update.set(edadPath, nuevaEdad);
            
            sesion.createMutationQuery(update).executeUpdate();
            sesion.getTransaction().commit();
        } catch (Exception e) {
            if (sesion.getTransaction() != null) sesion.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    // 17. Cambiar el nivel a "Superior" de todos los cursos llamados "DAM"
    public void cambiarNivelDAM() {
        sesion.beginTransaction();
        CriteriaUpdate<Curso> update = cb.createCriteriaUpdate(Curso.class);
        Root<Curso> root = update.from(Curso.class);
        update.set(root.get("nivel"), "Superior");
        update.where(cb.equal(root.get("nombre"), "DAM"));
        sesion.createMutationQuery(update).executeUpdate();
        sesion.getTransaction().commit();
    }

    // 18. Asignar una edad fija (18) a los alumnos menores de esa edad
    public void regularizarEdadMinima() {
        sesion.beginTransaction();
        CriteriaUpdate<Alumno> update = cb.createCriteriaUpdate(Alumno.class);
        Root<Alumno> root = update.from(Alumno.class);
        update.set(root.get("edad"), 18);
        update.where(cb.lessThan(root.get("edad"), 18));
        sesion.createMutationQuery(update).executeUpdate();
        sesion.getTransaction().commit();
    }

    // 19. Eliminar los alumnos mayores de 65 años
    public void eliminarMayores65() {
        sesion.beginTransaction();
        CriteriaDelete<Alumno> delete = cb.createCriteriaDelete(Alumno.class);
        Root<Alumno> root = delete.from(Alumno.class);
        delete.where(cb.greaterThan(root.get("edad"), 65));
        sesion.createMutationQuery(delete).executeUpdate();
        sesion.getTransaction().commit();
    }

    // 20. Eliminar los cursos que no tengan alumnos
    public void eliminarCursosSinAlumnos() {
        sesion.beginTransaction();
        CriteriaDelete<Curso> delete = cb.createCriteriaDelete(Curso.class);
        Root<Curso> root = delete.from(Curso.class);
        delete.where(cb.isEmpty(root.get("alumnos")));
        sesion.createMutationQuery(delete).executeUpdate();
        sesion.getTransaction().commit();
    }
}