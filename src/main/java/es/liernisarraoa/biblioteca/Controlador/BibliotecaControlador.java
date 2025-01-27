package es.liernisarraoa.biblioteca.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BibliotecaControlador {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}