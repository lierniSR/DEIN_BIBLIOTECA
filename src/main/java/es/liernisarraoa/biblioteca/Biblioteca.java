package es.liernisarraoa.biblioteca;

import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal del proyecto
 *
 * @author Lierni Sarraoa Joaquin
 * @version 1.0
 */
public class Biblioteca extends Application {
    /**
     * Atributos para la clase
     */
    private Stage stage;

    /**
     * Es por la herencia de Application, cuando se ejecuta esto se lanza una ventana
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("biblioteca.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.setTitle("Biblioteca");
        this.stage.setScene(scene);
        this.stage.show();

        //Pasar al controlador el Stage
        BibliotecaControlador controlador = fxmlLoader.getController();
        controlador.setStage(this.stage);
    }

    /**
     * Es la main para que se pueda ejecutar la aplicacion.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Para las ventanas.
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}