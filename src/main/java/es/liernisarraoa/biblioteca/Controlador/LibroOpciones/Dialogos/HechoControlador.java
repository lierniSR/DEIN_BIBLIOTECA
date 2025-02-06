package es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.ListaLibrosControlador;
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
 * Modal para cuando insertas un libro
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class HechoControlador implements Initializable {
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
     * Es la implementacion de la interfaz Initializable.
     * Se ejecuta cada vez que se abre esta ventana.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTexto.setText("El libro que has insertado se ha \n" +
                "guardado con éxito.\n" +
                "Puedes ver la lista para verificar que se ha \n" +
                "añadido correctamente o puedes seguir \n" +
                "añadiendo y después verificar la lista.\n" +
                "Selecciona la opción que desees.");
        // Crear un Tooltip
        Tooltip tooltipLista = new Tooltip("Visualiza la lista");
        Tooltip tooltipVolverAniadirLibro = new Tooltip("Volver al formulario");

        // Asignar el Tooltip al botón
        Tooltip.install(btnLista, tooltipLista);
        Tooltip.install(btnOK, tooltipVolverAniadirLibro);
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
     * Para abrir la ventana donde aparecen todos los libros.
     *
     * @param actionEvent
     */
    public void abrirListaLibros(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/listaLibros.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Lista de libros");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            ListaLibrosControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }
}
