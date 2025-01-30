package es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.ListaPrestamoControlador;
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

public class HechoDevolverControlador implements Initializable {
    @FXML
    public Label lblTexto;
    @FXML
    public Button btnOK;

    private Stage stage;

    public void salir(ActionEvent actionEvent) {
        ((Stage)lblTexto.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTexto.setText("El libro que has devuelto se ha \n" +
                "devuelto con éxito.\n" +
                "Se han cambiado los siguientes datos: \n" +
                "El prestamo se ha eliminado. \n" +
                "Se ha insertado un registro en el historico.\n" +
                "Se ha actualizado con éxito el estado del \n" +
                "libro.");

        Tooltip tooltipVolverModificarLibro = new Tooltip("Volver a la lista de prestamos");

        Tooltip.install(btnOK, tooltipVolverModificarLibro);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
