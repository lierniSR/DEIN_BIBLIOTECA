package es.liernisarraoa.biblioteca.Controlador;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.AlumnoEliminarControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.LibroAniadirControlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LibroControlador implements Initializable {
    @FXML
    public Button volverPrincipal;
    @FXML
    private ImageView btnAniadir;
    @FXML
    private ImageView btnModificar;
    @FXML
    private ImageView btnEliminar;
    @FXML
    private ImageView btnPrestamo;
    @FXML
    private ImageView btnDevolucion;
    @FXML
    private ImageView btnHistorico;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void volverPrincipal(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("biblioteca.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Biblioteca");
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

    public void abrirAniadirLibro(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/aniadirLibro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Añadir libro");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroAniadirControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void abrirModificarLibro(ContextMenuEvent contextMenuEvent) {
    }

    public void abrirEliminarLibro(ContextMenuEvent contextMenuEvent) {
    }

    public void abrirPrestamo(MouseEvent mouseEvent) {
    }

    public void abrirDevolucion(MouseEvent mouseEvent) {
    }

    public void abrirHistorico(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipAniadir = new Tooltip("Nuevo alumno");
        Tooltip tooltipModificar = new Tooltip("Modificar alumno");
        Tooltip tooltipEliminar = new Tooltip("Eliminar alumno");
        Tooltip tooltipVolver = new Tooltip("Volver a la página inicial");
        Tooltip tooltipPrestamo = new Tooltip("Pedir un libro");
        Tooltip tooltipDevolucion = new Tooltip("Devolver libro");
        Tooltip tooltipHistorico = new Tooltip("Historial de prestamos");

        // Asignar el Tooltip al botón
        Tooltip.install(btnAniadir, tooltipAniadir);
        Tooltip.install(btnModificar, tooltipModificar);
        Tooltip.install(btnEliminar, tooltipEliminar);
        Tooltip.install(btnPrestamo, tooltipPrestamo);
        Tooltip.install(btnDevolucion, tooltipDevolucion);
        Tooltip.install(btnHistorico, tooltipHistorico);
        Tooltip.install(volverPrincipal, tooltipVolver);
    }
}
