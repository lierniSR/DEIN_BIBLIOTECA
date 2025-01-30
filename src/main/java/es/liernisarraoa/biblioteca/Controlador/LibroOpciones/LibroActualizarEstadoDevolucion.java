package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoDevolverControlador;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.DAO.PrestamoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LibroActualizarEstadoDevolucion implements Initializable {
    @FXML
    public Label lblEstado;
    @FXML
    public ChoiceBox<String> cbEstadoLibro;
    @FXML
    public Button btnGuardar;
    @FXML
    public Button btnVolver;

    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;
    private String titulo;

    public void guardarEstadoLibro(ActionEvent actionEvent) {
        if(!cbEstadoLibro.getSelectionModel().isEmpty()){
            Integer codigo_libro = LibroDAO.conseguirCodigoConTitulo(titulo);
            LibroDAO.actualizarEstadoLibro(cbEstadoLibro.getSelectionModel().getSelectedItem(), codigo_libro);
            //Esto si el controlador necesita hacer algo en la ventana principal
            // Cargar el FXML de la ventana modal
            FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoBienDevolver.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                modalStage = new Stage();
                modalScene = new Scene(root);

                modalStage.setScene(modalScene);
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("DEVUELTO");
                modalStage.setResizable(false);
                //Pasar al controlador el Stage
                HechoDevolverControlador controlador = loader.getController();
                controlador.setStage(modalStage);
                modalStage.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("FXML");
                alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                alert.showAndWait();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbEstadoLibro.getItems().addAll(
                "Nuevo",
                "Usado nuevo",
                "Usado seminuevo",
                "Usado estropeado",
                "Restaurado"
        );

        lblEstado.setText("Se ha devuelto el libro.\n" +
                "¿Quieres actualizar el estado del\n" +
                " libro?\n" +
                "Si no quieres actualizar deja el\n" +
                " pulsa el boton de volver.");

        // Crear un Tooltip
        Tooltip tooltipEstado = new Tooltip("Cambiar el estado del libro");
        Tooltip tooltipGuradar = new Tooltip("Actualizar estado del libro");
        Tooltip tooltipVolver = new Tooltip("Volver a la lista de prestamos");

        // Asignar el Tooltip al botón
        Tooltip.install(cbEstadoLibro, tooltipEstado);
        Tooltip.install(btnGuardar, tooltipGuradar);
        Tooltip.install(btnVolver, tooltipVolver);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void volverPrestamo(ActionEvent actionEvent) {
        ((Stage)lblEstado.getScene().getWindow()).close();
    }
}
