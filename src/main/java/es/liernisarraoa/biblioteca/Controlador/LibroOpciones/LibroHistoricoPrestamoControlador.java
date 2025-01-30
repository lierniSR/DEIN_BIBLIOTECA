package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.DAO.HistoricoDAO;
import es.liernisarraoa.biblioteca.Modelo.HistoricoPrestamo;
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

public class LibroHistoricoPrestamoControlador implements Initializable {
    @FXML
    public TextField tfDNI;
    @FXML
    public DatePicker dpFechaPrestamo;
    @FXML
    public TextField tfCodigoLibro;
    @FXML
    public ImageView iwFiltro;
    @FXML
    public ListView<HistoricoPrestamo> listaHistorico;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnVolver;
    @FXML
    public DatePicker dpFechaDevolucion;

    private Stage stage;

    public void volverLibros(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/libros.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Gestion de libros");
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
        }
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

    public void filtrar(MouseEvent mouseEvent) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipLista = new Tooltip("Lista del historico de prestamos");
        Tooltip tooltipHome = new Tooltip("Volver a la pagina inicial");
        Tooltip tooltipVolverLibro= new Tooltip("Volver a la pagina de libros");
        Tooltip tooltipFiltro = new Tooltip("Filtrar la lista");
        Tooltip tooltipFechaDevolucion = new Tooltip("Campo fecha devolucion");
        Tooltip tooltipFechaPrestamo = new Tooltip("Campo fecha prestamo");
        Tooltip tooltipCodigoLibro = new Tooltip("Campo codigo de libro");
        Tooltip tooltipDNI = new Tooltip("Campo DNI");

        // Asignar el Tooltip al botón
        Tooltip.install(btnVolver, tooltipVolverLibro);
        Tooltip.install(iwHome, tooltipHome);
        Tooltip.install(listaHistorico, tooltipLista);
        Tooltip.install(iwFiltro, tooltipFiltro);
        Tooltip.install(dpFechaDevolucion, tooltipFechaDevolucion);
        Tooltip.install(dpFechaPrestamo, tooltipFechaPrestamo);
        Tooltip.install(tfCodigoLibro, tooltipCodigoLibro);
        Tooltip.install(tfDNI, tooltipDNI);

        listaHistorico.getItems().addAll(HistoricoDAO.listaDelHistorial());
    }
}
