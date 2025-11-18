package Simulacion.Utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Simulacion.Modelos.CentroLogistico;
import Simulacion.Modelos.TipoPersonal;
import Simulacion.Modelos.Trabajador;


public class XMLDomBancoDeAlimentos {

    private static final Logger logger = LogManager.getLogger(XMLDomBancoDeAlimentos.class);
    private static final String rutaResources = "src/main/resources/";

    
    private Document getDocumentFromXML(String nombrefichero) {
        File file = new File(rutaResources + nombrefichero);
        Document documento = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            documento = dBuilder.parse(file);
            documento.getDocumentElement().normalize(); // Limpieza del XML
        } catch (Exception e) {
            logger.error("Error al cargar XML: " + e.getMessage());
        }
        return documento;
    }

    
    private CentroLogistico getCentroFromElement(Element elemento) {
        CentroLogistico c = new CentroLogistico();

        try {
            String id = elemento.getElementsByTagName("ID").item(0).getTextContent().trim();
            String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
            String ciudad = elemento.getElementsByTagName("Ciudad").item(0).getTextContent().trim();
            int comedores = Integer.parseInt(
                    elemento.getElementsByTagName("ComedoresAbastecidos").item(0).getTextContent().trim());

            c.setId(id);
            c.setNombre(nombre);
            c.setCiudad(ciudad);
            c.setComedores(comedores);

        } catch (Exception e) {
            logger.error("Error al procesar un centro logístico: {}", e.getMessage());
        }

        return c;
    }

    /**
     * Convierte un elemento XML <Trabajador> en un objeto Trabajador.
     */
    private Trabajador getTrabajadorFromElement(Element elemento, String idCentro) {
        Trabajador t = new Trabajador();

        try {
            String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
            String dni = elemento.getElementsByTagName("DNI").item(0).getTextContent().trim();
            String fecha = elemento.getElementsByTagName("FechaNacimiento").item(0).getTextContent().trim();
            String tipo = elemento.getElementsByTagName("Tipo").item(0).getTextContent().trim().toUpperCase();

            TipoPersonal tipoPersonal = tipo.equals("ASALARIADO")
                    ? TipoPersonal.ASALARIADO
                    : TipoPersonal.VOLUNTARIO;

            t.setNombre(nombre);
            t.setDni(dni);
            t.setFechaNacimiento(fecha);
            t.setTipo(tipoPersonal);
            t.setIdCentro(idCentro); // Relacionamos con el centro actual

        } catch (Exception e) {
            logger.error("Error al procesar un trabajador: {}", e.getMessage());
        }

        return t;
    }

    /**
     * Lee todos los centros logísticos y sus trabajadores desde el XML.
     */
    public Set<CentroLogistico> leerCentrosDesdeXML(String nombreFichero) {

        Set<CentroLogistico> centros = new HashSet<>();

        try {
            Document doc = getDocumentFromXML(nombreFichero);
            if (doc == null) {
                logger.error("El documento XML no pudo ser cargado. Revisa la ruta o el formato del archivo.");
                return centros;
            }

            NodeList listaCentros = doc.getElementsByTagName("CentroLogistico");

            for (int i = 0; i < listaCentros.getLength(); i++) {
                Node nodoCentro = listaCentros.item(i);

                if (nodoCentro.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoCentro = (Element) nodoCentro;
                    CentroLogistico centro = getCentroFromElement(elementoCentro);

                    // Leer los trabajadores asociados
                    NodeList listaTrabajadores = elementoCentro.getElementsByTagName("Trabajador");
                    List<Trabajador> trabajadores = new ArrayList<>();

                    for (int j = 0; j < listaTrabajadores.getLength(); j++) {
                        Node nodoTrabajador = listaTrabajadores.item(j);
                        if (nodoTrabajador.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoTrabajador = (Element) nodoTrabajador;
                            Trabajador trabajador = getTrabajadorFromElement(elementoTrabajador, centro.getId());
                            trabajadores.add(trabajador);
                        }
                    }

                    centro.setPersonal(trabajadores);
                    centros.add(centro);
                }
            }

            logger.info("Centros cargados desde XML: {}", centros.size());

        } catch (Exception e) {
            logger.error("Error al leer los centros desde el XML '{}': {}", nombreFichero, e.getMessage());
        }

        return centros;
    }
}