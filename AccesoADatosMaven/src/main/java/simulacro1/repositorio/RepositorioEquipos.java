package unidad1.simulacro1.repositorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import unidad1.simulacro1.modelo.Equipo;
import unidad1.simulacro1.modelo.Piloto;

public class RepositorioEquipos {
	

    public RepositorioEquipos() {
		super();
	}

	public RepositorioEquipos(Set<Equipo> equipos) {
		super();
		this.equipos = equipos;
	}

	private Set<Equipo> equipos = new HashSet<>();

    public void addEquipo(Equipo e) {
        equipos.add(e);
    }

    public Set<Equipo> recuperarEquipos() {
        return equipos;
    }

  /*  public void añadirPiloto(Piloto piloto) {
        for (Equipo equipo : equipos) {
            if (equipo.getIdentificadorEquipo() == piloto.getIdentificadorEquipo()) {
                equipo.getPilotos().add(piloto); // ✅ Añade directamente al Set
                return;
            }
        }
    }

*/

    /*public List<Piloto> getPilotosConMasDe(int puntos) {
        List<Piloto> resultado = new ArrayList<>();
        for (Equipo e : equipos.values()) {
            for (Piloto p : e.getPilotos()) {
                if (p.getPuntos() > puntos) resultado.add(p);
            }
        }
        return resultado;
    }

    public Collection<Equipo> getEquipos() {
        return equipos.values();
    }
}*/
}
	
	
	
	
	

