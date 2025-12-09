package Controlador;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Config.MongoDBConexion;
import Modelos.Moto;
import Modelos.Propietario;
import Modelos.Especificacion;
import Modelos.TipoMotor;
import Servicios.ServicioMotos;
import com.mongodb.client.MongoDatabase;

public class GestionaMotoLifeBD {

    private static final Logger logger = LogManager.getLogger(GestionaMotoLifeBD.class);

    public static void main(String[] args) {

        logger.info("Iniciando aplicación MotoLifeBD...");

        // Conectar a MongoDB
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();

        // Crear servicio
        ServicioMotos service = new ServicioMotos(db);

        logger.info("Conexión establecida y servicio inicializado.");

        //EScribir

        List<Propietario> propietarios = new ArrayList<>();
        propietarios.add(new Propietario("12345678A", "Carlos Pérez", "C/ Luna 4", 678123456));
        propietarios.add(new Propietario("98765432B", "Ana López", "Av. Sol 22", 612457894));

        Especificacion especificacion =
                new Especificacion(2019, 2, true, false);

        Moto nuevaMoto = new Moto(
                9999,
                600.0f,
                85,
                TipoMotor.GASOLINA,
                "Honda CB600F",
                true,
                propietarios,
                especificacion
        );

        logger.info("Guardando moto nueva...");

        service.save(nuevaMoto);

        logger.info("Moto guardada correctamente.");

        //Leer

        logger.info("Leyendo todas las motos de la base de datos...");

        List<Moto> todas = service.read();

        logger.info("Total motos encontradas: " + todas.size());
        for (Moto m : todas) {
            logger.info(m.toString());
        }

        //Filtro servicio

        logger.info("Filtrando motos en venta (JAVA)...");

        List<Moto> enVenta = service.filtrarEnVenta();
        logger.info("Motos en venta: " + enVenta.size());
        for (Moto m : enVenta) {
            logger.info(m);
        }

        //Ordena descendente

        logger.info("Ordenando motos por CV descendente (JAVA)...");

        List<Moto> ordenadasCV = service.ordenarPorCVDesc();
        for (Moto m : ordenadasCV) {
            logger.info(m);
        }

        //FIltar

        logger.info("Filtrando motos en venta (QUERY REPOSITORIO)...");

        List<Moto> enVentaQuery = service.getRepo().filtrarEnVentaQuery();

        logger.info("Motos en venta por query: " + enVentaQuery.size());
        for (Moto m : enVentaQuery) {
            logger.info(m);
        }

        //Ordenar ascendente

        logger.info("Ordenando motos por CC ascendente (QUERY REPOSITORIO)...");

        List<Moto> ordenadasCCQuery = service.getRepo().ordenarPorCCQuery();

        for (Moto m : ordenadasCCQuery) {
            logger.info(m);
        }

        logger.info("=== FIN DE LAS PRUEBAS ===");
    }
}
