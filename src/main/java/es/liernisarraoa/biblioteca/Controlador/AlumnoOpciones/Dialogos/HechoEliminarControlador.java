package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.AlumnoControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.ListaAlumnos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HechoEliminarControlador implements Initializable {
    @FXML
    public Label lblTexto;
    @FXML
    public Button btnBien;

    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTexto.setText("El alumno que has seleccionado\n" +
                " se ha eliminado con éxito.\n");
        // Crear un Tooltip
        Tooltip tooltipBien = new Tooltip("Volver a la lista");

        // Asignar el Tooltip al botón
        Tooltip.install(btnBien, tooltipBien);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void volverEliminar(ActionEvent actionEvent) {
        ((Stage)lblTexto.getScene().getWindow()).close();
    }
}
