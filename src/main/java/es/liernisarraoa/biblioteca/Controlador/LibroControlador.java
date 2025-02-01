package es.liernisarraoa.biblioteca.Controlador;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.*;
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
 * Controlador para el layout libros.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class LibroControlador implements Initializable {
    /**
     * Atributos del layout libros.fxml
     */
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

    /**
     * Este atributo es para poder cambiar de ventanas
     */
    private Stage stage;

    /**
     * Para pasar el parametro stage de controlador a controlador
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Para volver a la pagina inicial con el layout biblioteca.fxml con el controlador BibliotecaControlador
     *
     * @param actionEvent
     */
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

    /**
     * Este MouseEvent es para la imagen de mas.
     * Para ir al formulario para añadir un Libro.
     * Layout aniadirLibro.fxml y el controlador LibroAniadirControlador.
     *
     * @param mouseEvent
     */
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

    /**
     * Este MouseEvent es para la imagen del lapiz.
     * Para ir al formulario de modificar Libro.
     * Layout modificarLibro.fxml con el controlador LibroModificarControlador.
     *
     * @param mouseEvent
     */
    public void abrirModificarLibro(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/modificarLibro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Modificar libro");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroModificarControlador controlador = fxmlLoader.getController();
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
     * Este MouseEvent es para la imagen de la basura.
     * Para ir a la lista para eliminar Libro.
     * Layout eliminarLibro.fxml con el controlador LibroEliminarControlador.
     *
     * @param mouseEvent
     */
    public void abrirEliminarLibro(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/eliminarLibro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Eliminar libros");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroEliminarControlador controlador = fxmlLoader.getController();
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
     * Este MouseEvent es para la imagen del libro.
     * Para ir a la lista de libros para prestar un Libro.
     * Layout prestamoLibro.fxml con el controlador LibroPrestamoControlador.
     *
     * @param mouseEvent
     */
    public void abrirPrestamo(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/prestamoLibro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Prestamos de libros");
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

    /**
     * Este MouseEvent es para la imagen de la persona devolviendo un libro.
     * Para ir a la lista de préstamos para devolver un Libro.
     * Layout devolverLibro.fxml con el controlador LibroDevolverControlador.
     *
     * @param mouseEvent
     */
    public void abrirDevolucion(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/devolverLibro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Devolver libro");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroDevolverControlador controlador = fxmlLoader.getController();
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
     * Este MouseEvent es para la imagen del archivo con un reloj.
     * Para ir a la lista del historico de préstamos de todos los libros.
     * Layout historicoPrestamos.fxml con el controlador LibroHistoricoPrestamoControlador.
     *
     * @param mouseEvent
     */
    public void abrirHistorico(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/historicoPrestamos.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("HISTORICO");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //Pasar al controlador el Stage
            LibroHistoricoPrestamoControlador controlador = fxmlLoader.getController();
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
        Tooltip tooltipAniadir = new Tooltip("Nuevo libro");
        Tooltip tooltipModificar = new Tooltip("Modificar libro");
        Tooltip tooltipEliminar = new Tooltip("Dar de baja/alta libro");
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
