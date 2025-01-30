package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.Modelo.Libro;
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

public class ListaLibrosControlador implements Initializable {
    @FXML
    public Button btnVolver;
    @FXML
    public ListView<Libro> listaLibros;

    private Stage stage;
    private ArrayList<Libro> arrayListLibros;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void volverPregunta(ActionEvent actionEvent) {
        ((Stage)btnVolver.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipVolverAniadirAlumno = new Tooltip("Volver al formulario");

        // Asignar el Tooltip al botón
        Tooltip.install(btnVolver, tooltipVolverAniadirAlumno);

        arrayListLibros = LibroDAO.listaDeLibros();
        listaLibros.getItems().addAll(arrayListLibros);
    }
}
