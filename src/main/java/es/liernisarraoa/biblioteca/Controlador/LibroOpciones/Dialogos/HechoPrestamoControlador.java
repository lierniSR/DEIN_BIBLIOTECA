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

/**
 * Modal para cuando coges prestado un libro
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class HechoPrestamoControlador implements Initializable {
    /**
     * Atributos del archivo dialogoEstadoDevolucion.fxml
     */
    @FXML
    public Label lblTexto;
    @FXML
    public Button btnLista;
    @FXML
    public Button btnOK;

    /**
     * Atributos que se necesita para la clase
     */
    private Stage stage;

    /**
     * Para salir de la ventana modal
     *
     * @param actionEvent
     */
    public void salir(ActionEvent actionEvent) {
        ((Stage)lblTexto.getScene().getWindow()).close();
    }

    /**
     * Para abrir una ventana donde están todos los prestamos.
     *
     * @param actionEvent
     */
    public void abrirListaPrestamo(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/listaPrestamos.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Lista de prestamos");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            ListaPrestamoControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    /**
     * Es la implementacion de la interfaz Initializable.
     * Se ejecuta cada vez que se abre esta ventana.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTexto.setText("El prestamo que has insertado se ha \n" +
                "insertado con éxito.\n" +
                "Puedes ver la lista para verificar que se ha \n" +
                "modificado correctamente. \n" +
                "Selecciona la opción que desees.");
        // Crear un Tooltip
        Tooltip tooltipLista = new Tooltip("Visualiza la lista");
        Tooltip tooltipVolverModificarLibro = new Tooltip("Volver al formulario");

        // Asignar el Tooltip al botón
        Tooltip.install(btnLista, tooltipLista);
        Tooltip.install(btnOK, tooltipVolverModificarLibro);
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
