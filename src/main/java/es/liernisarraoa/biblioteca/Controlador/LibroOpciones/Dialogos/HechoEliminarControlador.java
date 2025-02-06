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

/**
 * Modal para cuando eliminas un libro
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class HechoEliminarControlador implements Initializable {
    /**
     * Atributos del archivo dialogoEstadoDevolucion.fxml
     */
    @FXML
    public Label lblTexto;
    @FXML
    public Button btnBien;

    /**
     * Atributos que se necesita para la clase
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
        lblTexto.setText("El libro que has seleccionado\n" +
                " se ha dado de baja o de alta con éxito.\n");
        // Crear un Tooltip
        Tooltip tooltipBien = new Tooltip("Volver a la lista");

        // Asignar el Tooltip al botón
        Tooltip.install(btnBien, tooltipBien);
    }

    /**
     * Para las ventanas.
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Volver a la ventana de la lista para eliminar el libro.
     *
     * @param actionEvent
     */
    public void volverEliminar(ActionEvent actionEvent) {
        ((Stage)lblTexto.getScene().getWindow()).close();
    }
}
