package Servicio;

import java.util.List;

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
import Repositorio.TransferenciaDao;
import Repositorio.UsuarioDao;

public class ServicioProyecto {
	private UsuarioDao usuarioDao = new UsuarioDao();
    private MotoDao motoDao = new MotoDao();
    private EventoDao eventoDao = new EventoDao();
    private TallerDao tallerDao = new TallerDao();
    private RutaDao rutaDao = new RutaDao();
    private TransferenciaDao transferenciaDao = new TransferenciaDao();

    // --- CRUD BÁSICO (Parte 1.e) ---
    public void registrarUsuario(Usuario u) { usuarioDao.create(u); }
    public void actualizarUsuario(Usuario u) { usuarioDao.update(u); }
    public void eliminarUsuario(Usuario u) { usuarioDao.delete(u); }

    public void registrarMoto(Moto m) { motoDao.create(m); }
    public void actualizarMoto(Moto m) { motoDao.update(m); }
    public void eliminarMoto(Moto m) { motoDao.delete(m); }

    public void registrarEvento(Evento e) { eventoDao.create(e); }
    public void eliminarEvento(Evento e) { eventoDao.delete(e); }

    public void registrarTaller(Taller t) { tallerDao.create(t); }
    public void eliminarTaller(Taller t) { tallerDao.delete(t); }

    public void registrarRuta(Ruta r) { rutaDao.create(r); }
    public void eliminarRuta(Ruta r) { rutaDao.delete(r); }

    public void registrarTransferencia(Transferencia t) { transferenciaDao.create(t); }
    public void eliminarTransferencia(Transferencia t) { transferenciaDao.delete(t); }

    // --- CONSULTAS HQL Y CRITERIA (Parte 2) ---
    public Moto obtenerTop1Moto() { return motoDao.obtenerPrimeraMoto(); }
    public List<String> obtenerMatriculas() { return motoDao.obtenerTodasLasMatriculas(); }
    public List<Object[]> obtenerResumenMotos() { return motoDao.obtenerResumenMotos(); }
    public List<Evento> buscarEventos(String tipo) { return eventoDao.buscarPorTipo(tipo); }
    public Long totalEventos() { return eventoDao.contarTotalEventos(); }
    public Double mediaKm() { return rutaDao.promedioKmRutas(); }
    public List<Ruta> rutasOrdenadas() { return rutaDao.rutasDificilesOrdenadas(); }

    public void actualizarUsuarioCriteria(Long id, String nombre) { 
        usuarioDao.actualizarNombreCriteria(id, nombre); 
    }
    public void borrarUsuariosCriteria() { 
        usuarioDao.borrarUsuarioNoVerificadoCriteria(); 
    }
}