package es.liernisarraoa.biblioteca.Propiedades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {
    private Properties properties;

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

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

