package Simulacion.Repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Simulacion.Modelos.CentroLogistico;
import Simulacion.Modelos.Trabajador;
import Simulacion.Utilidades.BancoException;

public class RepositorioCentro {

    private Set<CentroLogistico> centros;

    public RepositorioCentro() {
        this.centros = new HashSet<>();
    }

    public void agregarCentro(CentroLogistico c) throws BancoException {
        if (c == null) {
            throw new BancoException("El centro logístico no puede ser nulo.");
        }
        // Evitar duplicados por ID
        for (CentroLogistico existente : centros) {
            if (existente.getId().equalsIgnoreCase(c.getId())) {
                throw new BancoException("Ya existe un centro con el ID: " + c.getId());
            }
        }
        centros.add(c);
    }

    public void agregarTrabajadorACentro(Trabajador t) throws BancoException {
        if (t == null) {
            throw new BancoException("El trabajador no puede ser nulo.");
        }

        CentroLogistico centro = getCentroPorId(t.getIdCentro());
        if (centro == null) {
            throw new BancoException("No se encontró el centro con ID: " + t.getIdCentro());
        }

        if (centro.getPersonal() == null) {
            centro.setPersonal(new ArrayList<>());
        }

        // Evitar duplicados por DNI
        for (Trabajador existente : centro.getPersonal()) {
            if (existente.getDni().equalsIgnoreCase(t.getDni())) {
                throw new BancoException("Ya existe un trabajador con el DNI: " + t.getDni());
            }
        }

        centro.getPersonal().add(t);
    }

    public CentroLogistico getCentroPorId(String id) {
        if (id == null) return null;

        for (CentroLogistico c : centros) {
            if (c.getId().equalsIgnoreCase(id.trim())) {
                return c;
            }
        }
        return null;
    }

    public Trabajador getTrabajadorPorDni(String dni) {
        if (dni == null) return null;

        for (CentroLogistico c : centros) {
            for (Trabajador t : c.getPersonal()) {
                if (t.getDni().equalsIgnoreCase(dni.trim())) {
                    return t;
                }
            }
        }
        return null;
    }

    public Set<CentroLogistico> getCentros() {
        return centros;
    }

    public List<Trabajador> getTodosLosTrabajadores() {
        List<Trabajador> todos = new ArrayList<>();
        for (CentroLogistico c : centros) {
            todos.addAll(c.getPersonal());
        }
        return todos;
    }
}
