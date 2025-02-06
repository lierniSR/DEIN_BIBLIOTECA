package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoModificarControlador;
import es.liernisarraoa.biblioteca.DAO.AlumnoDAO;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.DAO.PrestamoDAO;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Ventana para poder modificar un libro
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class LibroModificarControlador implements Initializable {
    /**
     * Atributos del archivo modificarLibro.fxml
     */
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

    /**
     * Atributos que se necesita para la clase
     */
    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;
    private final Integer baja = 0;

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
     * Para poder modificar el libro seleccionado
     *
     * @param actionEvent
     */
    public void modificarLibro(ActionEvent actionEvent) {
        int nuevaBaja = cbBaja.isSelected() ? 1 : 0;

        if (!tfTitulo.getText().isEmpty() && !tfAutor.getText().isEmpty() && !tfEditorial.getText().isEmpty()
                && seleccionEstado.getSelectionModel().getSelectedItem() != null) {

            Libro libro = new Libro(
                    Integer.parseInt(tfCodigo.getText()),
                    tfTitulo.getText(),
                    tfAutor.getText(),
                    tfEditorial.getText(),
                    seleccionEstado.getSelectionModel().getSelectedItem(),
                    nuevaBaja
            );

            if (LibroDAO.actualizarLibro(libro)) {
                // Mostrar el modal de confirmación
                try {
                    FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoBienModificar.fxml"));
                    Parent root = loader.load();
                    modalStage = new Stage();
                    modalScene = new Scene(root);

                    modalStage.setScene(modalScene);
                    modalStage.initModality(Modality.APPLICATION_MODAL);
                    modalStage.setTitle("SE HA GUARDADO");
                    modalStage.setResizable(false);

                    HechoModificarControlador controlador = loader.getController();
                    controlador.setStage(modalStage);
                    modalStage.showAndWait();

                    // Actualizar ListView
                    seleccionLibro.getItems().setAll(LibroDAO.listaDeLibros());
                    seleccionEstado.getItems().setAll(LibroDAO.listaTitulosEnPrestamo());
                    seleccionLibro.getSelectionModel().select(libro);
                    System.out.println(baja);
                    System.out.println(nuevaBaja);
                    // Verificar si el estado cambió
                    if (nuevaBaja != baja) {
                        System.out.println("Cambio de estado detectado.");

                        // Obtener la lista de títulos en préstamo en un Set para búsqueda rápida
                        Set<String> titulosEnPrestamo = new HashSet<>(LibroDAO.listaTitulosEnPrestamo());

                        if (titulosEnPrestamo.contains(tfTitulo.getText())) {
                            // Eliminar préstamo si el libro estaba en la lista
                            PrestamoDAO.eliminarPrestamoPorLibro(tfCodigo.getText());

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setTitle("PRESTAMOS");
                            alert.setContentText("Se ha eliminado el préstamo asociado al libro.");
                            alert.showAndWait();
                        }
                    }

                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("FXML");
                    alert.setContentText("El archivo que contiene la visualización de la pestaña no se ha podido cargar.");
                    alert.showAndWait();
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("NO ACTUALIZADO");
                alert.setContentText("El libro no se ha podido guardar.\nTodos los campos deben estar llenos.");
                alert.showAndWait();
            }
        }
    }

    /**
     * Este ActionEvent es para el boton de volver.
     * Para volver a la gestion de Libros.
     * Layout libros.fxml y el controlador LibroControlador.
     *
     * @param actionEvent
     */
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
