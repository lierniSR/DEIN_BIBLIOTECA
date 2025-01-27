package es.liernisarraoa.biblioteca.Controlador;

import es.liernisarraoa.biblioteca.Biblioteca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoControlador implements Initializable {
    @FXML
    private ImageView btnAniadir;
    @FXML
    private ImageView btnModificar;
    @FXML
    private ImageView btnEliminar;
    @FXML
    private Button volverPrincipal;

    private Stage stage;

    public void abrirFormularioAniadir(MouseEvent mouseEvent) {
    }

    public void abrirFormularioModificar(MouseEvent mouseEvent) {
    }

    public void abrirListaEliminar(MouseEvent mouseEvent) {
    }

    public void volverBiblioteca(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("biblioteca.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Gestion de Alumnos");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            BibliotecaControlador controlador = fxmlLoader.getController();
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
        // Crear un Tooltip
        Tooltip tooltipAniadir = new Tooltip("Nuevo alumno");
        Tooltip tooltipModificar = new Tooltip("Modificar alumno");
        Tooltip tooltipEliminar = new Tooltip("Eliminar alumno");

        // Asignar el Tooltip al botón
        Tooltip.install(btnAniadir, tooltipAniadir);
        Tooltip.install(btnModificar, tooltipModificar);
        Tooltip.install(btnEliminar, tooltipEliminar);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
