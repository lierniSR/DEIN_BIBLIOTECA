package es.liernisarraoa.biblioteca.Controlador;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.Informe.InformeControlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para el layout biblioteca.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class BibliotecaControlador implements Initializable {
    /**
     * Atributos del layout biblioteca.fxml
     */
    @FXML
    public ImageView iwInformes;
    @FXML
    private ImageView libros;
    @FXML
    private ImageView alumnos;

    /**
     * Este atributo es para poder cambiar de ventanas
     */
    private Stage stage;

    /**
     * Este MouseEvent es para la imagen de los alumnos.
     * Para ir a la página de gestión de Alumnos.
     * Layout alumnos.fxml y el controlador AlumnoControlador.
     *
     * @param mouseEvent
     */
    public void abrirAlumnos(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Alumno/alumnos.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Gestion de Alumnos");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            AlumnoControlador controlador = fxmlLoader.getController();
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
     * Este MouseEvent es para la imagen de los libros.
     * Para ir a la página de gestión de los Libros.
     * Layout libros.fxml y el controlador LibroControlador.
     *
     * @param mouseEvent
     */
    public void abrirLibros(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/libros.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Gestion de Libros");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Para pasar el parametro stage de controlador a controlador
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
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
        // Crear un Tooltip
        Tooltip tooltipAlumno = new Tooltip("Gestión de Alumnos");
        Tooltip tooltipLibro = new Tooltip("Gestión de Libros");

        // Asignar el Tooltip al botón
        Tooltip.install(alumnos, tooltipAlumno);
        Tooltip.install(libros, tooltipLibro);
    }

    /**
     * Este MouseEvent es para la imagen de informes.
     * Para ir a la página de informes.
     * Layout informe.fxml y el controlador InformeControlador.
     *
     * @param mouseEvent
     */
    public void abrirInformes(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Informes/informe.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("INFORMES");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            InformeControlador controlador = fxmlLoader.getController();
            controlador.setStage(stage);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("FXML");
            alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}