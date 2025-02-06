package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.AlumnoControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos.HechoEliminarControlador;
import es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones.Dialogos.HechoModificarControlador;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para el layout eliminarAlumno.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class AlumnoEliminarControlador implements Initializable {
    /**
     * Atributos del layout eliminarAlumno.fxml
     */
    @FXML
    public ListView<Alumno> seleccionAlumno;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnVolver;

    /**
     * Atributos que necesitamos de la clase, para abrir ventanas normales y modales
     */
    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;

    /**
     * Este ActionEvent es para el boton de volver.
     * Para volver a la gestion de Alumnos.
     * Layout alumnos.fxml y el controlador AlumnoControlador.
     *
     * @param actionEvent
     */
    public void volverAlumno(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Alumno/alumnos.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Gestion de alumnos");
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
     * Este ActionEvent es para el boton de eliminar.
     * Cuando se clica comprueba que se ha seleccionado un registro de la lista,
     * después hace una consulta a la base de datos desde la clase AlumnoDAO,
     * si la consulta sale true abrira una ventana modal con el layout dialogoBienEliminar.fxml
     * y el controlador HechoEliminarControlador.
     * Si da algún error saldrá una alerta de error.
     *
     * @param actionEvent
     */
    public void eliminarAlumno(ActionEvent actionEvent) {
        Alumno alumno = seleccionAlumno.getSelectionModel().getSelectedItem();
        if(alumno != null){
            if(AlumnoDAO.eliminarAlumno(alumno)){
                //Esto si el controlador necesita hacer algo en la ventana principal
                // Cargar el FXML de la ventana modal
                FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Alumno/dialogoBienEliminar.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                    modalStage = new Stage();
                    modalScene = new Scene(root);

                    modalStage.setScene(modalScene);
                    modalStage.initModality(Modality.APPLICATION_MODAL);
                    modalStage.setTitle("SE HA ELIMINADO");
                    modalStage.setResizable(false);
                    //Pasar al controlador el Stage
                    HechoEliminarControlador controlador = loader.getController();
                    controlador.setStage(modalStage);
                    modalStage.showAndWait();

                    // Actualizar los elementos del ListView
                    seleccionAlumno.getItems().setAll(AlumnoDAO.listaDeAlumnos());
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("FXML");
                    alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                    alert.showAndWait();
                    throw new RuntimeException(e);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("SELECCION");
            alert.setContentText("No has seleccionado ningún alumno para eliminar.");
            alert.showAndWait();
        }

    }

    /**
     * Este MouseEvent es para la imagen de la casa.
     * Para volver a la página principal.
     * Layout biblioteca.fxml y el controlador BibliotecaControlador.
     *
     * @param mouseEvent
     */
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

    /**
     * Este atributo es para poder cambiar de ventanas
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
        Tooltip tooltipLista = new Tooltip("Lista de alumnos");
        Tooltip tooltipHome = new Tooltip("Volver a la pagina inicial");
        Tooltip tooltipVolverAlumno = new Tooltip("Volver a la pagina de alumnos");
        Tooltip tooltipEliminar = new Tooltip("Eliminar alumno seleccionado");

        // Asignar el Tooltip al botón
        Tooltip.install(btnEliminar, tooltipEliminar);
        Tooltip.install(btnVolver, tooltipVolverAlumno);
        Tooltip.install(iwHome, tooltipHome);
        Tooltip.install(seleccionAlumno, tooltipLista);

        seleccionAlumno.getItems().addAll(AlumnoDAO.listaDeAlumnos());
    }
}
