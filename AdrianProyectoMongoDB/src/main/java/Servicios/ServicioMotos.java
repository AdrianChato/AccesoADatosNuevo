package Servicios;

import java.util.ArrayList;
import java.util.List;

import Modelos.Moto;
import Repositorios.RepositorioMotos;
import com.mongodb.client.MongoDatabase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServicioMotos {

    private static final Logger logger = LogManager.getLogger(ServicioMotos.class);
    private RepositorioMotos repo;

    public ServicioMotos(MongoDatabase db) {
        this.repo = new RepositorioMotos(db);
        logger.info("Servicio de Motos inicializado");
    }

    // Guardar moto
    public void save(Moto moto) {
        repo.save(moto);
        logger.info("Moto guardada en servicio: " + moto.getModelo());
    }

    // Leer todas las motos
    public List<Moto> read() {
        return repo.read();
    }

    // FILTRO en Java: motos en venta
    public List<Moto> filtrarEnVenta() {
        List<Moto> lista = new ArrayList<>();
        List<Moto> todas = repo.getMotos();
        for (int i = 0; i < todas.size(); i++) {
            if (todas.get(i).isEnVenta()) {
                lista.add(todas.get(i));
            }
        }
        logger.info("Motos filtradas en venta: " + lista.size());
        return lista;
    }

    // ORDENACIÓN en Java: por CV descendente
    public List<Moto> ordenarPorCVDesc() {
        List<Moto> todas = repo.getMotos();
        List<Moto> lista = new ArrayList<>();

        // Copiar todas
        for (int i = 0; i < todas.size(); i++) {
            lista.add(todas.get(i));
        }

        // Ordenación burbuja simple
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size() - 1; j++) {
                if (lista.get(j).getCv() < lista.get(j + 1).getCv()) {
                    Moto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        logger.info("Motos ordenadas por CV descendente");
        return lista;
    }

    public RepositorioMotos getRepo() {
        return repo;
    }
}
