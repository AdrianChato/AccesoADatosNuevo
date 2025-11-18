package Unidad1.ejemplo;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileNuevo {

	private static final Logger logger = LogManager.getLogger(FileNuevo.class);
	public static void main(String[] args) {
		
	
	String rutaDirectorio = "C:\\Users\\alumno\\Desktop\\";
	File directorio = new File(rutaDirectorio);
	// Referencio a un fichero dentro del directorio soraya
	/*File fichero = new File(directorio);
	try {
		boolean creado = fichero.mkdir(); // Aquí Sí creo fichero
	} catch (IOException e) {
		// TODO Auto-generated catch block
		logger.error("Error al crear fichero:" + e.getMessage());
	}*/
	File f = new File(rutaDirectorio);
	String [] archivos = f.list();
	File [] archivos2 = f.listFiles();
	System.out.println(archivos);
	System.out.println(archivos2);
	for (String fichero : archivos) {
		logger.info(fichero);
		
	}
	FileNuevo a = new FileNuevo();
	a.getPropiedadesRecursivas(directorio);
	}
	
	public void getPropiedadesRecursivas (File padre) {
		String rutaDirectorio = "C:\\Users\\alumno\\Desktop\\";
		boolean existe = padre.exists();
		File [] listaficheros = padre.listFiles();
		if (existe) {
		for (File f2 : listaficheros) {
			if (f2.isFile()) {
				logger.info(f2.getName());
			} else {
				this.getPropiedadesRecursivas(f2);
			} 
		}
	} else {
		logger.info("No existe");
	}
	}
}
