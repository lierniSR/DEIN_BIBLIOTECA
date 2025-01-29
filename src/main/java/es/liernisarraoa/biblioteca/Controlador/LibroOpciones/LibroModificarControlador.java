package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoModificarControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.Modelo.Alumno;
import es.liernisarraoa.biblioteca.Modelo.Libro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LibroModificarControlador implements Initializable {
    @FXML
    public ChoiceBox<Libro> seleccionLibro;
    @FXML
    public TextField tfCodigo;
    @FXML
    public TextField tfTitulo;
    @FXML
    public TextField tfAutor;
    @FXML
    public TextField tfEditorial;
    @FXML
    public ChoiceBox<String> seleccionEstado;
    @FXML
    public CheckBox cbBaja;
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnModificar;
    @FXML
    public Button btnVolver;

    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;

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

    public void modificarLibro(ActionEvent actionEvent) {
        if(!tfTitulo.getText().isEmpty() && !tfAutor.getText().isEmpty() && !tfEditorial.getText().isEmpty() && !seleccionEstado.getSelectionModel().getSelectedItem().isEmpty()){
            Libro libro = null;
            if(cbBaja.isSelected()){
                libro = new Libro(Integer.parseInt(tfCodigo.getText()), tfTitulo.getText(),tfAutor.getText(), tfEditorial.getText(), seleccionEstado.getSelectionModel().getSelectedItem(), 1);
            } else {
                libro = new Libro(Integer.parseInt(tfCodigo.getText()), tfTitulo.getText(),tfAutor.getText(), tfEditorial.getText(), seleccionEstado.getSelectionModel().getSelectedItem(), 2);
            }
            if(LibroDAO.actualizarLibro(libro)) {
                //Esto si el controlador necesita hacer algo en la ventana principal
                // Cargar el FXML de la ventana modal
                FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoBienModificar.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                    modalStage = new Stage();
                    modalScene = new Scene(root);

                    modalStage.setScene(modalScene);
                    modalStage.initModality(Modality.APPLICATION_MODAL);
                    modalStage.setTitle("SE HA GUARDADO");
                    modalStage.setResizable(false);
                    //Pasar al controlador el Stage
                    HechoModificarControlador controlador = loader.getController();
                    controlador.setStage(modalStage);
                    modalStage.showAndWait();

                    // Actualizar los elementos del ListView
                    seleccionLibro.getItems().setAll(LibroDAO.listaDeLibros());
                    if(cbBaja.isSelected()){
                        seleccionLibro.getSelectionModel().select(new Libro(Integer.parseInt(tfCodigo.getText()), tfTitulo.getText(),tfAutor.getText(), tfEditorial.getText(), seleccionEstado.getSelectionModel().getSelectedItem(), 1));
                    } else {
                        seleccionLibro.getSelectionModel().select(new Libro(Integer.parseInt(tfCodigo.getText()), tfTitulo.getText(),tfAutor.getText(), tfEditorial.getText(), seleccionEstado.getSelectionModel().getSelectedItem(), 2));
                    }
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("FXML");
                    alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                    alert.showAndWait();
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("NO ACTUALIZADO");
                alert.setContentText("El libro que acabas de intentar actualizar no se ha podido guardar.\n" +
                        "Todos los campos que aparecen en el formulario tienen que estar llenos.");
                alert.showAndWait();
            }
            }
    }

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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipCodigo = new Tooltip("Campo codigo");
        Tooltip tooltipTitulo = new Tooltip("Campo titulo");
        Tooltip tooltipAutor = new Tooltip("Campo autor");
        Tooltip tooltipEditorial = new Tooltip("Campo editorial");
        Tooltip tooltipEstado = new Tooltip("Campo estado");
        Tooltip tooltipBaja = new Tooltip("Campo baja");
        Tooltip tooltipHome = new Tooltip("Volver a la pagina principal");
        Tooltip tooltipModificar = new Tooltip("Guardar cambios del libro");
        Tooltip tooltipVolverLibro = new Tooltip("Volver a gestion de libros");

        // Asignar el Tooltip al botón
        Tooltip.install(tfCodigo, tooltipCodigo);
        Tooltip.install(tfTitulo, tooltipTitulo);
        Tooltip.install(tfAutor, tooltipAutor);
        Tooltip.install(tfEditorial, tooltipEditorial);
        Tooltip.install(seleccionEstado, tooltipEstado);
        Tooltip.install(cbBaja, tooltipBaja);
        Tooltip.install(btnModificar, tooltipModificar);
        Tooltip.install(btnVolver, tooltipVolverLibro);
        Tooltip.install(iwHome, tooltipHome);

        seleccionEstado.getItems().addAll(
                "Nuevo",
                "Usado nuevo",
                "Usado seminuevo",
                "Usado estropeado",
                "Restaurado"
        );

        seleccionLibro.getItems().addAll(LibroDAO.listaDeLibros());
        seleccionLibro.setOnAction(actionEvent -> {
            Libro libro = seleccionLibro.getSelectionModel().getSelectedItem();
            tfTitulo.setDisable(false);
            tfAutor.setDisable(false);
            tfEditorial.setDisable(false);
            seleccionEstado.setDisable(false);
            cbBaja.setDisable(false);

            tfCodigo.setText(libro.getCodigo().toString());
            tfTitulo.setText(libro.getTitulo());
            tfAutor.setText(libro.getAutor());
            tfEditorial.setText(libro.getEditorial());
            seleccionEstado.getSelectionModel().select(libro.getEstado());
            if(libro.getBaja() == 0){
                cbBaja.setSelected(false);
            } else {
                cbBaja.setSelected(true);
            }
        });
    }
}
