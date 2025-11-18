package Unidad1.Controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Unidad1.Modelo.Empleado;
import Unidad1.XML.XMLDomAbstract;
import Unidad1.ejemplo.Utiles.UtilidadesJsonPokemons;

public class ControlaXML {
	
	private static final Logger logger =LogManager.getLogger(ControlaXML.class);

	public static void main(String[] args) {

		XMLDomAbstract xmlEmpleado = new XMLDomAbstract(); 
		try {
			Empleado e = xmlEmpleado.leerEmpleadoDesdeXML("empleado.xml");
			logger.debug(e);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
