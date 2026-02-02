package Repositorio;

import java.util.List;

import org.hibernate.Session;

import Modelo.Moto;
import Util.AbstractDao;
import Util.HibernateUtil;

public class MotoDao extends AbstractDao<Moto> {

    public MotoDao() {
        setClase(Moto.class);
    }
    
 // 1. Restringir el número de elementos devueltos a 1
    public Moto obtenerPrimeraMoto() {
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            return session.createQuery("FROM Moto", Moto.class)
                          .setMaxResults(1)
                          .uniqueResult();
        }
    }

    // 2. Consulta que devuelva un campo (Matrículas)
    public List<String> obtenerTodasLasMatriculas() {
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            return session.createQuery("SELECT m.matricula FROM Moto m", String.class).list();
        }
    }

    // 3. Consulta que devuelva dos campos (Marca y Modelo)
    public List<Object[]> obtenerResumenMotos() {
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            return session.createQuery("SELECT m.marca, m.modelo FROM Moto m").list();
        }
    }
}
