package Repositorio;

import Modelo.Taller;
import Util.AbstractDao;

public class TallerDao extends AbstractDao<Taller> {

    public TallerDao() {
        setClase(Taller.class);
    }
}
