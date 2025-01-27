package es.liernisarraoa.biblioteca;

import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Biblioteca extends Application {
    private Stage stage;

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

    public static void main(String[] args) {
        launch();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}