package Unidad1.ejemploStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Unidad1.ejemplo.FileNuevo;

public class Gestionaficheros {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rutaYNombre = "C:\\Users\\alumno\\Desktop\\fichero.txt";
		Gestionaficheros g = new Gestionaficheros();
		try {
			g.muestraContenidoFich(rutaYNombre);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				

	}
	private static final Logger logger = LogManager.getLogger(FileNuevo.class);
	void muestraContenidoFich(String rutaYNombre) throws FileNotFoundException {
		rutaYNombre = "C:\\Users\\alumno\\Desktop\\fichero.txt";
		Scanner in = null;
		try {
			// abre el fichero
			FileReader fichero = new FileReader(rutaYNombre);
			//Se crea el flujo
			in = new Scanner(fichero);
			// lee el fichero
			in.useDelimiter(",");
			while (in.hasNext()) { //Lectura palabra a palabra
				// Aquí se hará la lectura in.next()
				logger.info(in.next());
				
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/*package tema1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestionaPersona {
	private static final Logger logger = LogManager.getLogger(Ejercicio1.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Notas> listaNotas = new ArrayList<Notas>();
		GestionaPersona p = new GestionaPersona();
		try {
			p.cargarLista(
					"C:\\Users\\alumno\\Desktop\\segundoDAM\\JAVA\\accesoADatos\\src\\main\\java\\tema1\\fichero1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cargarLista(String rutaYNombre) throws FileNotFoundException {
		Scanner in = null;
		try {
			// abre el fichero
			FileReader fichero = new FileReader(rutaYNombre);
			// Se crea el flujo
			in = new Scanner(fichero);
			// lee el fichero
			while (in.hasNextLine()) { // Lectura palabra a palabra
				// Aquí se hará la lectura in.next()
				String linea = in.nextLine();
				Persona p = cargaPersona(linea);
				logger.info(p.toString());
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public Persona cargaPersona(String linea) {
		String[] cadenas = linea.split(" ");
		List<Notas> lista = new ArrayList<Notas>();
		for (int i = 1; i < cadenas.length; i++) {
			Notas n = new Notas(Double.parseDouble(cadenas[i]));
			lista.add(n);
		}
		Persona p = new Persona(cadenas[0], lista);
		return p;

	}

}*/

}
