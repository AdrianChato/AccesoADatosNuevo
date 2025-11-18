package Simulacion.Servicios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Simulacion.Modelos.CentroLogistico;
import Simulacion.Modelos.TipoPersonal;
import Simulacion.Modelos.Trabajador;
import Simulacion.Repositorios.RepositorioCentro;
import Simulacion.Utilidades.BancoException;


public class BancoAlimentos {

    private RepositorioCentro repo;

    public BancoAlimentos() {
        this.repo = new RepositorioCentro();
    }

    public void agregarCentro(CentroLogistico c) throws BancoException {
        repo.agregarCentro(c);
    }

    public void agregarTrabajador(Trabajador t) throws BancoException {
        repo.agregarTrabajadorACentro(t);
    }

    public CentroLogistico buscarCentro(String id) {
        return repo.getCentroPorId(id);
    }

    public Trabajador buscarTrabajador(String dni) {
        return repo.getTrabajadorPorDni(dni);
    }

    /**
     * Devuelve todos los colaboradores de un tipo (voluntario o asalariado)
     */
    public List<Trabajador> getColaboradoresPorTipo(TipoPersonal tipo) {
        List<Trabajador> resultado = new ArrayList<>();
        for (CentroLogistico c : repo.getCentros()) {
            for (Trabajador t : c.getPersonal()) {
                if (t.getTipo() == tipo) {
                    resultado.add(t);
                }
            }
        }
        return resultado;
    }

    /**
     * Genera un archivo CSV con los voluntarios.
     */
    public void generarCSVVoluntarios(String ruta) throws IOException {
        List<Trabajador> voluntarios = getColaboradoresPorTipo(TipoPersonal.VOLUNTARIO);

        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("NombreV,DniV,FechaNac,NombreC,IdentificadorC,CiudadC\n");
            for (Trabajador v : voluntarios) {
                CentroLogistico centro = repo.getCentroPorId(v.getIdCentro());
                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                        v.getNombre(), v.getDni(), v.getFechaNacimiento(),
                        centro.getNombre(), centro.getId(), centro.getCiudad()));
            }
        }
    }
}
