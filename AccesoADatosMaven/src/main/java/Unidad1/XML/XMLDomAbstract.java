package Unidad1.XML;

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

import Unidad1.Modelo.Empleado;
import repaso.controlator.PruebaLog;

public class XMLDomAbstract {
	private static final Logger logger = LogManager.getLogger(PruebaLog.class);
	private static final String rutaResources = "src\\main\\resources\\";


	private  Empleado getEmpleadoFromElement(Element elemento) //unico que cambia
	{
			Empleado e = new Empleado();
			String nombre = elemento.getElementsByTagName("nombreApellido").item(0).getTextContent();
			int edad = Integer.parseInt(elemento.getElementsByTagName("edad").item(0).getTextContent());
			String empresa = elemento.getElementsByTagName("empresa").item(0).getTextContent();
			String id = elemento.getAttribute("identificador"); // La etiqueta empleado tiene el atributo identificador
			e.setEdad(edad);
			e.setNombreApellido(nombre);
			e.setIdentificador(id);
			e.setEmpresa(empresa);
			return e;
		}
	
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
	
	public Empleado leerEmpleadoDesdeXML(String rutaFichero) throws Exception {
	       Document doc = getDocumentFromXML(rutaFichero);
	       // Obtener el elemento raíz (el único <empleado>)
	       Element elementoEmpleado = doc.getDocumentElement(); //Para uno
	       // Usar tu método
	       return getEmpleadoFromElement(elementoEmpleado);
	   }
	
	public List<Empleado> leerEmpleadosDesdeXML(String rutaFichero) throws Exception {
		List<Empleado> empleados = new ArrayList<Empleado>();
		// 1. Calcula el dom
		Document doc = getDocumentFromXML(rutaFichero);
		// 2. Obtener todos los nodos con etiqueta empleados
		NodeList nodosEmpleados = doc.getElementsByTagName("empleado"); //Para varios
 // 3. Recorro la lista de los nodos empleado
		for (int j = 0; j < nodosEmpleados.getLength(); j++) {
			Node modeloNodo = nodosEmpleados.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
				Empleado e = this.getEmpleadoFromElement((Element) modeloNodo);
				empleados.add(e);
			}
		}
		return empleados;
	}


	
}

