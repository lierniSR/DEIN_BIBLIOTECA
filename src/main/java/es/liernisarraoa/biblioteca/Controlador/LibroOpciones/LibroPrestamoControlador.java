package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoEliminarControlador;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.Modelo.Libro;
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

public class LibroPrestamoControlador implements Initializable {
    @FXML
    public ListView<Libro> seleccionLibro;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnPrestar;
    @FXML
    public Button btnVolver;


    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;

    public void volverLibro(ActionEvent actionEvent) {
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipLista = new Tooltip("Lista de libros");
        Tooltip tooltipHome = new Tooltip("Volver a la pagina inicial");
        Tooltip tooltipVolverLibro= new Tooltip("Volver a la pagina de libros");
        Tooltip tooltipPrestar = new Tooltip("Coger prestado el libro");

        // Asignar el Tooltip al botón
        Tooltip.install(btnPrestar, tooltipPrestar);
        Tooltip.install(btnVolver, tooltipVolverLibro);
        Tooltip.install(iwHome, tooltipHome);
        Tooltip.install(seleccionLibro, tooltipLista);

        seleccionLibro.getItems().addAll(LibroDAO.listarLibrosNoPrestados());
    }

    public void prestarLibro(ActionEvent actionEvent) {
            FXMLLoader fxmlLoader = new FXMLLoader(Biblioteca.class.getResource("Libro/prestamoLibroFormulario.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Prestamos de libros");
                stage.setResizable(false);
                stage.setScene(scene);
                //Pasar al controlador el Stage
                LibroPrestamoFormularioControlador controlador = fxmlLoader.getController();
                controlador.setStage(stage);
                stage.show();


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
