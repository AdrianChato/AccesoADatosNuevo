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
import unidad1.simulacro1.modelo.Piloto;

public class XMLDomPiloto {

    private static final Logger logger = LogManager.getLogger(XMLDomPiloto.class);
    private static final String RUTA_RECURSOS = "src\\main\\resources\\";

    private Document getDocumentFromXML(String nombreFichero) {
        File file = new File(RUTA_RECURSOS + nombreFichero);
        Document documento = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            documento = dBuilder.parse(file);
            documento.getDocumentElement().normalize();
           // logger.info("XML cargado correctamente: {}", nombreFichero);
        } catch (Exception e) {
            logger.error("Error al cargar el XML de pilotos: {}", e.getMessage());
        }
        return documento;
    }

    private Piloto getPilotoFromElement(Element elemento) {
        Piloto p = new Piloto();
        try {
            p.setIdentificadorPiloto(Integer.parseInt(elemento.getAttribute("identificadorPiloto")));
            p.setNombrePiloto(elemento.getElementsByTagName("nombrePiloto").item(0).getTextContent());
            p.setPuntos(Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent()));
            p.setIdentificadorEquipo(Integer.parseInt(elemento.getElementsByTagName("identificadorEquipo").item(0).getTextContent()));
            p.setPais(elemento.getElementsByTagName("pais").item(0).getTextContent());
        } catch (Exception ex) {
            logger.error("Error al procesar piloto: {}", ex.getMessage());
        }
        return p;
    }

    public Set<Piloto> leerPilotosDesdeXML(String rutaFichero) {
        Set<Piloto> pilotos = new HashSet<>();
        Document doc = getDocumentFromXML(rutaFichero);
        if (doc == null) {
            logger.error("No se pudo leer el documento XML: {}", rutaFichero);
            return pilotos;
        }
        NodeList listaPilotos = doc.getElementsByTagName("piloto");
        for (int i = 0; i < listaPilotos.getLength(); i++) {
            Element elementoPiloto = (Element) listaPilotos.item(i);
            Piloto p = getPilotoFromElement(elementoPiloto);
            pilotos.add(p);
           // logger.debug("Piloto leído: {} - ID: {}", p.getNombrePiloto(), p.getIdentificadorPiloto());
        }
        logger.info("Pilotos cargados correctamente: {}", pilotos.size());
        return pilotos;
    }
}
