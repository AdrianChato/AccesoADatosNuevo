package unidad1.simulacro1.utilidades;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import unidad1.simulacro1.modelo.Equipo;
import unidad1.simulacro1.modelo.Piloto;





public class XMLDomEquipo {
	private static final String rutare= "src\\main\\resources\\";
    private static final Logger logger = LogManager.getLogger(XMLDomEquipo.class);

    private Document getDocumentFromXML(String nombreFichero) {
        File file = new File(rutare + nombreFichero);
        Document documento = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            documento = dBuilder.parse(file);
            documento.getDocumentElement().normalize();
            logger.info("XML cargado correctamente: " + nombreFichero);
        } catch (Exception e) {
            logger.error("Error al cargar el XML: " + e.getMessage());
        }
        return documento;
    }

    private Equipo getEquipoFromElement(Element elemento) {
        Equipo e = new Equipo();
        String nombre = elemento.getElementsByTagName("nombreEquipo").item(0).getTextContent();
        int puntos = Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent());
        String id = elemento.getAttribute("identificadorEquipo");

        e.setIdentificadorEquipo(id);
        e.setNombreEquipo(nombre);
        e.setPuntos(puntos);

        return e;
    }

    

   

    public Set<Equipo> leerEquiposDesdeXML(String nombreFichero) {
        Set<Equipo> equipos = new HashSet<>();
        try {
            Document doc = getDocumentFromXML(nombreFichero);
            NodeList listaEquipos = doc.getElementsByTagName("equipo");
            for (int i = 0; i < listaEquipos.getLength(); i++) {
                Element elemento = (Element) listaEquipos.item(i);
                equipos.add(getEquipoFromElement(elemento));
            }
            logger.info("Equipos cargados: " + equipos.size());
        } catch (Exception e) {
            logger.error("Error al leer los equipos: " + e.getMessage());
        }
        return equipos;
    }

    
}
