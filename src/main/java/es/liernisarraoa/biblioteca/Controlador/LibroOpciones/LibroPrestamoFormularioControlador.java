package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.Modelo.Libro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LibroPrestamoFormularioControlador implements Initializable {
    @FXML
    public TextField tfIdPrestamo;
    @FXML
    public DatePicker dpFechaPrestamo;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnPrestar;
    @FXML
    public Button btnVolver;
    @FXML
    public ChoiceBox<String> cbAlumno;
    @FXML
    public ChoiceBox<String> cbCodigoLibro;

    private Stage stage;

    public void volverPrestamo(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/prestamoLibro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Prestamo de libros");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroPrestamoControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
        }
    }

    public void prestarLibro(ActionEvent actionEvent) {
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipIDPrestamo = new Tooltip("Campo para insertar el ID del prestamo");
        Tooltip tooltipDNIAlumno = new Tooltip("Campo para seleccionar un DNI");
        Tooltip tooltipCodigoLibro= new Tooltip("Campo para el codigo del libro");
        Tooltip tooltipFechaPrestamo = new Tooltip("Campo para seleccionar la fecha");
        Tooltip tooltipVolverPrestamo = new Tooltip("Volver a la pagina de prestamos");
        Tooltip tooltipVolverHome = new Tooltip("Volver a la pagina principal");
        Tooltip tooltipPrestar = new Tooltip("Coger prestado el libro");

        // Asignar el Tooltip al botón
        Tooltip.install(tfIdPrestamo, tooltipIDPrestamo);
        Tooltip.install(cbAlumno, tooltipDNIAlumno);
        Tooltip.install(cbCodigoLibro, tooltipCodigoLibro);
        Tooltip.install(dpFechaPrestamo, tooltipFechaPrestamo);
        Tooltip.install(btnVolver, tooltipVolverPrestamo);
        Tooltip.install(btnPrestar, tooltipPrestar);
        Tooltip.install(iwHome, tooltipVolverHome);

        cbAlumno.getItems().addAll(AlumnoDAO.listaNombres());
        cbCodigoLibro.getItems().addAll(LibroDAO.listaTitulos());
    }
}
