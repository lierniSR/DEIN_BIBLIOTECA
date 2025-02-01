package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para el layout dialogoBienEliminar.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class HechoEliminarControlador implements Initializable {
    /**
     * Atributos del layout biblioteca.fxml
     */
    @FXML
    public Label lblTexto;
    @FXML
    public Button btnBien;

    /**
     * Atributos que necesitamos de la clase, para abrir ventanas normales y modales
     */
    private Stage stage;

    /**
     * Es la implementacion de la interfaz Initializable.
     * Se ejecuta cada vez que se abre esta ventana.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTexto.setText("El alumno que has seleccionado\n" +
                " se ha eliminado con éxito.\n");
        // Crear un Tooltip
        Tooltip tooltipBien = new Tooltip("Volver a la lista");

        // Asignar el Tooltip al botón
        Tooltip.install(btnBien, tooltipBien);
    }

    /**
     * Este atributo es para poder cambiar de ventanas
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Para salir de la ventana modal.
     *
     * @param actionEvent
     */
    public void volverEliminar(ActionEvent actionEvent) {
        ((Stage)lblTexto.getScene().getWindow()).close();
    }
}
