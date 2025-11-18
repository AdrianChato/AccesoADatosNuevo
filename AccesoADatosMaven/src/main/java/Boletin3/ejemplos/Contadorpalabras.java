package Boletin3.ejemplos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Contadorpalabras {

	public void cuentapalabras (int numero, String ruta) {
		
		PrintWriter out = null;
		try {
			FileWriter ficheroSalida;
			ficheroSalida = new FileWriter(ruta);
			out = new PrintWriter(ficheroSalida);
			out.printf("El numero de veces que aparece es %d %n", numero);
		}
		catch (IOException e) {
			System.out.println("IOException");
		}
		finally {
			if (out!=null)
				out.close();
		}
		}
}

