package hibernate;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import BoletinXML.Gestiona.GestionaPeliculas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Hibernate.Modelos.Reunion;
import Hibernate.Util.HibernateUtil;

public class TestReunionHibernate {
	private static final Logger logger =LogManager.getLogger(GestionaPeliculas.class);

	@Test
	void testCreateReunion() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		//Registramos una transacción
		sesion.beginTransaction();
		Reunion reunion = new Reunion();
		reunion.setAsunto("mi reunion de hoy");
		reunion.setFecha(LocalDateTime.now());
		sesion.persist(reunion);
		sesion.getTransaction().commit();
		sesion.close();		
	}
	
	@Test
	void testRetrieveReunion() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		Reunion r = sesion.find(Reunion.class, 1);
		logger.debug("El asunto es:"+r.getAsunto());
		sesion.close();
	}

	
	@Test
	void testUpdateReunion() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		Reunion r = sesion.find(Reunion.class, 1);
		sesion.beginTransaction();
		r.setAsunto("Nuevo Asunto --");
		sesion.getTransaction().commit();
		sesion.close();
	}

	
	@Test
	void testDeleteReunion() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		sesion.beginTransaction();
		sesion.remove(sesion.find(Reunion.class, 2));
		sesion.getTransaction().commit();
		sesion.close();	
	}
 
}
