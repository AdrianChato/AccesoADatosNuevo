package Unidad1.ejemplo.Utiles;

import java.io.FileReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.google.gson.Gson;
import Unidad1.ejemplo.Pokemon;

public class UtilidadesJsonPokemons {
	private static final Logger logger =LogManager.getLogger(UtilidadesJsonPokemons.class);
	public Pokemon leePokemon(String rutaFichero) {
		Pokemon pokemon = null;
		try {
			Gson gson = new Gson();
			FileReader fichero = new FileReader(rutaFichero);
			pokemon = gson.fromJson(fichero, Pokemon.class);
			logger.debug(pokemon);
		} catch (Exception e) {
			logger.warn("El pokemon no existe");
			
		}
		return pokemon;
	}
	
}
