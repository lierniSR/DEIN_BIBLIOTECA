package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos;

import es.liernisarraoa.biblioteca.Biblioteca;
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

public class HechoModificarControlador implements Initializable {
    @FXML
    public Label lblTexto;
    @FXML
    public Button btnLista;
    @FXML
    public Button btnOK;

    private Stage stage;

    public void salir(ActionEvent actionEvent) {
        ((Stage)lblTexto.getScene().getWindow()).close();
    }

    public void abrirListaAlumnos(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Alumno/listaAlumnos.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Lista de alumnos");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            ListaAlumnos controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTexto.setText("El alumno que has insertado se ha \n" +
                "actualizado con éxito.\n" +
                "Puedes ver la lista para verificar que se ha \n" +
                "modificado correctamente o puedes mirarlo \n" +
                "en la ventana anterior. \n" +
                "modificando y después verificar la lista.\n" +
                "Selecciona la opción que desees.");
        // Crear un Tooltip
        Tooltip tooltipLista = new Tooltip("Visualiza la lista");
        Tooltip tooltipVolverAniadirAlumno = new Tooltip("Volver al formulario");

        // Asignar el Tooltip al botón
        Tooltip.install(btnLista, tooltipLista);
        Tooltip.install(btnOK, tooltipVolverAniadirAlumno);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
