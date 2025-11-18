package Unidad1.ejemplo.Utiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Unidad1.ejemplo.Pokemon;

public class UtilidadesPokemonCsv {

	private static final Logger logger = LogManager.getLogger(UtilidadesPokemonCsv.class);

	public static void escribeCsv(Pokemon pokemon, String rutaCsv) {
		PrintWriter out = null;
		try (FileWriter writer = new FileWriter(rutaCsv)) {
//Locale.US para que ponga . en vez de ,
			writer.write(String.format(Locale.US, "%d,%s,\"%s\",%.1f,%.1f,\"%s\",%s\n", pokemon.getId(),
					pokemon.getNombre(), pokemon.getTipo(), pokemon.getAltura_m(), pokemon.getPeso_kg(),
					pokemon.getHabilidadPrincipal(), pokemon.getEvoluciona_a()));

			logger.info("CSV generado correctamente en: " + rutaCsv);
		} catch (IOException e) {
			logger.error("Error al escribir el CSV: " + e.getMessage());
		}
	}

	public Pokemon[] leeCsv(String rutacsvleer) {
	    List<Pokemon> lista = new ArrayList<>();
	    Scanner sc = null;

	    try {
	        FileReader fichero = new FileReader(rutacsvleer);
	        sc = new Scanner(fichero);
	        while (sc.hasNextLine()) {
	            String linea = sc.nextLine();
	            Pokemon p = metodoAdrian(linea);
	            lista.add(p);
	        }
	    } catch (FileNotFoundException e) {
	        System.err.println("Error al leer el CSV: " + e.getMessage());
	    }
	    return lista.toArray(new Pokemon[0]);
	}

	public Pokemon metodoAdrian(String linea) {
	    String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

	    int id = Integer.parseInt(campos[0]);
	    String nombre = campos[1];
	    String tipo = campos[2].replace("\"", "");
	    double altura = Double.parseDouble(campos[3]);
	    double peso = Double.parseDouble(campos[4]);
	    String habilidad = campos[5].replace("\"", "");
	    String evoluciona = campos[6];

	    return new Pokemon(id, nombre, tipo, altura, peso, habilidad, evoluciona);
	}
	public static void main(String[] args) {
		UtilidadesPokemonCsv a = new UtilidadesPokemonCsv();
		Pokemon[] pokemons = a.leeCsv("C:\\Users\\alumno\\Desktop\\Spring\\AccesoADatosMaven\\src\\main\\resources\\pokemon2.csv");

		for (Pokemon p : pokemons) {
		    System.out.println(p);
		}
		
;
	}

}
