package Repositorio;

import java.util.List;

import org.hibernate.Session;

import Modelo.Evento;
import Util.AbstractDao;
import Util.HibernateUtil;

public class EventoDao extends AbstractDao<Evento> {

    public EventoDao() {
        setClase(Evento.class);
    }
 // 4. Consulta parametrizada (Eventos por tipo)
    public List<Evento> buscarPorTipo(String tipo) {
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            return session.createQuery("FROM Evento WHERE tipoEvento = :tipo", Evento.class)
                          .setParameter("tipo", tipo).list();
        }
    }

    // 5. Consulta con COUNT
    public Long contarTotalEventos() {
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            return session.createQuery("SELECT count(e) FROM Evento e", Long.class).uniqueResult();
        }
    }
}