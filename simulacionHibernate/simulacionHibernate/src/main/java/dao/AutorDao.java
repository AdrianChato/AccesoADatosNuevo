package dao;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import modelos.Autor;
import utiles.AbstractDao;

public class AutorDao extends AbstractDao<Autor> {

	public AutorDao() {
		setClase(Autor.class);
	}
	
}