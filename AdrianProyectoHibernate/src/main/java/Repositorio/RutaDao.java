package Repositorio;

import java.util.List;

import org.hibernate.Session;

import Modelo.Ruta;
import Util.AbstractDao;
import Util.HibernateUtil;

public class RutaDao extends AbstractDao<Ruta>{

	public RutaDao() {
        setClase(Ruta.class);
    }
	
	// 5. Consulta con AVG
    public Double promedioKmRutas() {
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            return session.createQuery("SELECT avg(r.distanciaKm) FROM Ruta r", Double.class).uniqueResult();
        }
    }

    // 6. Filtrar y ordenar
    public List<Ruta> rutasDificilesOrdenadas() {
        try (Session session = HibernateUtil.getFactoriaSession().openSession()) {
            return session.createQuery("FROM Ruta WHERE dificultad = 'Alta' ORDER BY distanciaKm DESC", Ruta.class).list();
        }
    }
}