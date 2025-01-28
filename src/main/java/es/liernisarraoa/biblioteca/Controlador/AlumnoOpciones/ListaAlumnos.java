package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListaAlumnos implements Initializable {
    @FXML
    public Button btnVolver;
    @FXML
    public ListView<Alumno> listaAlumnos;
    @FXML
    public ImageView ivHome;

    private Stage stage;
    private ArrayList<Alumno> arrayListAlumnos;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void volverHome(MouseEvent mouseEvent) {
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

    public void volverPregunta(ActionEvent actionEvent) {
        ((Stage)btnVolver.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipHome = new Tooltip("Volver a la pagina inicial");
        Tooltip tooltipVolverAniadirAlumno = new Tooltip("Volver al formulario");

        // Asignar el Tooltip al botón
        Tooltip.install(btnVolver, tooltipVolverAniadirAlumno);
        Tooltip.install(ivHome, tooltipHome);

        arrayListAlumnos = AlumnoDAO.listaDeAlumnos();
        listaAlumnos.getItems().addAll(arrayListAlumnos);
    }
}
