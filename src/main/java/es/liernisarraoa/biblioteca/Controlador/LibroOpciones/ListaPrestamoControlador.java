package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.DAO.PrestamoDAO;
import es.liernisarraoa.biblioteca.Modelo.Prestamo;
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

public class ListaPrestamoControlador implements Initializable {
    @FXML
    public Button btnVolver;
    @FXML
    public ListView<Prestamo> listaPrestamos;

    private Stage stage;
    private ArrayList<Prestamo> arrayListPrestamos;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void volverPregunta(ActionEvent actionEvent) {
        ((Stage)btnVolver.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipVolverAniadirPrestamo = new Tooltip("Volver al formulario");

        // Asignar el Tooltip al botón
        Tooltip.install(btnVolver, tooltipVolverAniadirPrestamo);

        arrayListPrestamos = PrestamoDAO.listaDePrestamos();
        listaPrestamos.getItems().addAll(arrayListPrestamos);
    }
}
