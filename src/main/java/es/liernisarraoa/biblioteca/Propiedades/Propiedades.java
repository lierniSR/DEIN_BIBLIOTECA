package es.liernisarraoa.biblioteca.Propiedades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Para las propiedades del proyecto
 *
 * @author Lierni Sarraoa Joaquin
 * @version 1.0
 */
public class Propiedades {
    /**
     * Atributos que necesitamos
     */
    private Properties properties;

    /**
     * Constructor
     */
    public Propiedades() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se encontró config.properties");
                return;
            }
            // Cargar el archivo .properties
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Para encontrar el valor
     *
     * @param key
     * @return valor
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

