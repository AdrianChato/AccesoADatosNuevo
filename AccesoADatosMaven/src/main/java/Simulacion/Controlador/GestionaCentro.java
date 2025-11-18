package Simulacion.Controlador;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Simulacion.Modelos.CentroLogistico;
import Simulacion.Modelos.Trabajador;
import Simulacion.Servicios.BancoAlimentos;
import Simulacion.Utilidades.BancoException;
import Simulacion.Utilidades.XMLDomBancoDeAlimentos;

public class GestionaCentro {
	private static final Logger logger = LogManager.getLogger(GestionaCentro.class);

	public static void main(String[] args) {

        logger.info("=== Iniciando carga de datos del Banco de Alimentos ===");

        try {
            XMLDomBancoDeAlimentos lector = new XMLDomBancoDeAlimentos();
            BancoAlimentos servicio = new BancoAlimentos();

            // Leer todos los centros (con sus trabajadores incluidos)
            Set<CentroLogistico> centros = lector.leerCentrosDesdeXML("bancoAlimentos.xml");

            // Agregar los centros completos al repositorio
            for (CentroLogistico centro : centros) {
                servicio.agregarCentro(centro);
            }

            // NO volver a agregar los trabajadores aquí, ya están dentro del centro
            logger.info("Centros y trabajadores cargados correctamente desde XML.");

            // Si quieres generar el CSV de voluntarios:
            servicio.generarCSVVoluntarios("voluntarios.csv");
            logger.info("Archivo CSV de voluntarios generado correctamente.");

        } catch (BancoException e) {
            logger.error("Error de negocio: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Error durante la ejecución del programa: {}", e.getMessage());
        }
    }
}
