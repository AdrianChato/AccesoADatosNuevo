package BoletinXML.Utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import BoletinXML.Modelo.Equipo;
import BoletinXML.Modelo.Pelicula;
import repaso.controlator.PruebaLog;

public class XMLDomFormula1 {
	private static final Logger logger = LogManager.getLogger(XMLDomFormula1.class);
	private static final String rutaResources = "src\\main\\resources\\";


	private  Equipo getEquipoFromElement(Element elemento) //unico que cambia
	{
		Equipo e = new Equipo();
			String titulo = elemento.getElementsByTagName("Titulo").item(0).getTextContent();
			int año = Integer.parseInt(elemento.getElementsByTagName("Fecha").item(0).getTextContent());
			String director = elemento.getElementsByTagName("Director").item(0).getTextContent();
			//String id = elemento.getAttribute("identificador"); // La etiqueta empleado tiene el atributo identificador
			
			NodeList actoresNodes = elemento.getElementsByTagName("Actor");
		    List<String> actores = new ArrayList<>();
		    for (int i = 0; i < actoresNodes.getLength(); i++) {
		        Node actorNode = actoresNodes.item(i);
		        if (actorNode.getNodeType() == Node.ELEMENT_NODE) {
		            String actor = actorNode.getTextContent();
		            actores.add(actor);
		        }
		    }

			e.setActores(actores);
			e.setAño(año);
			e.setDirector(director);
			e.setTitulo(titulo);
			
			return e;
		}
	
	/*private  List getActoresFromElement(Element elemento) //unico que cambia
	{
			List<String> actores = new ArrayList<>();
			String actor = elemento.getElementsByTagName("Actor").item(0).getTextContent();
			actores.add(actor);
			return actores;
		}*/
	
	private Document getDocumentFromXML(String nombrefichero) {
		File file = new File(rutaResources + nombrefichero);
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			documento = dBuilder.parse(file);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return documento;
	} 
	
	
	public List<Equipo> leerEquipoDesdeXML(String rutaFichero) throws Exception {
		List<Equipo> equipos = new ArrayList<Equipo>();
		// 1. Calcula el dom
		Document doc = getDocumentFromXML(rutaFichero);
		// 2. Obtener todos los nodos con etiqueta empleados
		NodeList nodosEmpleados = doc.getElementsByTagName("equipos"); //Para varios
 // 3. Recorro la lista de los nodos empleado
		for (int j = 0; j < nodosEmpleados.getLength(); j++) {
			Node modeloNodo = nodosEmpleados.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
				Equipo e = this.getEquipoFromElement((Element) modeloNodo);
				equipos.add(e);
			}
		}
		return equipos;
	}
	
	/*public List<Pelicula> leerActoresDesdeXML(String rutaFichero) throws Exception {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		// 1. Calcula el dom
		Document doc = getDocumentFromXML(rutaFichero);
		// 2. Obtener todos los nodos con etiqueta empleados
		NodeList nodosEmpleados = doc.getElementsByTagName("Actores"); //Para varios
 // 3. Recorro la lista de los nodos empleado
		for (int j = 0; j < nodosEmpleados.getLength(); j++) {
			Node modeloNodo = nodosEmpleados.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
				Pelicula e = this.getPeliculaFromElement((Element) modeloNodo);
				peliculas.add(e);
			}
		}
		return peliculas;
	}*/
}
