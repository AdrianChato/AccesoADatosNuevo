package Unidad1.ejemplo.Utiles;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import Unidad1.ejemplo.Empleado;

public class UtilidadesJsonEmpleados {


		public void leeEmpleados(String rutaFichero) {
			try {
			  Gson gson = new Gson();
			  FileReader fichero = new FileReader(rutaFichero);
			  // Leer el archivo JSON y convertirlo a un objeto Empleado
			  Empleado[] empleadosArray = gson.fromJson(fichero,Empleado[].class);
				List<Empleado> empleados = Arrays.asList(empleadosArray);
				System.out.println(empleados);
			} catch (Exception e) {
				//logger.debug("Error al leer empleados"+e.getMessage());
			}	}
}
