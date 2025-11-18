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

import BoletinXML.Modelo.Pelicula;
import BoletinXML.Modelo.Producto;
import repaso.controlator.PruebaLog;

public class XMLDomProductos {

	private static final Logger logger = LogManager.getLogger(XMLDomProductos.class);
	private static final String rutaResources = "src\\main\\resources\\";


	private  Producto getProductoFromElement(Element elemento) //unico que cambia
	{
			Producto e = new Producto();
			Double precio = (double) Integer.parseInt(elemento.getElementsByTagName("Precio").item(0).getTextContent());
			int stock = Integer.parseInt(elemento.getElementsByTagName("Stock").item(0).getTextContent());
			String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
			String id = elemento.getAttribute("id"); // La etiqueta empleado tiene el atributo identificador
			boolean enVenta = Boolean.parseBoolean(elemento.getAttribute("enVenta"));
			
			e.setEnVenta(enVenta);
			e.setId(id);
			e.setStock(stock);
			e.setPrecio(precio);
			e.setNombre(nombre);
			
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
	
	
	public List<Producto> leerPeliculaDesdeXML(String rutaFichero) throws Exception {
		List<Producto> Producto = new ArrayList<Producto>();
		// 1. Calcula el dom
		Document doc = getDocumentFromXML(rutaFichero);
		// 2. Obtener todos los nodos con etiqueta empleados
		NodeList nodosEmpleados = doc.getElementsByTagName("Productos"); //Para varios
 // 3. Recorro la lista de los nodos empleado
		for (int j = 0; j < nodosEmpleados.getLength(); j++) {
			Node modeloNodo = nodosEmpleados.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
				Producto e = this.getProductoFromElement((Element) modeloNodo);
				Producto.add(e);
			}
		}
		return Producto;
	}
}
