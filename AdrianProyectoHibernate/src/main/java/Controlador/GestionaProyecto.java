package Controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Modelo.Evento;
import Modelo.Moto;
import Modelo.Ruta;
import Modelo.Taller;
import Modelo.Transferencia;
import Modelo.Usuario;
import Repositorio.EventoDao;
import Repositorio.MotoDao;
import Repositorio.RutaDao;
import Repositorio.TallerDao;
import Repositorio.UsuarioDao;
import Servicio.ServicioProyecto;
import Util.HibernateUtil;


public class GestionaProyecto {
	
	private static final Logger logger =LogManager.getLogger(GestionaProyecto.class);

	public static void main(String[] args) {
		ServicioProyecto servicio = new ServicioProyecto();

		try {
            // 1. ALTAS (6 Entidades y sus relaciones)
            Usuario venedor = new Usuario("Adrian", "Vendedor", "adrian@test.com", "usuario");
            servicio.registrarUsuario(venedor);

            Usuario comprador = new Usuario("Juan", "Comprador", "juan@test.com", "usuario");
            servicio.registrarUsuario(comprador);

            Taller taller = new Taller();
            taller.setNombreTaller("MotoExpert");
            taller.setUsuario(venedor); // 1:1
            servicio.registrarTaller(taller);

            Moto moto = new Moto();
            moto.setMarca("Yamaha");
            moto.setPropietario(venedor); // 1:N
            servicio.registrarMoto(moto);

            Evento ev = new Evento();
            ev.setTipoEvento("Mantenimiento");
            ev.setMoto(moto);
            servicio.registrarEvento(ev);

            Ruta ruta = new Ruta();
            ruta.setNombre("Ruta de Montaña");
            ruta.setDistanciaKm(150);
            ruta.setMotos(new ArrayList<>());
            ruta.getMotos().add(moto); // N:M
            servicio.registrarRuta(ruta);

            // CORRECCIÓN: Transferencia con nombres exactos de tu clase
            Transferencia trans = new Transferencia();
            trans.setMoto(moto);
            trans.setPropietarioAnterior(venedor);
            trans.setPropietarioNuevo(comprador);
            trans.setFechaTransferencia(LocalDate.now());
            trans.setEstado("COMPLETADA");
            servicio.registrarTransferencia(trans);

            logger.info("Altas de los 6 modelos realizadas.");

            // 2. ACTUALIZACIÓN Y CASCADE
            logger.info("--- Probando Actualización y Cascade ---");
            moto.setModelo("MT-09");
            servicio.actualizarMoto(moto); // Aquí se explica el CASCADE según tu mapeo

            // 3. ELIMINACIÓN
            logger.info("--- Probando Bajas ---");
            servicio.eliminarTransferencia(trans);
            logger.info("Transferencia eliminada.");

            // 4. CONSULTAS PARTE 2
            logger.info("--- Ejecutando Consultas Parte 2 ---");
            logger.info("Count Eventos: " + servicio.totalEventos());
            logger.info("Media KM: " + servicio.mediaKm());
            
            // Criteria (Usa el ID de tu objeto usuario)
            servicio.actualizarUsuarioCriteria(venedor.getIdUsuario(), "Adrian Verificado");
            logger.info("Nombre actualizado con Criteria.");

        } catch (Exception e) {
            logger.error("ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
            logger.info("=== FIN PROYECTO ===");
        }
    }
}
