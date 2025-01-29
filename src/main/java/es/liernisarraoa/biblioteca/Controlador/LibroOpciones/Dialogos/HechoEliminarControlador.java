package es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

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
        lblTexto.setText("El libro que has seleccionado\n" +
                " se ha dado de baja o de alta con éxito.\n");
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
