package Hibernate.Repositorio;

import Hibernate.Modelos.Equipo;
import Hibernate.Util.AbstractDao;

public class EquipoDao extends AbstractDao<Equipo>{

	public EquipoDao() {
		setClase(Equipo.class);
	}
}
