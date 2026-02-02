package Repositorio;

import Modelo.Transferencia;
import Util.AbstractDao;

public class TransferenciaDao extends AbstractDao<Transferencia> {

    public TransferenciaDao() {
        setClase(Transferencia.class);
    }
}
