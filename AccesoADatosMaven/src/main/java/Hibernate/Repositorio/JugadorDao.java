package Hibernate.Repositorio;

import Hibernate.Modelos.Jugador;
import Hibernate.Util.AbstractDao;

public class JugadorDao extends AbstractDao<Jugador>{

	public JugadorDao() {
		setClase(Jugador.class);
	}
}
