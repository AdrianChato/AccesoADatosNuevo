package unidad1.simulacro1.utilidades;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import unidad1.simulacro1.modelo.Piloto;

public class UtilidadesJsonPilotos {
    private static final Logger logger = LogManager.getLogger(UtilidadesJsonPilotos.class);

    public void escribePilotosJson(Set<Piloto> pilotos, String ruta) {
        try (FileWriter fw = new FileWriter(ruta)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(pilotos, fw);
            logger.info("Archivo JSON generado correctamente: {}", ruta);
        } catch (IOException e) {
            logger.error("Error al escribir el archivo JSON: {}", e.getMessage());
        }
    }
}
