package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.AlumnoControlador;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoControlador;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
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

public class LibroAniadirControlador implements Initializable {
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
    public Button btnAniadir;
    @FXML
    public Button btnVolver;

    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public void aniadirLibro(ActionEvent actionEvent) {
        if(!tfTitulo.getText().isEmpty() && !tfAutor.getText().isEmpty() && !tfEditorial.getText().isEmpty() && !seleccionEstado.getSelectionModel().getSelectedItem().isEmpty()){
            try{
                Integer codigo = Integer.valueOf(tfCodigo.getText());
                int baja = 0;
                if(cbBaja.isSelected()){
                    baja = 1;
                }
                Libro libro = new Libro(codigo, tfTitulo.getText(), tfAutor.getText(), tfEditorial.getText(), seleccionEstado.getSelectionModel().getSelectedItem(), baja);
                if(LibroDAO.aniadirLibro(libro)){
                    //Esto si el controlador necesita hacer algo en la ventana principal
                    // Cargar el FXML de la ventana modal
                    FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoBien.fxml"));
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
                        HechoControlador controlador = loader.getController();
                        controlador.setStage(modalStage);
                        modalStage.showAndWait();
                        tfCodigo.setText("");
                        tfTitulo.setText("");
                        tfAutor.setText("");
                        tfEditorial.setText("");
                        seleccionEstado.getSelectionModel().clearSelection();
                        cbBaja.setSelected(false);

                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("FXML");
                        alert.setContentText("El archivo que contiene la visualizacion de la pestaña no se ha podido cargar.");
                        alert.showAndWait();
                        throw new RuntimeException(e);
                    }
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("NO INSERTADO");
                alert.setContentText("El campo codigo tiene algún caracter, tiene que ser numérico");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("NO INSERTADO");
            alert.setContentText("El libro que acabas de insertar no se ha podido guardar.\n" +
                    "Todos los campos que aparecen en el formulario tienen que estar llenos.");
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
        Tooltip tooltipAniadir = new Tooltip("Añadir libro");
        Tooltip tooltipVolverLibro = new Tooltip("Volver a gestion de libros");

        // Asignar el Tooltip al botón
        Tooltip.install(tfCodigo, tooltipCodigo);
        Tooltip.install(tfTitulo, tooltipTitulo);
        Tooltip.install(tfAutor, tooltipAutor);
        Tooltip.install(tfEditorial, tooltipEditorial);
        Tooltip.install(seleccionEstado, tooltipEstado);
        Tooltip.install(cbBaja, tooltipBaja);
        Tooltip.install(btnAniadir, tooltipAniadir);
        Tooltip.install(btnVolver, tooltipVolverLibro);
        Tooltip.install(iwHome, tooltipHome);

        seleccionEstado.getItems().addAll(
                "Nuevo",
                "Usado nuevo",
                "Usado seminuevo",
                "Usado estropeado",
                "Restaurado"
        );
    }
}
