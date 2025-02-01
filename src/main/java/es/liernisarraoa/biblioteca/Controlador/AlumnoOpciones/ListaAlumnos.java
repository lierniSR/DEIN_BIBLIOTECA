package es.liernisarraoa.biblioteca.Controlador.AlumnoOpciones;

import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlador para el layout listaAlumnos.fxml
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class ListaAlumnos implements Initializable {
    /**
     * Atributos del layout biblioteca.fxml
     */
    @FXML
    public Button btnVolver;
    @FXML
    public ListView<Alumno> listaAlumnos;

    /**
     * Atributos que necesitamos de la clase.
     */
    private Stage stage;
    private ArrayList<Alumno> arrayListAlumnos;

    /**
     * Este atributo es para poder cambiar de ventanas
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Para cerrar la ventana.
     *
     * @param actionEvent
     */
    public void volverPregunta(ActionEvent actionEvent) {
        ((Stage)btnVolver.getScene().getWindow()).close();
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
        Tooltip tooltipVolverAniadirAlumno = new Tooltip("Volver al formulario");

        // Asignar el Tooltip al botón
        Tooltip.install(btnVolver, tooltipVolverAniadirAlumno);

        arrayListAlumnos = AlumnoDAO.listaDeAlumnos();
        listaAlumnos.getItems().addAll(arrayListAlumnos);
    }
}
