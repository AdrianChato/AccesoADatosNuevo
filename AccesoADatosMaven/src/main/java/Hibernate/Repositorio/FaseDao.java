package Hibernate.Repositorio;

import Hibernate.Modelos.Fase;
import Hibernate.Util.AbstractDao;

public class FaseDao extends AbstractDao<Fase>{

	public FaseDao() {
		setClase(Fase.class);
	}
}
