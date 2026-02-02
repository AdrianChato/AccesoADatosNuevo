package Repositorio;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import Modelo.Usuario;
import Util.AbstractDao;
import Util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

public class UsuarioDao extends AbstractDao<Usuario> {

    public UsuarioDao() {
        setClase(Usuario.class);
    }
    
 // 7. Actualización con CriteriaBuilder
    public void actualizarNombreCriteria(Long id, String nuevoNombre) {
        // Usamos try-with-resources para asegurar que la sesión se cierre
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
                // Creamos el CriteriaUpdate para la clase Usuario
                CriteriaUpdate<Usuario> update = cb.createCriteriaUpdate(Usuario.class);
                Root<Usuario> root = update.from(Usuario.class);

                // Seteamos el nuevo valor y la condición (asegúrate que el atributo se llame idUsuario)
                update.set(root.get("nombre"), nuevoNombre);
                update.where(cb.equal(root.get("idUsuario"), id));

                // En Hibernate 6+ se recomienda createMutationQuery para UPDATE/DELETE
                session.createMutationQuery(update).executeUpdate();
                
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e; // Lanzamos para ver el error real en el logger
            }
        }
    }

    // 8. Borrado con CriteriaBuilder
    public void borrarUsuarioNoVerificadoCriteria() {
        Session session = HibernateUtil.getFactoriaSession().openSession();
        Transaction tx = session.beginTransaction();
        try {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Usuario> delete = cb.createCriteriaDelete(Usuario.class);
            Root<Usuario> root = delete.from(Usuario.class);
            delete.where(cb.equal(root.get("verificado"), false));
            session.createMutationQuery(delete).executeUpdate();
            tx.commit();
        } catch (Exception e) { tx.rollback(); }
        finally { session.close(); }
    }
}
