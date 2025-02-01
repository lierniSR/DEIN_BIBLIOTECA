package es.liernisarraoa.biblioteca.Controlador;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.AlumnoAniadirControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.AlumnoEliminarControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.AlumnoModificarControlador;
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

/**
 * Controlador para el layout alumnos.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class AlumnoControlador implements Initializable {
    /**
     * Atributos del layout biblioteca.fxml
     */
    @FXML
    private ImageView btnAniadir;
    @FXML
    private ImageView btnModificar;
    @FXML
    private ImageView btnEliminar;
    @FXML
    private Button volverPrincipal;

    /**
     * Este atributo es para poder cambiar de ventanas
     */
    private Stage stage;

    /**
     * Este MouseEvent es para la imagen de más.
     * Para ir al formulario para añadir un Alumno.
     * Layout aniadirAlumno.fxml y el controlador AlumnoAniadirControlador.
     *
     * @param mouseEvent
     */
    public void abrirFormularioAniadir(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Alumno/aniadirAlumno.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Nuevo alumno");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            AlumnoAniadirControlador controlador = fxmlLoader.getController();
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
     * Este MouseEvent es para la imagen del lapiz.
     * Para ir al formulario para modificar un Alumno.
     * Layout modificarAlumno.fxml y el controlador AlumnoModificarControlador.
     *
     * @param mouseEvent
     */
    public void abrirFormularioModificar(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Alumno/modificarAlumno.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Modificar alumno");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            AlumnoModificarControlador controlador = fxmlLoader.getController();
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
     * Este MouseEvent es para la imagen de la basura.
     * Para ir al formulario para eliminar un Alumno.
     * Layout eliminarAlumno.fxml y el controlador AlumnoEliminarControlador.
     *
     * @param mouseEvent
     */
    public void abrirListaEliminar(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Alumno/eliminarAlumno.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Eliminar alumno");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            AlumnoEliminarControlador controlador = fxmlLoader.getController();
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
     * Este ActionEvent es para el boton de volver.
     * Para volver a la página inicial.
     * Layout biblioteca.fxml y el controlador BibliotecaControlador.
     *
     * @param actionEvent
     */
    public void volverBiblioteca(ActionEvent actionEvent) {
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
        Tooltip tooltipAniadir = new Tooltip("Nuevo alumno");
        Tooltip tooltipModificar = new Tooltip("Modificar alumno");
        Tooltip tooltipEliminar = new Tooltip("Eliminar alumno");
        Tooltip tooltipVolver = new Tooltip("Volver a la página inicial");

        // Asignar el Tooltip al botón
        Tooltip.install(btnAniadir, tooltipAniadir);
        Tooltip.install(btnModificar, tooltipModificar);
        Tooltip.install(btnEliminar, tooltipEliminar);
        Tooltip.install(volverPrincipal, tooltipVolver);
    }

    /**
     * Para pasar el parametro stage de controlador a controlador
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
