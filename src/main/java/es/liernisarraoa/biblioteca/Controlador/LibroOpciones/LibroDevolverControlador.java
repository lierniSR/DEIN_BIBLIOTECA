package es.liernisarraoa.biblioteca.Controlador.LibroOpciones;

import es.liernisarraoa.biblioteca.Biblioteca;
import es.liernisarraoa.biblioteca.Controlador.BibliotecaControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroControlador;
import es.liernisarraoa.biblioteca.Controlador.LibroOpciones.Dialogos.HechoControlador;
import es.liernisarraoa.biblioteca.DAO.HistoricoDAO;
import es.liernisarraoa.biblioteca.DAO.LibroDAO;
import es.liernisarraoa.biblioteca.DAO.PrestamoDAO;
import es.liernisarraoa.biblioteca.Modelo.HistoricoPrestamo;
import es.liernisarraoa.biblioteca.Modelo.Libro;
import es.liernisarraoa.biblioteca.Modelo.Prestamo;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Formulario para poder devolver un libro
 *
 * @version 1.0
 * @author Lierni Sarraoa Joaquin
 */
public class LibroDevolverControlador implements Initializable {
    /**
     * Atributos del archivo aniadirLibro.fxml
     */
    @FXML
    public ImageView iwHome;
    @FXML
    public Button btnDevolver;
    @FXML
    public Button btnVolver;
    @FXML
    public ListView<Prestamo> lvPrestamos;

    /**
     * Atributos que se necesita para la clase
     */
    private Stage stage;
    private Stage modalStage;
    private Scene modalScene;

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
     * Al clical en el boton de devolver se hacen varias consultas a la base de tos
     * con las clases LibroDao, HistoricoDAO y PrestamoDAO.
     *
     * @param actionEvent
     */
    public void devolverLibro(ActionEvent actionEvent) {
        Prestamo prestamo = lvPrestamos.getSelectionModel().getSelectedItem();
        String tituloLibro = LibroDAO.tituloPorCodigo(prestamo.getCodigo_libro());
        if(prestamo != null){
            LocalDate fecha_actual = LocalDate.now();
            HistoricoPrestamo historico = new HistoricoPrestamo(prestamo.getId_prestamo(), prestamo.getDni_alumno(), prestamo.getCodigo_libro(), Date.valueOf(prestamo.getFecha_prestamo()), Date.valueOf(fecha_actual));
            HistoricoDAO.insertarHistorico(historico);
            PrestamoDAO.eliminarPrestamoPorLibro(prestamo.getCodigo_libro().toString());
            //Esto si el controlador necesita hacer algo en la ventana principal
            // Cargar el FXML de la ventana modal
            FXMLLoader loader =  new FXMLLoader(Biblioteca.class.getResource("Libro/dialogoEstadoDevolucion.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                modalStage = new Stage();
                modalScene = new Scene(root);

                modalStage.setScene(modalScene);
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("LIBRO: " + tituloLibro);
                modalStage.setResizable(false);
                //Pasar al controlador el Stage
                LibroActualizarEstadoDevolucion controlador = loader.getController();
                controlador.setStage(modalStage);
                controlador.setTitulo(tituloLibro);

                modalStage.showAndWait();
                lvPrestamos.getItems().setAll(PrestamoDAO.listaDePrestamos());
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
            alert.setTitle("SELECCION");
            alert.setContentText("No has seleccionado ningun prestamo a devolver.");
            alert.showAndWait();
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
     * Es la implementacion de la interfaz Initializable.
     * Se ejecuta cada vez que se abre esta ventana.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un Tooltip
        Tooltip tooltipLista = new Tooltip("Lista de prestamos");
        Tooltip tooltipDevolver = new Tooltip("Devolver el libro del prestamo");
        Tooltip tooltipHome = new Tooltip("Volver a la pagina principal");
        Tooltip tooltipVolverLibro = new Tooltip("Volver a gestion de libros");

        // Asignar el Tooltip al botón
        Tooltip.install(btnDevolver, tooltipDevolver);
        Tooltip.install(lvPrestamos, tooltipLista);
        Tooltip.install(btnVolver, tooltipVolverLibro);
        Tooltip.install(iwHome, tooltipHome);

        lvPrestamos.getItems().addAll(PrestamoDAO.listaDePrestamos());
    }

    /**
     * Este atributo es para poder cambiar de ventanas
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
