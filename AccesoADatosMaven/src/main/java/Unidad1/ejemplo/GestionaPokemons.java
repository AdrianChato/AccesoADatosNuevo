package Unidad1.ejemplo;

import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import Unidad1.ejemplo.Utiles.UtilidadesJsonPokemons;
import Unidad1.ejemplo.Utiles.UtilidadesPokemonCsv;

public class GestionaPokemons {
	private static final Logger logger = LogManager.getLogger(GestionaPokemons.class);

    public static void main(String[] args) {
        String rutaJson = "C:\\Users\\alumno\\Desktop\\Spring\\AccesoADatosMaven\\src\\main\\resources\\pokemon.json";
        String rutaCsv = "C:\\Users\\alumno\\Desktop\\Spring\\AccesoADatosMaven\\src\\main\\resources\\pokemon.csv";

        UtilidadesJsonPokemons utilJson = new UtilidadesJsonPokemons();

        try {
            Gson gson = new Gson();
            FileReader fichero = new FileReader(rutaJson);
            Pokemon pokemon = gson.fromJson(fichero, Pokemon.class);
            logger.info("Pokemon leído: " + pokemon);

            UtilidadesPokemonCsv.escribeCsv(pokemon, rutaCsv);
        } catch (Exception e) {
            logger.error("Error en la gestión de Pokémons: " + e.getMessage());
        }
    }


}
